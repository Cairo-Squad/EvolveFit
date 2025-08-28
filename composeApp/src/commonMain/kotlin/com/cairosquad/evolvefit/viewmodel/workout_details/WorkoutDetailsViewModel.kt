package com.cairosquad.evolvefit.viewmodel.workout_details

import androidx.lifecycle.viewModelScope
import com.cairosquad.evolvefit.domain.entity.Workout
import com.cairosquad.evolvefit.domain.entity.WorkoutSuggested
import com.cairosquad.evolvefit.domain.usecase.workout.ManageWorkoutUseCase
import com.cairosquad.evolvefit.ui.util.Share
import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.fail_to_add_workout_to_your_favorites
import evolvefit.composeapp.generated.resources.fail_to_remove_workout_from_your_favorites
import evolvefit.composeapp.generated.resources.ic_green_check_circle
import evolvefit.composeapp.generated.resources.ic_info
import evolvefit.composeapp.generated.resources.ic_save_tick
import evolvefit.composeapp.generated.resources.workout_added_to_your_favorites
import evolvefit.composeapp.generated.resources.workout_removed_from_your_favorites
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

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
            onSuccess = ::handleSaveWorkoutSuccess,
            onError = ::handleSaveWorkoutError
        )
    }

    private suspend fun toggleWorkoutSavedStatus(workoutId: String, isSaved: Boolean) {
        if (isSaved) {
            manageWorkoutUseCase.deleteFavouriteWorkout(workoutId)
        } else {
            manageWorkoutUseCase.addWorkoutToFavorites(workoutId)
        }
    }

    private fun handleSaveWorkoutSuccess(response: Unit) {
        val newFavoriteState = !screenState.value.isFavorite
        updateState {
            it.copy(
                isFavorite = newFavoriteState
            )
        }
        showSnackBar(
            messageRes =
                if (newFavoriteState) Res.string.workout_added_to_your_favorites
                else Res.string.workout_removed_from_your_favorites,
            iconRes =
                if (newFavoriteState) Res.drawable.ic_save_tick
                else Res.drawable.ic_green_check_circle,
        )
    }

    private fun handleSaveWorkoutError(e: Throwable) {
        updateState { it.copy(isLoading = false) }
        val isFavorite = screenState.value.isFavorite
        showSnackBar(
            messageRes =
                if (isFavorite) Res.string.fail_to_remove_workout_from_your_favorites
                else Res.string.fail_to_add_workout_to_your_favorites,
            iconRes = Res.drawable.ic_info,
        )
    }

    private var snackBarJob: Job? = null
    private fun showSnackBar(
        messageRes: StringResource,
        iconRes: DrawableResource,
        durationMilli: Long = 3000,
    ) {
        snackBarJob?.cancel()
        updateState {
            it.copy(
                snackBarState = WorkoutDetailsScreenState.SnackBarState(
                    isVisible = true,
                    messageRes = messageRes,
                    iconRes = iconRes
                )
            )
        }
        snackBarJob = viewModelScope.launch {
            delay(durationMilli)
            updateState { it.copy(snackBarState = it.snackBarState.copy(isVisible = false)) }
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

    companion object {
        const val DEEP_LINK_BASE_URL = "https://evolvefit.com/workouts/"
    }
}