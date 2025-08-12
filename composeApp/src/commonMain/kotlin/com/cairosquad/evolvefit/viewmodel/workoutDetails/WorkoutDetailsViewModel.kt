package com.cairosquad.evolvefit.viewmodel.workoutDetails

import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel

class WorkoutDetailsViewModel(

) : BaseViewModel<WorkoutDetailsScreenState, WorkoutDetailsEffect>(
    WorkoutDetailsScreenState()
), WorkoutDetailsInteractionListener {
    override fun onBackClick() {
        TODO("Not yet implemented")
    }

    override fun onShareClick() {
        TODO("Not yet implemented")
    }

    override fun onAddToFavoriteClick() {
        TODO("Not yet implemented")
    }

    override fun onExerciseClick(exerciseName: String) {
        TODO("Not yet implemented")
    }

    override fun onExerciseBottomSheetDismiss() {
        TODO("Not yet implemented")
    }

    override fun onStartWorkoutClick() {
        TODO("Not yet implemented")
    }

}