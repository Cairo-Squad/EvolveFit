package com.cairosquad.evolvefit.viewmodel.workout

import androidx.lifecycle.viewModelScope
import com.cairosquad.evolvefit.domain.entity.WorkoutSuggested
import com.cairosquad.evolvefit.domain.usecase.workout.ManageWorkoutUseCase
import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel
import com.cairosquad.evolvefit.viewmodel.workout.WorkoutScreenState.FocusAreaUiState
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.failed_to_load_workouts
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class WorkoutViewModel(
    private val workoutUseCase: ManageWorkoutUseCase,

    ) : BaseViewModel<WorkoutScreenState, WorkoutEffect>(
    WorkoutScreenState()
), WorkoutInteractionListener {
    init {
        loadAllWorkouts()
    }

    private fun loadAllWorkouts() {
        tryToCall(
            onStart = ::onLoading,
            block = workoutUseCase::getSuggestedWorkouts,
            onSuccess = ::onGetSuggestedWorkoutsSuccess,
            onError = ::onGetSuggestedWorkoutError,
        )
    }

    private fun loadWorkoutsByFocusArea(focusAreaUiState: FocusAreaUiState) {
        tryToCall(
            onStart = {
                updateState {
                    it.copy(
                        screenStatus = WorkoutScreenState.ScreenStatus.LOADING,
                        errorMessage = null
                    )
                }
            },
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
        val status = if (workouts.isEmpty()) {
            WorkoutScreenState.ScreenStatus.EMPTY
        } else {
            WorkoutScreenState.ScreenStatus.SUCCESS
        }
        updateState { it ->
            it.copy(
                allWorkouts = workouts.map { it.toUiState() },
                errorMessage = null,
                screenStatus = status
            )
        }
    }

    private fun onGetSuggestedWorkoutError(t: Throwable) {
        updateState {
            it.copy(
                errorMessage = Res.string.failed_to_load_workouts,
                screenStatus = WorkoutScreenState.ScreenStatus.FAIL,
                isItemsLoading = false
            )
        }
    }

    private fun onLoadWorkoutByFocusAreaSuccess(workouts: List<WorkoutSuggested>) {
        val status = if (workouts.isEmpty()) {
            WorkoutScreenState.ScreenStatus.EMPTY
        } else {
            WorkoutScreenState.ScreenStatus.SUCCESS
        }
        updateState { it ->
            it.copy(
                allWorkouts = workouts.map { it.toUiState() },
                errorMessage = null,
                screenStatus = status,
                isItemsLoading = false
            )
        }
    }

    private fun onLoadWorkoutByFocusAreaError(t: Throwable) {
        updateState() {
            it.copy(
                errorMessage = Res.string.failed_to_load_workouts,
                screenStatus = WorkoutScreenState.ScreenStatus.FAIL,
                isItemsLoading = false
            )
        }
    }

    override fun onRefresh() {
        updateState {
            it.copy(isRefreshing = true)
        }
        val selected = screenState.value.selectedFocusArea
        if (selected == FocusAreaUiState.ALL) loadAllWorkouts()
        else loadWorkoutsByFocusArea(selected)
        viewModelScope.launch {
            delay(500L)
            updateState { it.copy(isRefreshing = false) }
        }
    }

    private fun onLoading() {
        updateState {
            it.copy(
                screenStatus = WorkoutScreenState.ScreenStatus.LOADING,
                isItemsLoading = false,
                errorMessage = null
            )
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