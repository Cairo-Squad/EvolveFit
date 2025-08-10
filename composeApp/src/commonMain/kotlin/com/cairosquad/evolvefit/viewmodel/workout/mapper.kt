package com.cairosquad.evolvefit.viewmodel.workout

import com.cairosquad.evolvefit.entity.Workout

fun Workout.toUiState(): WorkoutScreenState.WorkoutUiState {
    return WorkoutScreenState.WorkoutUiState(
        id = id,
        title = title,
        duration = duration,
        bodyPart = bodyPart,
        imageUrl = imageUrl
    )
}