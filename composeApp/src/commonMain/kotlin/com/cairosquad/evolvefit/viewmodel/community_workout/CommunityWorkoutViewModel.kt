package com.cairosquad.evolvefit.viewmodel.community_workout

import androidx.lifecycle.viewModelScope
import com.cairosquad.evolvefit.domain.usecase.workout.ManageWorkoutsUseCase
import com.cairosquad.evolvefit.entity.BodyPart
import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel
import com.cairosquad.evolvefit.viewmodel.workout.WorkoutScreenState
import com.cairosquad.evolvefit.viewmodel.workout.toUiState
import kotlinx.coroutines.launch

class CommunityWorkoutViewModel(
    private val getWorkouts: ManageWorkoutsUseCase,
) : BaseViewModel<WorkoutScreenState, CommunityWorkoutEffect>(
    WorkoutScreenState()
), CommunityWorkoutInteractionListener {
    init {
        loadAllWorkouts()
    }

    private fun loadAllWorkouts() = viewModelScope.launch {
        val list = getWorkouts.getAllWorkouts()
        updateState { st -> st.copy(allWorkouts = list.map { it.toUiState() }) }
    }

    private fun loadWorkoutsByBodyPart(name: String) = viewModelScope.launch {
        val list = getWorkouts.getWorkoutsByBodyPart(name)
        updateState { it -> it.copy(allWorkouts = list.map { it.toUiState() }) }
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
}