package com.cairosquad.evolvefit.viewmodel.community_workout

sealed interface CommunityWorkoutEffect {
    data object NavigateBack : CommunityWorkoutEffect
    data class NavigateToWorkoutDetails(val workoutId: String) : CommunityWorkoutEffect
}