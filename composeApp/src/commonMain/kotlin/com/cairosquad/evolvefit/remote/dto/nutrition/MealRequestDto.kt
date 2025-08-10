package com.cairosquad.evolvefit.remote.dto.nutrition

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MealRequestDto(
    @SerialName("caloriesConsumed")
    val caloriesConsumed: Int,
    @SerialName("mealName")
    val mealName: String,
    @SerialName("mealType")
    val mealType: String
)