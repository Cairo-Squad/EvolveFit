package com.cairosquad.evolvefit.repository.home.data_source.remote.dto

import com.cairosquad.evolvefit.domain.usecase.home.model.WeeklyProgress
import kotlinx.datetime.LocalDate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeeklyProgressResponse(
    @SerialName("workoutDates")
    val workoutDates: List<String>,
    @SerialName("activityPercentage")
    val activityPercentage: Float,
) {
    fun toEntity(
        startDate: LocalDate,
        endDate: LocalDate
    ): WeeklyProgress {
        return WeeklyProgress(
            weeklyProgressChecks = createWorkoutDatesMap(
                startDate = startDate,
                endDate = endDate,
                workoutDates = workoutDates
            ),
            activityPercentage = (activityPercentage * 100).toInt(),
        )
    }
}
