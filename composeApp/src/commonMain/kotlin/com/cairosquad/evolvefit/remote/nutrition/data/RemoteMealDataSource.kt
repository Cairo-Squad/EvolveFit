package com.cairosquad.evolvefit.remote.nutrition.data

import com.cairosquad.evolvefit.remote.nutrition.dto.ConsumedMealDto
import com.cairosquad.evolvefit.remote.nutrition.dto.ConsumedMealRequestDto
import com.cairosquad.evolvefit.remote.nutrition.dto.DailyCalorieSummaryDto
import com.cairosquad.evolvefit.remote.nutrition.dto.SuggestedMealDetailsDto
import com.cairosquad.evolvefit.remote.nutrition.dto.SuggestedMealDto

interface RemoteMealDataSource {
    suspend fun getSuggestedMeals(): List<SuggestedMealDto>
    suspend fun getSuggestedMealDetailsById(mealId: String): SuggestedMealDetailsDto
    suspend fun getConsumedMealsToday(startDate: String, endDate: String): List<ConsumedMealDto>
    suspend fun getAllConsumedMeals(): List<ConsumedMealDto>
    suspend fun addConsumedMeal(consumedMealRequestDto: ConsumedMealRequestDto): Boolean
    suspend fun getDailyCalorieSummary(): DailyCalorieSummaryDto
}