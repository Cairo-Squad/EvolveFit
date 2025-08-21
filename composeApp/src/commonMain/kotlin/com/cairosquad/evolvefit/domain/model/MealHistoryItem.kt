package com.cairosquad.evolvefit.domain.model

import kotlinx.datetime.LocalDateTime

data class MealHistoryItem(
    val id: String,
    val name: String,
    val type: MealType,
    val date: LocalDateTime,
    val caloriesConsumed: Int,
)