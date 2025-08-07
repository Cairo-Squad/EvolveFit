package com.cairosquad.evolvefit.entity

data class Meal(
    val id: Long,
    val name: String,
    val type: MealType,
    val dateTime: String,
    val calories: Int,
)