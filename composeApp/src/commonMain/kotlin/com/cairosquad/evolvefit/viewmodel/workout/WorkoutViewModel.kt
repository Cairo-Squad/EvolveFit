package com.cairosquad.evolvefit.viewmodel.workout

import com.cairosquad.evolvefit.domain.usecase.workout.ManageWorkoutsUseCase
import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel

class WorkoutViewModel(
    private val getWorkouts: ManageWorkoutsUseCase,

    ) : BaseViewModel<WorkoutScreenState, WorkoutEffect>(
    WorkoutScreenState()
), WorkoutInteractionListener {
    init {
        loadAllWorkouts()
    }

    private fun loadAllWorkouts() {
        tryToCall(
            block = { getWorkouts.getAllWorkouts() },
            onSuccess = { list ->
                updateState { st -> st.copy(allWorkouts = list.map { it.toUiState() }) }
            },
            onError = { },
        )
    }

    private fun loadWorkoutsByBodyPart(name: String) {
        tryToCall(
            block = { getWorkouts.getWorkoutsByBodyPart(name) },
            onSuccess = { list ->
                updateState { st -> st.copy(allWorkouts = list.map { it.toUiState() }) }
            },
            onError = { }
        )
    }

    override fun onSelectBodyPart(bodyPart: String) {
        updateState { it.copy(selectedBodyPart = bodyPart) }

        if (bodyPart.equals("All", true)) {
            loadAllWorkouts()
        } else {
            loadWorkoutsByBodyPart(bodyPart)
        }
    }

    override fun onClickWorkout(id: Long) {
        sendEffect(WorkoutEffect.NavigateToWorkoutDetails(id))
    }

    override fun onClickAddWorkout() {
        sendEffect(WorkoutEffect.NavigateToCreateWorkout)
    }

    override fun onClickCommunity() {
        sendEffect(WorkoutEffect.NavigateToCommunityWorkout)
    }
}