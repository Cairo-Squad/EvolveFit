package com.cairosquad.evolvefit.domain.repository

import com.cairosquad.evolvefit.domain.entity.Meal
import com.cairosquad.evolvefit.domain.entity.MealHistory
import kotlinx.datetime.LocalDate

interface NutritionRepository {
    suspend fun getSuggestedMeals(): List<Meal>
    suspend fun getFavoriteMeals(): List<Meal>
    suspend fun getMealHistory(): List<MealHistory>
    suspend fun getMealHistoryByDate(date: LocalDate): List<MealHistory>
    suspend fun getMealById(id: String): Meal
    suspend fun saveMealHistory(mealHistory: MealHistory)
    suspend fun getWaterIntake(): Float
    suspend fun getCaloriesIntake(): Int
    suspend fun getWaterGoal(): Float
    suspend fun getCaloriesGoal(): Int
}