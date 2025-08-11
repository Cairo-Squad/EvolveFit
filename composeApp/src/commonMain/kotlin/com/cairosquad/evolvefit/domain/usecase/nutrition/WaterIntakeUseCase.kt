package com.cairosquad.evolvefit.domain.usecase.nutrition

import com.cairosquad.evolvefit.domain.repository.WaterIntakeRepository
import com.cairosquad.evolvefit.entity.DailyWaterSummary

class WaterIntakeUseCase(private val waterIntakeRepository: WaterIntakeRepository) {
    suspend fun addConsumedWater(waterAmount: Float): Boolean {
        return waterIntakeRepository.addConsumedWater(waterAmount)
    }

    suspend fun getDailyWaterSummary(): DailyWaterSummary {
        return waterIntakeRepository.getDailyWaterSummary()
    }
}