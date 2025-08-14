package com.cairosquad.evolvefit.viewmodel.workoutDetails


sealed class WorkoutDetailsEffect {
    object NavigateToPlayWorkout: WorkoutDetailsEffect()
    object NavigateBack: WorkoutDetailsEffect()
    data class NavigateToShareWithCommunity(val workoutId: String) : WorkoutDetailsEffect()
}