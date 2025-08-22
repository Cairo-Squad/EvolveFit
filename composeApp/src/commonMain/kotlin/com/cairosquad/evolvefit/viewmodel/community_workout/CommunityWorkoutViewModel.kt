package com.cairosquad.evolvefit.viewmodel.community_workout

import com.cairosquad.evolvefit.domain.entity.WorkoutSuggested
import com.cairosquad.evolvefit.domain.usecase.workout.ManageWorkoutUseCase
import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel
import com.cairosquad.evolvefit.viewmodel.workout.WorkoutScreenState
import com.cairosquad.evolvefit.viewmodel.workout.toDomain
import com.cairosquad.evolvefit.viewmodel.workout.toUiState

class CommunityWorkoutViewModel(
    private val workoutUseCase: ManageWorkoutUseCase,
) : BaseViewModel<WorkoutScreenState, CommunityWorkoutEffect>(
    WorkoutScreenState()
), CommunityWorkoutInteractionListener {
    init {
        loadAllCommunityWorkouts()
    }

    private fun loadAllCommunityWorkouts() {
        onLoading()
        tryToCall(
            block = workoutUseCase::getCommunityWorkouts,
            onSuccess = ::onGetSuggestedWorkoutsSuccess,
            onError = ::onGetSuggestedWorkoutError,
        )
    }

    private fun loadCommunityWorkoutsByFocusArea(focusAreaUiState: WorkoutScreenState.FocusAreaUiState) {
        onLoading()
        tryToCall(
            block = { workoutUseCase.getCommunityWorkoutsByFocusArea(focusAreaUiState.toDomain()) },
            onSuccess = ::onLoadWorkoutByFocusAreaSuccess,
            onError = ::onLoadWorkoutByFocusAreaError

        )
    }

    override fun onFocusAreaSelected(focusArea: WorkoutScreenState.FocusAreaUiState) {
        updateState { it.copy(selectedFocusArea = focusArea, errorMessage = null) }
        if (focusArea == WorkoutScreenState.FocusAreaUiState.ALL) {
            loadAllCommunityWorkouts()
        } else {
            loadCommunityWorkoutsByFocusArea(focusArea)
        }
    }

    override fun onWorkoutClicked(id: String) {
        sendEffect(CommunityWorkoutEffect.NavigateToWorkoutDetails(id))
    }

    override fun onBackClicked() {
        sendEffect(CommunityWorkoutEffect.NavigateBack)
    }

    override fun getCommunityWorkout() {
        loadAllCommunityWorkouts()
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
                errorMessage =
                    t.message ?: "Failed to load community workouts",
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
                errorMessage = t.message ?: "Failed to load workouts by focus",
                screenStatus = WorkoutScreenState.ScreenStatus.FAIL
            )
        }

    }

    override fun onRefresh() {
        if (screenState.value.isRefreshing) return
        isRefreshing(true)
        val selected = screenState.value.selectedFocusArea

        tryToCall(
            block = onGetCommunityWorkoutsByFocusArea(selected),
            onSuccess = { list -> onRefreshSuccess(selected, list) },
            onError = ::onRefreshError
        )
    }

    override fun onRetryClicked() {
        val selected = screenState.value.selectedFocusArea
        if (selected == WorkoutScreenState.FocusAreaUiState.ALL) loadAllCommunityWorkouts()
        else loadCommunityWorkoutsByFocusArea(selected)
    }

    private fun onLoading() = updateState {
        it.copy(
            screenStatus = WorkoutScreenState.ScreenStatus.LOADING,
            errorMessage = null
        )
    }

    private fun isRefreshing(isRefreshing: Boolean) = updateState {
        it.copy(
            isRefreshing = isRefreshing,
            errorMessage = if (isRefreshing) null else it.errorMessage
        )
    }

    private fun onRefreshSuccess(
        selected: WorkoutScreenState.FocusAreaUiState,
        list: List<WorkoutSuggested>
    ) {
        if (screenState.value.selectedFocusArea != selected) {
            isRefreshing(false)
            return
        }

        updateState {
            it.copy(
                allWorkouts = list.map { w -> w.toUiState() },
                isRefreshing = false,
                errorMessage = null
            )
        }
    }

    private fun onRefreshError(t: Throwable) = updateState {
        it.copy(
            isRefreshing = false,
            errorMessage = t.message ?: "Failed to refresh community workouts"
        )
    }

    private fun onGetCommunityWorkoutsByFocusArea(
        focusArea: WorkoutScreenState.FocusAreaUiState
    ): suspend () -> List<WorkoutSuggested> = {
        if (focusArea == WorkoutScreenState.FocusAreaUiState.ALL)
            workoutUseCase.getCommunityWorkouts()
        else
            workoutUseCase.getCommunityWorkoutsByFocusArea(focusArea.toDomain())
    }

}