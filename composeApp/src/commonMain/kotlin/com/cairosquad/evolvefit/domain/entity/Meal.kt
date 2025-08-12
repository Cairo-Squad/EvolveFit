package com.cairosquad.evolvefit.domain.entity

import com.cairosquad.evolvefit.domain.model.MealType

data class Meal(
    val id: String,
    val name: String,
    val description: String,
    val ingredients: List<String>,
    val imageUrl: String,
    val nutrition: Nutrition,
    val type: MealType
) {
    data class Nutrition(
        val caloriesInKcal: Int,
        val fatInGrams: Int,
        val proteinInGrams: Int,
        val carbsInGrams: Int
    )
}