package com.cairosquad.evolvefit.remote.dto.nutrition

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DailyCalorieSummaryDto(
    @SerialName("totalCalories")
    val totalCalories: Int,
    @SerialName("caloriesConsumed")
    val caloriesConsumed: Int,
)