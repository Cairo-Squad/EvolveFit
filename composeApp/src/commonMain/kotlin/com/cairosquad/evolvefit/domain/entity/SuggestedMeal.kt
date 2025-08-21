package com.cairosquad.evolvefit.domain.entity

import com.cairosquad.evolvefit.domain.model.MealType

data class SuggestedMeal(
    val id: String,
    val name: String,
    val type: MealType,
    val calories: Int,
    val imageUrl:String
)