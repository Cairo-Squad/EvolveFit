package com.cairosquad.evolvefit.domain.repository

import com.cairosquad.evolvefit.domain.usecase.mealHistory.ManageMealHistoryUseCase
import kotlinx.datetime.LocalDate

interface MealHistoryRepository {
    suspend fun getMealHistory(): List<ManageMealHistoryUseCase.MealHistoryItem>
    suspend fun getMealHistoryByDate(date: LocalDate): List<ManageMealHistoryUseCase.MealHistoryItem>
    suspend fun saveMealHistory(mealHistory: ManageMealHistoryUseCase.MealHistoryItem)
}