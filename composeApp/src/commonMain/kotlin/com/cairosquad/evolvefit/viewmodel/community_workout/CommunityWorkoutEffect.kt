package com.cairosquad.evolvefit.viewmodel.community_workout

sealed interface CommunityWorkoutEffect {
    data class NavigateToWorkoutDetails(val workoutId: Long) : CommunityWorkoutEffect
}