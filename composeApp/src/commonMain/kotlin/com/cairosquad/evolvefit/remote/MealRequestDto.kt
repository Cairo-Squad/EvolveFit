package com.cairosquad.evolvefit.remote

import com.cairosquad.evolvefit.entity.Meal

@kotlinx.serialization.Serializable
data class MealRequestDto(
    val caloriesConsumed: Int,
    val mealName: String,
    val mealType: String
)
fun Meal.toRequestDto(): MealRequestDto {
    return MealRequestDto(
        mealName = name,
        mealType = type.name,
        caloriesConsumed = calories
    )
}