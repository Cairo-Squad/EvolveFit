package com.cairosquad.evolvefit.viewmodel.workout_details

import com.cairosquad.evolvefit.domain.usecase.workout.ManageWorkoutUseCase
import com.cairosquad.evolvefit.ui.util.Share
import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.workout_saved_successfully

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

    override fun onBackClicked() {
        sendEffect(WorkoutDetailsEffect.NavigateBack)
    }

    override fun onShareClicked() {
        updateState {it.copy(isShareClicked = true) }
    }

    override fun onAddToFavoriteClicked(workoutId: String) {
        updateState { it.copy(isFavorite = it.isFavorite.not()) }
        tryToCall(
            block = { manageWorkoutUseCase.addWorkoutToFavorites(workoutId) },
            onSuccess = {
                updateState { it.copy(isFavorite = true,snackBarMessageId = Res.string.workout_saved_successfully) }
            },
            onError = {  }
        )
    }

    override fun onExerciseClicked(exercise: WorkoutDetailsScreenState.ExerciseUiState) {
        updateState { state -> state.copy(workout = state.workout.copy(selectedExercise = exercise)) }
    }


    override fun onExerciseBottomSheetDismiss() {
        updateState { state -> state.copy(workout = state.workout.copy(selectedExercise = null)) }
    }

    override fun onShareBottomSheetDismiss() {
        updateState {it.copy(isShareClicked = false) }
    }

    override fun onStartWorkoutClicked(workoutId: String) {
        sendEffect(WorkoutDetailsEffect.NavigateToPlayWorkout)
    }

    override fun onShareWithCommunityClicked(workoutId: String) {
        sendEffect(WorkoutDetailsEffect.NavigateToShareWithCommunity(workoutId))

    }

    override fun onCopyLinkClicked(workoutId: String) {
        Share.copyLink("https://evolvefit.com/workouts/$workoutId") { message, _ ->
            updateState {
                it.copy(snackBarMessageId = message)
            }
        }
    }
}