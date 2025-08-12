package com.cairosquad.evolvefit.viewmodel.workout

import com.cairosquad.evolvefit.domain.usecase.workout.ManageWorkoutUseCase
import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel

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
            block = { workoutUseCase.getSuggestedWorkouts() },
            onSuccess = { list ->
                updateState { st -> st.copy(allWorkouts = list.map { it.toUiState() }) }
            },
            onError = { },
        )
    }

    private fun loadWorkoutsByFocusArea(focusAreaUiState: WorkoutScreenState.FocusAreaUiState) {
        tryToCall(
            block = {
                if (focusAreaUiState == WorkoutScreenState.FocusAreaUiState.FULL_BODY) {
                    workoutUseCase.getSuggestedWorkouts()
                } else {
                    workoutUseCase.getWorkoutsByFocusArea(focusAreaUiState.toDomain())
                }
            },
            onSuccess = { list ->
                updateState { st -> st.copy(allWorkouts = list.map { it.toUiState() }) }
            },
            onError = { }
        )
    }

    override fun onSelectFocusArea(focusArea: WorkoutScreenState.FocusAreaUiState) {
        updateState { it.copy(selectedFocusArea = focusArea) }

        if (focusArea == WorkoutScreenState.FocusAreaUiState.FULL_BODY) {
            loadAllWorkouts()
        } else {
            loadWorkoutsByFocusArea(focusArea)
        }
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
}