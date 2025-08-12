package com.cairosquad.evolvefit.domain.entity

import com.cairosquad.evolvefit.domain.model.MealType
import kotlinx.datetime.LocalDateTime

data class MealHistory(
    val id: String,
    val name: String,
    val type: MealType,
    val date: LocalDateTime,
    val caloriesConsumed: Int,
)
