package com.cairosquad.evolvefit.entity

data class SuggestedMealDetails(
    val name: String,
    val description: String,
    val calories: Int,
    val carbs: Int,
    val protein: Int,
    val fat: Int,
    val type: MealType,
    val ingredients: List<String>,
    val imageUrl: String,
)