package com.cairosquad.evolvefit.repository.report.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WorkoutsByDay(
    @SerialName("day")
    val day: String,
    @SerialName("value")
    val workoutsCount: Int
)