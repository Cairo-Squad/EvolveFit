package com.cairosquad.evolvefit.viewmodel.play_workout

sealed class PlayWorkoutEffect {
    data object NavigateBackToApp : PlayWorkoutEffect()
    data object NavigateBack : PlayWorkoutEffect()
}