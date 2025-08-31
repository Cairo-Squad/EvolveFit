package com.cairosquad.evolvefit.domain.repository

import com.cairosquad.evolvefit.domain.model.MealHistoryItem
import kotlinx.datetime.LocalDate

interface MealHistoryRepository {
    suspend fun getMealHistory(): List<MealHistoryItem>
    suspend fun getMealHistoryByDate(date: LocalDate): List<MealHistoryItem>
    suspend fun saveMealHistory(mealHistory: MealHistoryItem)
}