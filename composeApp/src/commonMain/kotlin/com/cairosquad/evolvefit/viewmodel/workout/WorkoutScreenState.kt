package com.cairosquad.evolvefit.viewmodel.workout

import com.cairosquad.evolvefit.entity.BodyPart

data class WorkoutScreenState(
    val selectedBodyPart: String = "All",
    val allWorkouts: List<WorkoutUiState> = emptyList(),
    val bodyParts: List<BodyPart> = listOf(
        BodyPart(0, "All"),
        BodyPart(1, "Arm"),
        BodyPart(2, "Chest"),
        BodyPart(3, "Back"),
        BodyPart(4, "Shoulder")
    )
) {
    data class WorkoutUiState(
        val id: Long,
        val title: String,
        val duration: String,
        val bodyPart: BodyPart,
        val imageUrl: String
    )
}