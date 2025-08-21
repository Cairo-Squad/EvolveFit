package com.cairosquad.evolvefit.repository.workout.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WorkoutHistoryDto(
    @SerialName("createdAt")
    val createdAt: String,
    @SerialName("durationSeconds")
    val durationSeconds: Long,
    @SerialName("exercisesCount")
    val exercisesCount: Int,
    @SerialName("imageUrl")
    val imageUrl: String,
    @SerialName("level")
    val level: String,
    @SerialName("name")
    val name: String
)