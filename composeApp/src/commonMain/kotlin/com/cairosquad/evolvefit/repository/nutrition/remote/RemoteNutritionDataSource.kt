package com.cairosquad.evolvefit.repository.nutrition.remote

import com.cairosquad.evolvefit.repository.nutrition.dto.ConsumedMealDto
import com.cairosquad.evolvefit.repository.nutrition.dto.ConsumedMealRequestDto
import com.cairosquad.evolvefit.repository.nutrition.dto.DailyCalorieSummaryDto
import com.cairosquad.evolvefit.repository.nutrition.dto.DailyWaterSummaryDto
import com.cairosquad.evolvefit.repository.nutrition.dto.MealDto
import com.cairosquad.evolvefit.repository.nutrition.dto.SuggestedMealDto

interface RemoteNutritionDataSource {
    suspend fun getSuggestedMeals(): List<SuggestedMealDto>
    suspend fun getFavouriteMeals(): List<SuggestedMealDto>
    suspend fun getMealHistory(): List<ConsumedMealDto>
    suspend fun getConsumedMealsByDate(startDate: String, endDate: String): List<ConsumedMealDto>
    suspend fun getMealById(id: String): MealDto
    suspend fun saveConsumedMeal(consumedMealRequestDto: ConsumedMealRequestDto): Boolean
    suspend fun getDailyCalorieSummary(): DailyCalorieSummaryDto
    suspend fun saveConsumedWater(amountLiters: Float): Boolean
    suspend fun getDailyWaterSummary(): DailyWaterSummaryDto
}