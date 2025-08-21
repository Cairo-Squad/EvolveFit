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
        updateState {
            it.copy(
                screenStatus = WorkoutScreenState.ScreenStatus.LOADING,
                errorMessage = null
            )
        }
        tryToCall(
            block = workoutUseCase::getSuggestedWorkouts,
            onSuccess = ::onGetSuggestedWorkoutsSuccess,
            onError = ::onGetSuggestedWorkoutError,
        )
    }

    private fun loadWorkoutsByFocusArea(focusAreaUiState: FocusAreaUiState) {
        updateState {
            it.copy(
                screenStatus = WorkoutScreenState.ScreenStatus.LOADING,
                errorMessage = null
            )
        }
        tryToCall(
            block = { workoutUseCase.getSuggestedWorkoutsByFocusArea(focusAreaUiState.toDomain()) },
            onSuccess = ::onLoadWorkoutByFocusAreaSuccess,
            onError = ::onLoadWorkoutByFocusAreaError
        )
    }

    override fun onSelectFocusArea(focusArea: FocusAreaUiState) {
        updateState { it.copy(selectedFocusArea = focusArea, errorMessage = null) }

        if (focusArea == FocusAreaUiState.CORE) {
            loadAllWorkouts()
        } else {
            loadWorkoutsByFocusArea(focusArea)
        }
    }

    override fun onRetryClicked() {
        val selected = screenState.value.selectedFocusArea
        if (selected == FocusAreaUiState.CORE) loadAllWorkouts()
        else loadWorkoutsByFocusArea(selected)
    }

    override fun onClickWorkout(id: String) {
        sendEffect(WorkoutEffect.NavigateToWorkoutDetails(id))
    }

    override fun onClickAddWorkout() {
        sendEffect(WorkoutEffect.NavigateToCreateWorkout)
    }

    override fun onClickCommunity() {
        sendEffect(WorkoutEffect.NavigateToCommunityWorkout)
    }

    private fun onGetSuggestedWorkoutsSuccess(workouts: List<WorkoutSuggested>) {
        updateState { st ->
            st.copy(
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
        updateState { st ->
            st.copy(
                allWorkouts = workouts.map { it.toUiState() },
                errorMessage = null, screenStatus = WorkoutScreenState.ScreenStatus.SUCCESS

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
        updateState { it.copy(isRefreshing = true, errorMessage = null) }
        val selected = screenState.value.selectedFocusArea
        tryToCall(
            block = {
                if (selected == FocusAreaUiState.CORE)
                    workoutUseCase.getSuggestedWorkouts()
                else
                    workoutUseCase.getSuggestedWorkoutsByFocusArea(selected.toDomain())
            },
            onSuccess = { list ->
                updateState {
                    it.copy(
                        allWorkouts = list.map { w -> w.toUiState() },
                        isRefreshing = false,
                        errorMessage = null,
                    )
                }
            },
            onError = { t ->
                updateState {
                    it.copy(
                        isRefreshing = false,
                        errorMessage = t.message ?: "Failed to refresh workouts"
                    )
                }
            }
        )
    }
}