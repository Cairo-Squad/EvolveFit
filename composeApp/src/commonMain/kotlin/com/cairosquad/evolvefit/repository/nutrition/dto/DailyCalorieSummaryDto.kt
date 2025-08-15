package com.cairosquad.evolvefit.repository.nutrition.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DailyCalorieSummaryDto(
    @SerialName("totalCalories")
    val totalCalories: Int,
    @SerialName("caloriesConsumed")
    val consumedCalories: Int ,
)