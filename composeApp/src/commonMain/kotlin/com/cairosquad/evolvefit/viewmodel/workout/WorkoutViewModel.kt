package com.cairosquad.evolvefit.viewmodel.workout

import com.cairosquad.evolvefit.domain.entity.WorkoutSuggested
import com.cairosquad.evolvefit.domain.usecase.workout.ManageWorkoutUseCase
import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel
import com.cairosquad.evolvefit.viewmodel.workout.WorkoutScreenState.FocusAreaUiState

class WorkoutViewModel(
    private val workoutUseCase: ManageWorkoutUseCase,

    ) : BaseViewModel<WorkoutScreenState, WorkoutEffect>(
    WorkoutScreenState()
), WorkoutInteractionListener {
    init {
        loadAllWorkouts()
    }

    private fun loadAllWorkouts() {
        onLoading()
        tryToCall(
            block = workoutUseCase::getSuggestedWorkouts,
            onSuccess = ::onGetSuggestedWorkoutsSuccess,
            onError = ::onGetSuggestedWorkoutError,
        )
    }

    private fun loadWorkoutsByFocusArea(focusAreaUiState: FocusAreaUiState) {
        onLoading()
        tryToCall(
            block = { workoutUseCase.getSuggestedWorkoutsByFocusArea(focusAreaUiState.toDomain()) },
            onSuccess = ::onLoadWorkoutByFocusAreaSuccess,
            onError = ::onLoadWorkoutByFocusAreaError
        )
    }

    override fun onFocusAreaSelected(focusArea: FocusAreaUiState) {
        updateState { it.copy(selectedFocusArea = focusArea, errorMessage = null) }

        if (focusArea == FocusAreaUiState.ALL) {
            loadAllWorkouts()
        } else {
            loadWorkoutsByFocusArea(focusArea)
        }
    }

    override fun onRetryClicked() {
        val selected = screenState.value.selectedFocusArea
        if (selected == FocusAreaUiState.ALL) loadAllWorkouts()
        else loadWorkoutsByFocusArea(selected)
    }

    override fun onWorkoutClicked(id: String) {
        sendEffect(WorkoutEffect.NavigateToWorkoutDetails(id))
    }

    override fun onAddWorkoutClicked() {
        sendEffect(WorkoutEffect.NavigateToCreateWorkout)
    }

    override fun onCommunityClicked() {
        sendEffect(WorkoutEffect.NavigateToCommunityWorkout)
    }

    private fun onGetSuggestedWorkoutsSuccess(workouts: List<WorkoutSuggested>) {
        updateState { it ->
            it.copy(
                allWorkouts = workouts.map { it.toUiState() },
                errorMessage = null,
                screenStatus = WorkoutScreenState.ScreenStatus.SUCCESS
            )
        }
    }

    private fun onGetSuggestedWorkoutError(t: Throwable) {
        updateState {
            it.copy(
                errorMessage = t.message ?: "Failed to load suggested workouts",
                screenStatus = WorkoutScreenState.ScreenStatus.FAIL
            )
        }
    }

    private fun onLoadWorkoutByFocusAreaSuccess(workouts: List<WorkoutSuggested>) {
        updateState { it ->
            it.copy(
                allWorkouts = workouts.map { it.toUiState() },
                errorMessage = null,
                screenStatus = WorkoutScreenState.ScreenStatus.SUCCESS
            )
        }
    }

    private fun onLoadWorkoutByFocusAreaError(t: Throwable) {
        updateState {
            it.copy(
                errorMessage = t.message ?: "Failed to load workouts by focus Area",
                screenStatus = WorkoutScreenState.ScreenStatus.FAIL
            )
        }
    }

    override fun onRefresh() {
        if (screenState.value.isRefreshing) return
        isRefreshing(true)
        val selected = screenState.value.selectedFocusArea
        tryToCall(
            block = onGetWorkoutsByFocusArea(selected),
            onSuccess = ::onRefreshSuccess,
            onError = ::onRefreshError
        )
    }

    private fun onLoading() {
        updateState {
            it.copy(
                screenStatus = WorkoutScreenState.ScreenStatus.LOADING,
                errorMessage = null
            )
        }
    }

    private fun isRefreshing(isRefreshing: Boolean) {
        updateState {
            it.copy(
                isRefreshing = isRefreshing,
                errorMessage = if (isRefreshing) null else it.errorMessage
            )
        }
    }

    private fun onRefreshSuccess(list: List<WorkoutSuggested>) {
        isRefreshing(false)
        updateState {
            it.copy(
                allWorkouts = list.map { w -> w.toUiState() },
                errorMessage = null
            )
        }
    }

    private fun onRefreshError(t: Throwable) {
        isRefreshing(false)
        updateState {
            it.copy(errorMessage = t.message ?: "Failed to refresh workouts")
        }
    }

    private fun onGetWorkoutsByFocusArea(focusArea: FocusAreaUiState): suspend () -> List<WorkoutSuggested> =
        {
            if (focusArea == FocusAreaUiState.ALL)
                workoutUseCase.getSuggestedWorkouts()
            else
                workoutUseCase.getSuggestedWorkoutsByFocusArea(focusArea.toDomain())
        }
}