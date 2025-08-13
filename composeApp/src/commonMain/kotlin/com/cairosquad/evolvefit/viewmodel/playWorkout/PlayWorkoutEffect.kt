package com.cairosquad.evolvefit.viewmodel.playWorkout

sealed class PlayWorkoutEffect {
    data object NavigateBackToApp : PlayWorkoutEffect()
    data object NavigateBack : PlayWorkoutEffect()
}