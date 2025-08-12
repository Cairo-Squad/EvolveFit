package com.cairosquad.evolvefit.viewmodel.workoutDetails


sealed class WorkoutDetailsEffect {
    object NavigateToPlayWorkout: WorkoutDetailsEffect()
    object NavigateBack: WorkoutDetailsEffect()

}