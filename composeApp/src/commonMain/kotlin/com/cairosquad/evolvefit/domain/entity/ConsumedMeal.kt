package com.cairosquad.evolvefit.domain.entity

import com.cairosquad.evolvefit.domain.model.MealType

data class ConsumedMeal(
    val id: String,
    val name: String,
    val type: MealType,
    val dateTime: String,
    val calories: Int,
)