package com.cairosquad.evolvefit.domain.repository

import com.cairosquad.evolvefit.domain.entity.ConsumedMeal
import com.cairosquad.evolvefit.domain.entity.DailyCalorieSummary
import com.cairosquad.evolvefit.domain.entity.DailyWaterSummary
import com.cairosquad.evolvefit.domain.entity.Meal
import com.cairosquad.evolvefit.domain.entity.SuggestedMeal

interface NutritionRepository {
    suspend fun getSuggestedMeals(): List<SuggestedMeal>
    suspend fun getFavouriteMeals(): List<SuggestedMeal>
    suspend fun addFavouriteMealById(mealId: String)
    suspend fun deleteFavouriteMeal(mealId: String)
    suspend fun getMealHistory(): List<ConsumedMeal>
    suspend fun getConsumedMealsByDate(startDate: String, endDate: String): List<ConsumedMeal>
    suspend fun getMealById(id: String): Meal
    suspend fun saveConsumedMeal(consumedMeal: ConsumedMeal): Boolean
    suspend fun getDailyCalorieSummary(): DailyCalorieSummary
    suspend fun saveConsumedWater(amountLiters: Float): Boolean
    suspend fun getDailyWaterSummary(): DailyWaterSummary
}