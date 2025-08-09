package com.cairosquad.evolvefit.viewmodel.workout

import com.cairosquad.evolvefit.entity.BodyPart

data class WorkoutScreenState(
    val selectedBodyPart: BodyPart = BodyPart.All,
    val allWorkouts: List<WorkoutUiModel> = emptyList(),
    val bodyParts: List<BodyPart> = BodyPart.entries
) {
    data class WorkoutUiModel(
        val id: Long,
        val title: String,
        val duration: String,
        val bodyPart: BodyPart,
        val imageUrl: String
    )
}