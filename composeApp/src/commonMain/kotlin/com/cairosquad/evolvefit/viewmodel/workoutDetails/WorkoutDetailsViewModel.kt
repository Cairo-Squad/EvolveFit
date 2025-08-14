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
        tryToCall(
            block = { manageWorkoutUseCase.getWorkoutById(workoutId) },
            onSuccess = { workout -> updateState { state -> state.copy(workout = workout.toUiState()) } },
            onError = { updateState { it.copy(isLoading = false) } },
            onStart = { updateState { it.copy(isLoading = true ) } }
        )
    }

    override fun onClickBack() {
        sendEffect(WorkoutDetailsEffect.NavigateBack)
    }

    override fun onClickShare() {
        updateState {it.copy(isShareClicked = true) }
    }

    override fun onClickAddToFavorite(workoutId: String) {

        updateState { it.copy(isFavorite = true) }
        tryToCall(
            block = { manageWorkoutUseCase.addWorkoutToFavorites(workoutId) },
            onSuccess = {
                updateState { it.copy(isFavorite = true) }
            },
            onError = {  }
        )
    }

    override fun onClickExercise(exercise: WorkoutDetailsScreenState.ExerciseUiState) {
        updateState { state -> state.copy(workout = state.workout.copy(selectedExercise = exercise)) }
    }


    override fun onExerciseBottomSheetDismiss() {
        updateState { state -> state.copy(workout = state.workout.copy(selectedExercise = null)) }
    }

    override fun onClickStartWorkout(workoutId: String) {
        sendEffect(WorkoutDetailsEffect.NavigateToPlayWorkout)
    }
}