package com.cairosquad.evolvefit.viewmodel.workoutHistory

sealed class WorkoutHistoryEffect {
    data object NavigateBack : WorkoutHistoryEffect()
}