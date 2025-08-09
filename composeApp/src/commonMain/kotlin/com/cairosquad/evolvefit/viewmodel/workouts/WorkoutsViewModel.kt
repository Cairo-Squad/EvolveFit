package com.cairosquad.evolvefit.viewmodel.workouts

import androidx.lifecycle.viewModelScope
import com.cairosquad.evolvefit.domain.model.BodyPart
import com.cairosquad.evolvefit.domain.usecase.workout.GetAllWorkoutsUseCase
import com.cairosquad.evolvefit.domain.usecase.workout.GetWorkoutsByBodyPartUseCase
import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel
import kotlinx.coroutines.launch

class WorkoutsViewModel(
    private val getAllWorkouts: GetAllWorkoutsUseCase,
    private val getWorkoutsByBodyPart: GetWorkoutsByBodyPartUseCase
) : BaseViewModel<WorkoutsScreenState, WorkoutsEffect>(
    WorkoutsScreenState()
), WorkoutsInteractionListener {
    init {
        loadAllWorkouts()
    }

    private fun loadAllWorkouts() = viewModelScope.launch {
        val list = getAllWorkouts()
        updateState { st -> st.copy(allWorkouts = list.map { it.toUi() }) }
    }

    private fun loadWorkoutsByBodyPart(bodyPart: BodyPart) = viewModelScope.launch {
        val list = getWorkoutsByBodyPart(bodyPart)
        updateState { st -> st.copy(allWorkouts = list.map { it.toUi() }) }
    }

    override fun onSelectBodyPart(bodyPart: BodyPart) {
        updateState { it.copy(selectedBodyPart = bodyPart) }

        if (bodyPart == BodyPart.All) {
            loadAllWorkouts()
        } else {
            loadWorkoutsByBodyPart(bodyPart)
        }
    }

    override fun onClickWorkout(id: Long) {
        sendEffect(WorkoutsEffect.NavigateToWorkoutDetails(id))
    }

    override fun onClickAddWorkout() {
        sendEffect(WorkoutsEffect.NavigateToCreateWorkout)
    }

    override fun onClickCommunity() {
        sendEffect(WorkoutsEffect.NavigateToCommunityWorkout)
    }
}