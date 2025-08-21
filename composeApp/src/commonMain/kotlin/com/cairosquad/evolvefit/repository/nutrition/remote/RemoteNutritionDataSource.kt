package com.cairosquad.evolvefit.repository.nutrition.remote

import com.cairosquad.evolvefit.repository.nutrition.remote.dto.ConsumedMealDto
import com.cairosquad.evolvefit.repository.nutrition.remote.dto.ConsumedMealRequestDto
import com.cairosquad.evolvefit.repository.nutrition.remote.dto.DailyCalorieSummaryDto
import com.cairosquad.evolvefit.repository.nutrition.remote.dto.DailyWaterSummaryDto
import com.cairosquad.evolvefit.repository.nutrition.remote.dto.FavouriteMealDto
import com.cairosquad.evolvefit.repository.nutrition.remote.dto.MealDto
import com.cairosquad.evolvefit.repository.nutrition.remote.dto.SuggestedMealDto

interface RemoteNutritionDataSource {
    suspend fun getSuggestedMeals(): List<SuggestedMealDto>
    suspend fun getFavouriteMeals(): List<FavouriteMealDto>
    suspend fun addFavouriteMealById(mealId: String)
    suspend fun deleteFavouriteMeal(mealId: String)
    suspend fun getMealHistory(): List<ConsumedMealDto>
    suspend fun getConsumedMealsByDate(startDate: String, endDate: String): List<ConsumedMealDto>
    suspend fun getMealById(id: String): MealDto
    suspend fun saveConsumedMeal(consumedMealRequestDto: ConsumedMealRequestDto): Boolean
    suspend fun getDailyCalorieSummary(): DailyCalorieSummaryDto
    suspend fun saveConsumedWater(amountLiters: Float): Boolean
    suspend fun getDailyWaterSummary(): DailyWaterSummaryDto
}