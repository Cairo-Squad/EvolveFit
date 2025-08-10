package com.cairosquad.evolvefit.remote

import com.cairosquad.evolvefit.entity.SuggestedMeal
import com.cairosquad.evolvefit.remote.dto.nutrition.DailyCalorieSummaryDto
import com.cairosquad.evolvefit.remote.dto.nutrition.ConsumedMealDto
import com.cairosquad.evolvefit.remote.dto.nutrition.MealRequestDto

interface RemoteMealDataSource {
    suspend fun getSuggestedMeals(): List<SuggestedMeal>
    suspend fun getMealsHistoryForToday(startDate: String, endDate: String): List<ConsumedMealDto>
    suspend fun getAllMealsHistory(startDate: String, endDate: String): List<ConsumedMealDto>
    suspend fun addMeal(meal: MealRequestDto): Boolean
    suspend fun getDailySummary(): DailyCalorieSummaryDto
}