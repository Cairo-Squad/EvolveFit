package com.cairosquad.evolvefit.domain.usecases

import com.cairosquad.evolvefit.domain.repository.WaterIntakeRepository
import com.cairosquad.evolvefit.entity.DailyWaterIntake

class WaterIntakeUseCase(private val waterIntakeRepository: WaterIntakeRepository) {
     suspend fun addWaterIntake(waterAmount : Float): Boolean {
        return waterIntakeRepository.recordWaterIntake(waterAmount)
    }

     suspend fun getDailyWaterIntake(): DailyWaterIntake {
        return waterIntakeRepository.getDailyWaterIntake()
    }
}