package com.cairosquad.evolvefit.domain.usecases

import com.cairosquad.evolvefit.domain.repository.NutritionWaterRepository
import com.cairosquad.evolvefit.entity.DailyWaterIntake

class WaterIntakeUseCase(private val nutritionWaterRepository: NutritionWaterRepository) {
     suspend fun addWaterIntake(waterAmount : Float): Boolean {
        return nutritionWaterRepository.addWaterIntake(waterAmount)
    }

     suspend fun getDailyWaterIntake(): DailyWaterIntake {
        return nutritionWaterRepository.getTotalWaterIntake()
    }
}