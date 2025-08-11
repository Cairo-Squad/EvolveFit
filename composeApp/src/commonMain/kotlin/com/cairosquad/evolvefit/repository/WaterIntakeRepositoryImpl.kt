package com.cairosquad.evolvefit.repository

import com.cairosquad.evolvefit.domain.repository.WaterIntakeRepository
import com.cairosquad.evolvefit.entity.DailyWaterSummary
import com.cairosquad.evolvefit.remote.nutrition.data.RemoteWaterIntakeDataSource
import com.cairosquad.evolvefit.remote.nutrition.mapper.toDomain


class WaterIntakeRepositoryImpl(private val remoteWaterIntakeDataSource: RemoteWaterIntakeDataSource) :
    WaterIntakeRepository {
    override suspend fun addConsumedWater(amountLiters: Float): Boolean {
        return remoteWaterIntakeDataSource.addConsumedWater(amountLiters)
    }

    override suspend fun getDailyWaterSummary(): DailyWaterSummary {
        return remoteWaterIntakeDataSource.getDailyWaterSummary().toDomain()
    }
}