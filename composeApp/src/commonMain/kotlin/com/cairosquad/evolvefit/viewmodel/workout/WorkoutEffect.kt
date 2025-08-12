package com.cairosquad.evolvefit.viewmodel.workout

sealed interface WorkoutEffect {
    object NavigateToCreateWorkout : WorkoutEffect
    object NavigateToCommunityWorkout : WorkoutEffect
    data class NavigateToWorkoutDetails(val workoutId: String) : WorkoutEffect
}