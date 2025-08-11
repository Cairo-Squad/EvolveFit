package com.cairosquad.evolvefit.viewmodel.community_workout

import com.cairosquad.evolvefit.domain.usecase.workout.ManageWorkoutsUseCase
import com.cairosquad.evolvefit.entity.BodyPart
import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel
import com.cairosquad.evolvefit.viewmodel.workout.WorkoutScreenState
import com.cairosquad.evolvefit.viewmodel.workout.toUiState

class CommunityWorkoutViewModel(
    private val getWorkouts: ManageWorkoutsUseCase,
) : BaseViewModel<WorkoutScreenState, CommunityWorkoutEffect>(
    WorkoutScreenState()
), CommunityWorkoutInteractionListener {
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

    override fun onSelectBodyPart(bodyPart: BodyPart) {
        updateState { it.copy(selectedBodyPart = bodyPart.name) }

        if (bodyPart.name.equals("All", ignoreCase = true)) {
            loadAllWorkouts()
        } else {
            loadWorkoutsByBodyPart(bodyPart.name)
        }
    }

    override fun onClickWorkout(id: Long) {
        sendEffect(CommunityWorkoutEffect.NavigateToWorkoutDetails(id))
    }

    override fun onClickBack() {
        sendEffect(CommunityWorkoutEffect.NavigateBack)
    }

    override fun getCommunityWorkout() {
        TODO("Not yet implemented")
    }

}