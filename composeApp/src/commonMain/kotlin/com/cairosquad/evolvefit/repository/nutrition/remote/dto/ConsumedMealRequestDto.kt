package com.cairosquad.evolvefit.repository.nutrition.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConsumedMealRequestDto(
    @SerialName("caloriesConsumed")
    val consumedCalories: Int,
    @SerialName("mealName")
    val mealName: String,
    @SerialName("mealType")
    val mealType: String
)