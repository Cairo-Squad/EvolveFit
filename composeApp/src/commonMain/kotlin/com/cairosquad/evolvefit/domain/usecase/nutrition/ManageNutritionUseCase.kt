package com.cairosquad.evolvefit.domain.usecase.nutrition

import com.cairosquad.evolvefit.domain.entity.Meal
import com.cairosquad.evolvefit.domain.entity.MealHistory
import com.cairosquad.evolvefit.domain.repository.NutritionRepository
import kotlinx.datetime.LocalDate

class ManageNutritionUseCase(
    private val nutritionRepository: NutritionRepository
) {
    suspend fun getSuggestedMeals(): List<Meal> {
        return nutritionRepository.getSuggestedMeals()
    }
    suspend fun getFavoriteMeals(): List<Meal> {
        return nutritionRepository.getFavoriteMeals()
    }
    suspend fun getMealHistory(): List<MealHistory> {
        return nutritionRepository.getMealHistory()
    }
    suspend fun getMealHistoryByDate(date: LocalDate): List<MealHistory> {
        return nutritionRepository.getMealHistoryByDate(date)
    }
    suspend fun getMealById(id: String): Meal {
        return nutritionRepository.getMealById(id)
    }
    suspend fun saveMealHistory(mealHistory: MealHistory) {
        nutritionRepository.saveMealHistory(mealHistory)
    }
    suspend fun getWaterIntake(): Float {
        return nutritionRepository.getWaterIntake()
    }
    suspend fun getCaloriesIntake(): Int {
        return nutritionRepository.getCaloriesIntake()
    }
    suspend fun getWaterGoal(): Float {
        return nutritionRepository.getWaterGoal()
    }
    suspend fun getCaloriesGoal(): Int {
        return nutritionRepository.getCaloriesGoal()
    }
}