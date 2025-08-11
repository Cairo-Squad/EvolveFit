package com.cairosquad.evolvefit.remote.nutrition.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConsumedMealDto(
    @SerialName("id")
    val id: String,
    @SerialName("userId")
    val userId: String,
    @SerialName("date")
    val date: String,
    @SerialName("caloriesConsumed")
    val caloriesConsumed: Int,
    @SerialName("mealName")
    val mealName: String,
    @SerialName("mealType")
    val mealType: String
)