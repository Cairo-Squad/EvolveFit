package com.cairosquad.evolvefit.viewmodel.workout_history

sealed class WorkoutHistoryEffect {
    data object NavigateBack : WorkoutHistoryEffect()
}