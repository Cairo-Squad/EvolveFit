package com.cairosquad.evolvefit.domain.repository

import com.cairosquad.evolvefit.entity.nutrition.ConsumedMeal
import com.cairosquad.evolvefit.entity.nutrition.DailyCalorieSummary
import com.cairosquad.evolvefit.entity.nutrition.DailyWaterSummary
import com.cairosquad.evolvefit.entity.nutrition.Meal
import com.cairosquad.evolvefit.entity.nutrition.SuggestedMeal

interface NutritionRepository {
    suspend fun getSuggestedMeals(): List<SuggestedMeal>
    suspend fun getFavouriteMeals(): List<SuggestedMeal>
    suspend fun getMealHistory(): List<ConsumedMeal>
    suspend fun getConsumedMealsByDate(startDate: String, endDate: String): List<ConsumedMeal>
    suspend fun getMealById(id: String): Meal
    suspend fun saveConsumedMeal(consumedMeal: ConsumedMeal): Boolean
    suspend fun getDailyCalorieSummary(): DailyCalorieSummary
    suspend fun saveConsumedWater(amountLiters: Float): Boolean
    suspend fun getDailyWaterSummary(): DailyWaterSummary
}