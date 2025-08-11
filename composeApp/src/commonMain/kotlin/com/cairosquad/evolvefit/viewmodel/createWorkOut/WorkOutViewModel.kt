package com.cairosquad.evolvefit.viewmodel.createWorkOut

import com.cairosquad.evolvefit.domain.usecase.CreateWorkoutUseCase
import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel
import com.cairosquad.evolvefit.viewmodel.onboarding.models.UiImage

class CreateWorkoutViewModel(
    private val createWorkoutUseCase: CreateWorkoutUseCase
) : BaseViewModel<WorkOutScreenState, WorkOutEffect>(WorkOutScreenState()),
    WorkOutInteractionListener {

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
        updateState {
            it.copy(
                name = newName,
                isNextEnabled = validate(newName, it.goal ?: "", it.description)
            )
        }
    }

    override fun onGoalSelected(goal: String) {
        updateState {
            it.copy(
                goal = goal,
                isNextEnabled = validate(it.name, goal, it.description)
            )
        }
    }

    override fun onDescriptionChanged(desc: String) {
        val trimmed = desc.take(3000)
        updateState {
            it.copy(
                description = trimmed,
                isNextEnabled = validate(it.name, it.goal ?: "", trimmed)
            )
        }
    }

    private fun validate(name: String, goal: String, desc: String): Boolean {
        return name.isNotBlank() && goal.isNotBlank() && desc.isNotBlank()
    }

    init {
        loadExercises()
    }

    private fun loadExercises() {
        tryToCall(
            block = { createWorkoutUseCase.getAllExercises() },
            onSuccess = { exercises ->
                val uiExercises = exercises.map { it.toUiExercise() }
                updateState {
                    it.copy(
                        isLoading = false,
                        allExercises = uiExercises,
                        filteredExercises = uiExercises
                    )
                }
            },
            onError = { throwable ->
                updateState {
                    it.copy(
                        isLoading = false,
                    )
                }
            },
            onStart = {
                updateState { it.copy(isLoading = true) }
            },
            onEnd = {}
        )
    }

    override fun onExerciseCheckedChanged(id: String) {
        val exerciseId = id.toLongOrNull() ?: return
        updateState { currentState ->
            val newSelectedIds = currentState.selectedExerciseIds.toMutableList()
            if (newSelectedIds.contains(exerciseId)) {
                newSelectedIds.remove(exerciseId)
            } else {
                newSelectedIds.add(exerciseId)
            }
            currentState.copy(
                selectedExerciseIds = newSelectedIds.toList(),
                exerciseCount = newSelectedIds.size,
                isAddExercisesEnabled = newSelectedIds.isNotEmpty()
            )
        }
    }

    override fun onBackClicked() {
        updateState {
            it.copy(currentStep = WorkOutScreenState.CreateWorkoutStep.DETAILS)
        }
    }

    override fun onAddClicked() {
        sendEffect(WorkOutEffect.NavigateToCreateExercise)
    }

    override fun onSearchQueryChanged(query: String) {
        updateState { state ->
            val filtered = if (query.isBlank()) {
                state.allExercises
            } else {
                state.allExercises.filter {
                    it.name.contains(query, ignoreCase = true)
                }
            }
            state.copy(
                searchQuery = query,
                filteredExercises = filtered
            )
        }
    }

    override fun onNextClicked() {
        updateState {
            it.copy(currentStep = WorkOutScreenState.CreateWorkoutStep.EXERCISES)
        }
    }

    override fun onAddWorkoutClicked() {
        if (screenState.value.selectedExerciseIds.isEmpty()) return

        val workout = screenState.value.toDomainWorkout()

        tryToCall(
            block = { createWorkoutUseCase.createWorkOut(workout) },
            onSuccess = {
                sendEffect(WorkOutEffect.NavigateToWorkouts)
            },
            onError = { throwable ->
                updateState {
                    it.copy()
                }
            }
        )
    }

}
