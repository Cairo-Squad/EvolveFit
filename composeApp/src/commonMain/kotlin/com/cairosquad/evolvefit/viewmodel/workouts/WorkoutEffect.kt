package com.cairosquad.evolvefit.viewmodel.workouts

sealed interface WorkoutsEffect {
    object NavigateToCreateWorkout : WorkoutsEffect
    object NavigateToCommunityWorkout : WorkoutsEffect
    data class NavigateToWorkoutDetails(val workoutId: Long) : WorkoutsEffect
}