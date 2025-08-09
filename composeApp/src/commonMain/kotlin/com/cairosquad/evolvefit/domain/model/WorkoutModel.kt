package com.cairosquad.evolvefit.domain.model

data class WorkoutModel(
    val id: Long,
    val title: String,
    val duration: String,
    val bodyPart: BodyPart,
    val imageUrl: String
)

enum class BodyPart(val displayName: String) {
    All("All"),
    Arm("Arm"),
    Chest("Chest"),
    Back("Back"),
    Shoulder("Shoulder")
}