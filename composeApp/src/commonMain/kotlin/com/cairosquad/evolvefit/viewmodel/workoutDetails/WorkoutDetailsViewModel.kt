package com.cairosquad.evolvefit.viewmodel.workoutDetails

import com.cairosquad.evolvefit.domain.usecase.workout.ManageWorkoutUseCase
import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel

class WorkoutDetailsViewModel(
    workoutId: String,
    private val manageWorkoutUseCase: ManageWorkoutUseCase,
) : WorkoutDetailsInteractionListener,
    BaseViewModel<WorkoutDetailsScreenState, WorkoutDetailsEffect>(WorkoutDetailsScreenState()) {
    init {
        loadData(workoutId)
    }

    private fun loadData(workoutId: String) {
        updateState { it.copy(isLoading = true ) }
        tryToCall(
            block = { manageWorkoutUseCase.getWorkoutById(workoutId) },
            onSuccess = { workout -> updateState { workout.toUiState().copy(isLoading = false) } },
            onError = { updateState { it.copy(isLoading = false) } }
        )
    }

    override fun onBackClick() {
        sendEffect(WorkoutDetailsEffect.NavigateBack)
    }

    override fun onShareClick() {
        updateState {
            it.copy(
                isShareClicked = true
            )
        }
    }

    override fun onAddToFavoriteClick(workoutId: String) {

        updateState { it.copy(isFavorite = true) }
        tryToCall(
            block = { manageWorkoutUseCase.addWorkoutToFavorites(workoutId) },
            onSuccess = {
                updateState { it.copy(isFavorite = true) }
            },
            onError = {  }
        )
    }

    override fun onExerciseClick(exercise: WorkoutDetailsScreenState.ExerciseUiState) {
        updateState {
            it.copy(selectedExercise = exercise)
        }
    }


    override fun onExerciseBottomSheetDismiss() {
        updateState {
            it.copy(selectedExercise = null)
        }
    }

    override fun onStartWorkoutClick(workoutId: String) {
        sendEffect(WorkoutDetailsEffect.NavigateToPlayWorkout)
    }
}