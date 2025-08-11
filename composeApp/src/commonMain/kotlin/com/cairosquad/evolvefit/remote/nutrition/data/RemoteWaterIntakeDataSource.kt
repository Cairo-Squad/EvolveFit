package com.cairosquad.evolvefit.remote.nutrition.data

import com.cairosquad.evolvefit.remote.nutrition.dto.DailyWaterSummaryDto

interface RemoteWaterIntakeDataSource {
    suspend fun addConsumedWater(amountLiters: Float): Boolean
    suspend fun getDailyWaterSummary(): DailyWaterSummaryDto
}