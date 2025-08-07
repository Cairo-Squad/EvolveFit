package com.cairosquad.evolvefit.domain.repository

import com.cairosquad.evolvefit.entity.DailySummary
import com.cairosquad.evolvefit.entity.Meal
import com.cairosquad.evolvefit.entity.SuggestedMeal

interface MealRepository {
    suspend fun getSuggestedMeals(): List<SuggestedMeal>
    suspend fun getMealHistoryForToday(): List<Meal>
    suspend fun addMeal(meal: Meal): Boolean
    suspend fun getDailySummary(): DailySummary
}