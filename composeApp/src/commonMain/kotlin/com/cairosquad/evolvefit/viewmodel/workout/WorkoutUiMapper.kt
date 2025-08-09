package com.cairosquad.evolvefit.viewmodel.workout

import com.cairosquad.evolvefit.entity.Workout

fun Workout.toUiState(): WorkoutScreenState.WorkoutUiModel {
    return WorkoutScreenState.WorkoutUiModel(
        id = id,
        title = title,
        duration = duration,
        bodyPart = bodyPart,
        imageUrl = imageUrl
    )
}