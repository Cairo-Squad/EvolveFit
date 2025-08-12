package com.cairosquad.evolvefit.repository.nutrition.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DailyCalorieSummaryDto(
    @SerialName("caloriesConsumed")
    val consumedCalories: Int,
    @SerialName("totalCalories")
    val totalCalories: Int,
)