package com.cairosquad.evolvefit.viewmodel.community_workout

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
            block = { workoutUseCase.getWorkoutsByFocusArea(focusAreaUiState.toDomain()) },
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
        sendEffect(CommunityWorkoutEffect.NavigateToWorkoutDetails(id))
    }

    override fun onClickBack() {
        sendEffect(CommunityWorkoutEffect.NavigateBack)
    }

    override fun getCommunityWorkout() {
        TODO("Not yet implemented")
    }

}