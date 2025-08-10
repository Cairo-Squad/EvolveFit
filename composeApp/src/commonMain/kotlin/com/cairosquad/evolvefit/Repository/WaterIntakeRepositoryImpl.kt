package com.cairosquad.evolvefit.repository

import com.cairosquad.evolvefit.domain.repository.WaterIntakeRepository
import com.cairosquad.evolvefit.entity.DailyWaterIntake
import com.cairosquad.evolvefit.remote.RemoteWaterIntakeDataSource
import com.cairosquad.evolvefit.remote.mapper.toDomain


class WaterIntakeRepositoryImpl(private val remote: RemoteWaterIntakeDataSource) :
    WaterIntakeRepository {
    override suspend fun recordWaterIntake(amountLiters : Float): Boolean {
        return remote.recordWaterIntake(amountLiters)
    }

    override suspend fun getDailyWaterIntake(): DailyWaterIntake {
        return remote.getDailyWaterIntake().toDomain()
    }
}