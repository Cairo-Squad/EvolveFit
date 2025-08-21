package com.cairosquad.evolvefit.domain.usecase.home

import com.cairosquad.evolvefit.domain.repository.NutritionRepository
import com.cairosquad.evolvefit.domain.model.NutritionProgress

class GetNutritionProgressUseCase(
    private val nutritionRepository: NutritionRepository
) {
    suspend fun getWaterNutrition(): NutritionProgress<Float> {
        val dailyWaterSummary =  nutritionRepository.getDailyWaterSummary()
        return NutritionProgress(
            goal =dailyWaterSummary.totalWater ,
            currentProgress = dailyWaterSummary.consumedWater
        )
    }

    suspend fun getCaloriesNutrition(): NutritionProgress<Int> {
        val dailyCalorieSummary =  nutritionRepository.getDailyCalorieSummary()
        return NutritionProgress(
            goal = dailyCalorieSummary.totalCalories,
            currentProgress =  dailyCalorieSummary.consumedCalories
        )
    }
}