package com.cairosquad.evolvefit.domain.repository

import com.cairosquad.evolvefit.entity.DailyWaterSummary

interface WaterIntakeRepository {
    suspend fun addConsumedWater(amountLiters : Float): Boolean
    suspend fun getDailyWaterSummary(): DailyWaterSummary
}