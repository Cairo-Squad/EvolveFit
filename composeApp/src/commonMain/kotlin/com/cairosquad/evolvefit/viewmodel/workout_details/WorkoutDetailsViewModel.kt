package com.cairosquad.evolvefit.viewmodel.workout_details

import com.cairosquad.evolvefit.domain.entity.Workout
import com.cairosquad.evolvefit.domain.entity.WorkoutSuggested
import com.cairosquad.evolvefit.domain.usecase.workout.ManageWorkoutUseCase
import com.cairosquad.evolvefit.ui.util.Share
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
            onStart = ::onLoadDataStart,
            block = { manageWorkoutUseCase.getWorkoutById(workoutId) },
            onSuccess = ::handleGetWorkoutSuccess,
            onError = { updateState { it.copy(isLoading = false) } }
        )
    }

    private fun onLoadDataStart() {
        updateState {
            it.copy(
                isLoading = true,
                screenState = WorkoutDetailsScreenState.ScreenState.Loading
            )
        }
    }

    private fun handleGetWorkoutSuccess(workout: Workout) {
        updateState { state ->
            state.copy(
                workout = workout.toUiState(),
                screenState = WorkoutDetailsScreenState.ScreenState.Success
            )
        }
        loadWorkoutSaveStatus(workout.id)
    }

    private fun loadWorkoutSaveStatus(workoutId: String) {
        tryToCall(
            onStart = { updateState { it.copy(isLoading = true) } },
            block = { manageWorkoutUseCase.getFavoriteWorkouts() },
            onSuccess = { updateWorkoutSaveStatus(workoutId, it) },
            onError = { updateState { it.copy(isLoading = false) } }
        )
    }

    private fun updateWorkoutSaveStatus(workoutId: String, workouts: List<WorkoutSuggested>) {
        updateState { state ->
            state.copy(
                isFavorite = workouts.map { it.id }.contains(workoutId),
                isLoading = false
            )
        }
    }

    override fun onBackClicked() {
        sendEffect(WorkoutDetailsEffect.NavigateBack)
    }

    override fun onShareClicked() {
        updateState { it.copy(isShareClicked = true) }
    }

    override fun onToggleFavoriteClicked(workoutId: String, isSaved: Boolean) {
        tryToCall(
            onStart = { updateState { it.copy(isLoading = true) } },
            block = { toggleWorkoutSavedStatus(workoutId, isSaved) },
            onSuccess = { handleSaveWorkoutSuccess() },
            onError = { updateState { it.copy(isLoading = false) } }
        )
    }

    private suspend fun toggleWorkoutSavedStatus(workoutId: String, isSaved: Boolean) {
        if (isSaved) {
            manageWorkoutUseCase.deleteFavouriteWorkout(workoutId)
        } else {
            manageWorkoutUseCase.addWorkoutToFavorites(workoutId)
        }
    }

    private fun handleSaveWorkoutSuccess() {
        updateState {
            it.copy(
                isFavorite = !it.isFavorite
            )
        }
    }

    override fun onExerciseClicked(exercise: WorkoutDetailsScreenState.ExerciseUiState) {
        updateState { state -> state.copy(workout = state.workout.copy(selectedExercise = exercise)) }
    }


    override fun onExerciseBottomSheetDismiss() {
        updateState { state -> state.copy(workout = state.workout.copy(selectedExercise = null)) }
    }

    override fun onShareBottomSheetDismiss() {
        updateState { it.copy(isShareClicked = false) }
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