package com.cairosquad.evolvefit.domain.repository

import com.cairosquad.evolvefit.entity.DailyWaterIntake

interface WaterIntakeRepository {
    suspend fun recordWaterIntake(amountLiters : Float): Boolean
    suspend fun getDailyWaterIntake(): DailyWaterIntake
}