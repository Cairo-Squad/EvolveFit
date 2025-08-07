package com.cairosquad.evolvefit.remote

import com.cairosquad.evolvefit.entity.DailySummary
import kotlinx.serialization.Serializable

@Serializable
data class CaloriesDto(
    val totalCalories: Int,
    val caloriesConsumed: Int,
)



fun CaloriesDto.toEntity(): DailySummary {
    return DailySummary(
        totalCalories = totalCalories,
        calorieGoal = caloriesConsumed,
        waterIntake =0.0f,
        waterGoal = 0.0f
    )
}