package com.cairosquad.evolvefit.domain.repository

import com.cairosquad.evolvefit.entity.ConsumedMeal
import com.cairosquad.evolvefit.entity.DailyCalorieSummary
import com.cairosquad.evolvefit.entity.SuggestedMeal
import com.cairosquad.evolvefit.entity.SuggestedMealDetails

interface MealRepository {
    suspend fun getSuggestedMeals(): List<SuggestedMeal>
    suspend fun getConsumedMealsToday(sartDate: String, endDate: String): List<ConsumedMeal>
    suspend fun getSuggestedMealDetailsById(mealId: String): SuggestedMealDetails
    suspend fun getAllConsumedMeals(): List<ConsumedMeal>
    suspend fun addConsumedMeal(consumedMeal: ConsumedMeal): Boolean
    suspend fun getDailyCalorieSummary(): DailyCalorieSummary
}