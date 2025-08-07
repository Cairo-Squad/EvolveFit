package com.cairosquad.evolvefit.viewmodel.workouts

import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel

class WorkoutsViewModel : BaseViewModel<WorkoutsScreenState, WorkoutsEffect>(
    WorkoutsScreenState(
        allWorkouts = listOf(
            WorkoutsScreenState.WorkoutUiModel(1, "Workout 1", "25 min", "Chest"),
            WorkoutsScreenState.WorkoutUiModel(2, "Workout 2", "30 min", "Arm"),
            WorkoutsScreenState.WorkoutUiModel(3, "Workout 3", "20 min", "Back"),
            WorkoutsScreenState.WorkoutUiModel(4, "Workout 4", "35 min", "Shoulder"),
            WorkoutsScreenState.WorkoutUiModel(5, "Workout 5", "40 min", "Chest"),
            WorkoutsScreenState.WorkoutUiModel(6, "Workout 6", "30 min", "Chest"),
        )
    )
), WorkoutsInteractionListener {

    override fun onBodyPartSelected(bodyPart: String) {
        updateState { it.copy(selectedBodyPart = bodyPart) }
    }

    override fun onWorkoutClicked(id: Long) {
        sendEffect(WorkoutsEffect.NavigateToWorkoutDetails(id))
    }

    override fun onAddWorkoutClicked() {
        sendEffect(WorkoutsEffect.NavigateToCreateWorkout)
    }

    override fun onCommunityClicked() {
        sendEffect(WorkoutsEffect.NavigateToCommunityWorkout)
    }
}