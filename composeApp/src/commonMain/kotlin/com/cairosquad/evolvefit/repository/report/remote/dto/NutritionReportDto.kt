package com.cairosquad.evolvefit.repository.report.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NutritionReportDto(
    @SerialName("caloriesConsumed")
    val caloriesConsumed: Int,
    @SerialName("totalCalories")
    val totalCalories: Int,
    @SerialName("waterConsumed")
    val waterConsumed: Double
)