package com.cairosquad.evolvefit.entity

data class Workout(
    val id: Long,
    val title: String,
    val duration: String,
    val bodyPart: BodyPart,
    val imageUrl: String
)

data class BodyPart(
    val id: Long,
    val name: String
)