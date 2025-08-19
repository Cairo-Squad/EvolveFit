package com.cairosquad.evolvefit.domain.entity.nutrition

data class SuggestedMeal(
    val id: String,
    val name: String,
    val type: MealType,
    val calories: Int,
    val imageUrl:String
)