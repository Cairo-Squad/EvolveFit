package com.cairosquad.evolvefit.viewmodel.createWorkOut

import com.cairosquad.evolvefit.domain.entity.Exercise
import com.cairosquad.evolvefit.domain.usecase.exercise.ManageExerciseUseCase
import com.cairosquad.evolvefit.domain.usecase.workout.ManageWorkoutUseCase
import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel
import com.cairosquad.evolvefit.viewmodel.createWorkOut.CreateWorkOutScreenState.WorkoutLevel
import com.cairosquad.evolvefit.viewmodel.onboarding.models.UiImage

class CreateWorkoutViewModel(
    private val manageWorkoutUseCase: ManageWorkoutUseCase,
    private val manageExerciseUseCase: ManageExerciseUseCase
) : BaseViewModel<CreateWorkOutScreenState, CreateWorkOutEffect>(CreateWorkOutScreenState()),
    CreateWorkOutInteractionListener {

    init {
        loadExercises()
    }

    private fun handleExercisesResultSuccess(
        exercises: List<Exercise>
    ) {
        val uiExercises = exercises.map { it }
        updateState { it.copy(
                status = CreateWorkOutScreenState.ScreenStatus.SUCCESS,
                allExercises = uiExercises,
                filteredExercises = uiExercises
            )
        }
    }

    private fun loadExercises() {
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
        updateState { it.copy(name = newName, isNextEnabled = validate(newName, it.goal.name, it.description)) }
    }

    override fun onGoalSelected(goalName: String) {
        val selectedGoal = WorkoutLevel.valueOf(goalName)
        updateState { it.copy(goal = selectedGoal, isNextEnabled = validate(it.name, selectedGoal.name, it.description)) }
    }

    override fun onDescriptionChanged(desc: String) {
        val trimmed = desc.take(3000)
        updateState { it.copy(description = trimmed, isNextEnabled = validate(it.name, it.goal.name, trimmed)) }
    }

    private fun validate(name: String, goal: String, desc: String): Boolean {
        return name.isNotBlank() && goal.isNotBlank() && desc.isNotBlank()
    }

    override fun onExerciseCheckedChanged(exercise: Exercise) {
        updateState { currentState ->
            val selected = currentState.selectedExercises.toMutableList()
            if (selected.any { it.id == exercise.id }) {
                selected.removeAll { it.id == exercise.id }
            } else {
                selected.add(exercise)
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

    private fun handleWorkoutSuccess() {
        updateState { it.copy(status = CreateWorkOutScreenState.ScreenStatus.SUCCESS) }
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
        tryToCall(
            block = { manageWorkoutUseCase.createWorkOut(workout) },
            onSuccess = { handleWorkoutSuccess() },
            onError = ::handleWorkoutError,
            onStart = ::handleWorkoutLoading
        )
    }

}

