package com.cairosquad.evolvefit.entity

data class ConsumedMeal(
    val id: String,
    val name: String,
    val type: MealType,
    val dateTime: String,
    val calories: Int,
)