package com.cairosquad.evolvefit.entity.nutrition

import com.cairosquad.evolvefit.entity.nutrition.MealType

data class ConsumedMeal(
    val id: String,
    val name: String,
    val type: MealType,
    val dateTime: String,
    val calories: Int,
)