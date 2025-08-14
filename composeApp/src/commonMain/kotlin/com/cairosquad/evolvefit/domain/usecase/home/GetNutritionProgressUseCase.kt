package com.cairosquad.evolvefit.domain.usecase.home

import com.cairosquad.evolvefit.domain.repository.NutritionRepository
import com.cairosquad.evolvefit.domain.usecase.home.model.NutritionProgress

class GetNutritionProgressUseCase(
    private val nutritionRepository: NutritionRepository
) {
    suspend fun getWaterNutrition(): NutritionProgress<Float> {
        return NutritionProgress(
            goal = nutritionRepository.getWaterGoalInLiters(),
            currentProgress = nutritionRepository.getWaterIntakeInLiters()
        )
    }

    suspend fun getCaloriesNutrition(): NutritionProgress<Int> {
        return NutritionProgress(
            goal = nutritionRepository.getCaloriesGoalInKcal(),
            currentProgress = nutritionRepository.getCaloriesIntakeInKcal()
        )
    }
}