package com.cairosquad.evolvefit.repository.report.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WorkoutReportDto(
    @SerialName("topFocusAreas")
    val topFocusAreas: List<TopFocusArea>,
    @SerialName("totalTimeSpentByDay")
    val totalTimeSpentByDay: List<TotalTimeSpentByDay>,
    @SerialName("totalTimeSpentSeconds")
    val totalTimeSpentSeconds: Long,
    @SerialName("totalWorkouts")
    val totalWorkouts: Int,
    @SerialName("workoutsByDay")
    val workoutsByDay: List<WorkoutsByDay>
)