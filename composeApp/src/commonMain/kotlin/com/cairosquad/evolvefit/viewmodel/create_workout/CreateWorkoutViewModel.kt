package com.cairosquad.evolvefit.viewmodel.create_workout

import com.cairosquad.evolvefit.domain.entity.Exercise
import com.cairosquad.evolvefit.domain.entity.Workout
import com.cairosquad.evolvefit.domain.usecase.exercise.ManageExerciseUseCase
import com.cairosquad.evolvefit.domain.usecase.workout.ManageWorkoutUseCase
import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel
import com.cairosquad.evolvefit.viewmodel.create_workout.CreateWorkOutEffect.NavigateBack
import com.cairosquad.evolvefit.viewmodel.create_workout.CreateWorkOutScreenState.WorkoutLevel
import com.cairosquad.evolvefit.viewmodel.onboarding.models.UiImage
import com.cairosquad.evolvefit.viewmodel.utils.asByteArray

class CreateWorkoutViewModel(
    private val manageWorkoutUseCase: ManageWorkoutUseCase,
    private val manageExerciseUseCase: ManageExerciseUseCase
) : BaseViewModel<CreateWorkOutScreenState, CreateWorkOutEffect>(CreateWorkOutScreenState()),
    CreateWorkOutInteractionListener {

    init {
        loadExercises()
    }

    private fun pushWorkoutImage(id: String) {
        val image = screenState.value.image ?: run {
            return
        }
        tryToCall(
            block = {
                val imageFileData = image.asByteArray()
                manageWorkoutUseCase.uploadWorkoutImage(
                    imageFileData.bytes,
                    imageFileData.fileName,
                    id
                )
            },
            onSuccess = { imageUrl ->
                updateState { it.copy(image = UiImage.ImageUrl(imageUrl)) }
            },
            onError = {}
        )
    }

    private fun handleExercisesResultSuccess(exercises: List<Exercise>) {
        val uiExercises = exercises.map { it.toUiState() }
        updateState {
            it.copy(
                status = CreateWorkOutScreenState.ScreenStatus.SUCCESS,
                allExercises = uiExercises,
                filteredExercises = uiExercises
            )
        }
    }

    fun loadExercises() {
        tryToCall(
            block = { manageExerciseUseCase.getAllExercises() },
            onSuccess = ::handleExercisesResultSuccess,
            onError = ::handleWorkoutError,
            onStart = ::handleWorkoutLoading,
            onEnd = {}
        )
    }

    override fun onImageSelected(image: UiImage) {
        updateState { it.copy(image = image, isImagePickerOpen = false) }
    }

    override fun onImageClicked() {
        updateState { it.copy(isImagePickerOpen = true) }
    }

    override fun onImagePickerDismiss() {
        updateState { it.copy(isImagePickerOpen = false) }
    }

    override fun onNameChanged(newName: String) {
        val cleanName = if (newName.isBlank()) "" else newName
        updateState {
            it.copy(
                name = cleanName,
                isNextEnabled = validate(cleanName, it.level?.name?:"", it.description)
            )
        }
    }

    override fun onGoalSelected(goalName: String) {
        val selectedGoal =
            enumValues<WorkoutLevel>().firstOrNull { it.name == goalName } ?: WorkoutLevel.BEGINNER
        updateState {
            it.copy(
                level = selectedGoal,
                isNextEnabled = validate(it.name, selectedGoal.name, it.description)
            )
        }
    }

    override fun onDescriptionChanged(desc: String) {
        val trimmed = desc.take(3000)
        updateState {
            it.copy(
                description = trimmed,
                isNextEnabled = validate(it.name, it.level?.name?:"", trimmed)
            )
        }
    }

    private fun validate(name: String, goal: String, desc: String): Boolean {
        return name.isNotBlank() && goal.isNotBlank() && desc.isNotBlank()
    }

    override fun onExerciseCheckedChanged(exercise: CreateWorkOutScreenState.ExerciseUiState) {
        updateState { currentState ->
            val selected = currentState.selectedExercises.toMutableList().apply {
                if (any { it.id == exercise.id }) removeAll { it.id == exercise.id }
                else add(exercise)
            }
            currentState.copy(
                selectedExercises = selected,
                exerciseCount = selected.size,
                isAddExercisesEnabled = selected.isNotEmpty()
            )
        }
    }

    override fun onBackClicked() {
        updateState { it.copy(currentStep = CreateWorkOutScreenState.CreateWorkoutStep.DETAILS) }
    }

    override fun onExitClicked() {
        sendEffect(NavigateBack)
    }

    override fun onAddClicked() {
        sendEffect(CreateWorkOutEffect.NavigateToCreateExercise)
    }

    override fun onSearchQueryChanged(query: String) {
        updateState { state ->
            val filtered =
                if (query.isBlank()) state.allExercises
                else state.allExercises.filter { it.name.contains(query, ignoreCase = true) }
            state.copy(
                searchQuery = query,
                filteredExercises = filtered
            )
        }
    }

    override fun onNextClicked() {
        updateState { it.copy(currentStep = CreateWorkOutScreenState.CreateWorkoutStep.EXERCISES) }
    }

    private fun handleWorkoutSuccess(createWorkout: Workout) {
        updateState { it.copy(status = CreateWorkOutScreenState.ScreenStatus.SUCCESS) }
        pushWorkoutImage(createWorkout.id)
        sendEffect(CreateWorkOutEffect.NavigateToWorkouts)
    }

    private fun handleWorkoutError(throwable: Throwable) {
        throwable.printStackTrace()
        updateState { it.copy(status = CreateWorkOutScreenState.ScreenStatus.ERROR) }
    }

    private fun handleWorkoutLoading() {
        updateState { it.copy(status = CreateWorkOutScreenState.ScreenStatus.LOADING) }
    }

    override fun onAddWorkoutClicked() {
        val selectedDomainExercises = screenState.value.selectedExercises.map { it }
        val workout = screenState.value.toDomainWorkout(selectedDomainExercises)

        println(">>> Creating workout: $workout")

        tryToCall(
            block = { manageWorkoutUseCase.createWorkOut(workout) },
            onSuccess = { createdWorkout ->
                handleWorkoutSuccess(createdWorkout)
                pushWorkoutImage(createdWorkout.id)
            },
            onError = {
                handleWorkoutError(it)
            },
            onStart = {
                handleWorkoutLoading()
            }
        )
    }
}