package com.cairosquad.evolvefit.domain.usecase.mealHistory

import com.cairosquad.evolvefit.domain.model.MealType
import com.cairosquad.evolvefit.domain.repository.MealHistoryRepository
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

class ManageMealHistoryUseCase(
    private val mealHistoryRepository: MealHistoryRepository
) {
    suspend fun getMealHistory(): List<MealHistoryItem> {
        return mealHistoryRepository.getMealHistory()
    }
    suspend fun getMealHistoryByDate(date: LocalDate): List<MealHistoryItem> {
        return mealHistoryRepository.getMealHistoryByDate(date)
    }
    suspend fun saveMealHistoryItem(mealHistoryItem: MealHistoryItem) {
        mealHistoryRepository.saveMealHistory(mealHistoryItem)
    }

    data class MealHistoryItem(
        val id: String,
        val name: String,
        val type: MealType,
        val date: LocalDateTime,
        val caloriesConsumed: Int,
    )
}