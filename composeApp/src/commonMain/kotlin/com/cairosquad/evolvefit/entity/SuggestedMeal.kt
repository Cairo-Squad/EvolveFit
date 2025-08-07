package com.cairosquad.evolvefit.entity

data class SuggestedMeal(
    val id: Long,
    val name: String,
    val calories: Int,
    val imageUrl: String,
    val type: MealType
)
