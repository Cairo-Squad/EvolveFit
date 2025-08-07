package com.cairosquad.evolvefit.remote

import com.cairosquad.evolvefit.entity.DailySummary
import com.cairosquad.evolvefit.entity.SuggestedMeal

interface RemoteMealDataSource {
    suspend fun getSuggestedMeals(): List<SuggestedMeal>
    suspend fun getMeals(startDate: String, endDate: String): List<MealDto>
    suspend fun addMeal(meal: MealRequestDto): Boolean
    suspend fun getDailySummary(): CaloriesDto
}