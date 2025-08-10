package com.cairosquad.evolvefit.viewmodel.workout

import androidx.lifecycle.viewModelScope
import com.cairosquad.evolvefit.domain.usecase.workout.ManageWorkoutsUseCase
import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel
import kotlinx.coroutines.launch

class WorkoutViewModel(
    private val getWorkouts: ManageWorkoutsUseCase,

    ) : BaseViewModel<WorkoutScreenState, WorkoutEffect>(
    WorkoutScreenState()
), WorkoutInteractionListener {
    init {
        loadAllWorkouts()
    }

    private fun loadAllWorkouts() = viewModelScope.launch {
        val list = getWorkouts.getAllWorkouts()
        updateState { st -> st.copy(allWorkouts = list.map { it.toUiState() }) }
    }

    private fun loadWorkoutsByBodyPart(bodyPartName: String) = viewModelScope.launch {
        val list = getWorkouts.getWorkoutsByBodyPart(bodyPartName)
        updateState { st -> st.copy(allWorkouts = list.map { it.toUiState() }) }
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