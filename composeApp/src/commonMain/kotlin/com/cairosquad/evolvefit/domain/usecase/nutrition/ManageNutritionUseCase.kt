package com.cairosquad.evolvefit.domain.usecase.nutrition

import com.cairosquad.evolvefit.domain.repository.NutritionRepository

class ManageNutritionUseCase(
    private val nutritionRepository: NutritionRepository
) {
    suspend fun getWaterIntake(): Float {
        return nutritionRepository.getWaterIntakeInLiters()
    }

    suspend fun getCaloriesIntake(): Int {
        return nutritionRepository.getCaloriesIntakeInKcal()
    }

    suspend fun getWaterGoal(): Float {
        return nutritionRepository.getWaterGoalInLiters()
    }

    suspend fun getCaloriesGoal(): Int {
        return nutritionRepository.getCaloriesGoalInKcal()
    }
}