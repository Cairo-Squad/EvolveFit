package com.cairosquad.evolvefit.viewmodel.workouts

import com.cairosquad.evolvefit.domain.model.WorkoutModel

fun WorkoutModel.toUi(): WorkoutsScreenState.WorkoutUiModel {
    return WorkoutsScreenState.WorkoutUiModel(
        id = id,
        title = title,
        duration = duration,
        bodyPart = bodyPart,
        imageUrl = imageUrl
    )
}