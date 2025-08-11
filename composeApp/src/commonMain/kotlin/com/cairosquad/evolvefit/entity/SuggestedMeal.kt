package com.cairosquad.evolvefit.entity

data class SuggestedMeal(
    val id: String,
    val name: String,
    val calories: Int,
    val imageUrl: String,
    val type: MealType
)
