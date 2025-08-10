package com.cairosquad.evolvefit.domain.repository

import com.cairosquad.evolvefit.entity.DailyCalorieSummary
import com.cairosquad.evolvefit.entity.ConsumedMeal
import com.cairosquad.evolvefit.entity.SuggestedMeal

interface MealRepository {
    suspend fun getSuggestedMeals(): List<SuggestedMeal>
    suspend fun getConsumedMealsForToday(): List<ConsumedMeal>
    suspend fun getAllMealsHistory(): List<ConsumedMeal>
    suspend fun addConsumedMeal(consumedMeal: ConsumedMeal): Boolean
    suspend fun getDailyCalorieSummary(): DailyCalorieSummary
}