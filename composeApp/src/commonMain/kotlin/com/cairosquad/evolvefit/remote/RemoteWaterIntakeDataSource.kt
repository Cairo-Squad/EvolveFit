package com.cairosquad.evolvefit.remote

import com.cairosquad.evolvefit.remote.dto.nutrition.DailyWaterIntakeDto

interface RemoteWaterIntakeDataSource {
    suspend fun recordWaterIntake(amountLiters: Float): Boolean
    suspend fun getDailyWaterIntake(): DailyWaterIntakeDto
}