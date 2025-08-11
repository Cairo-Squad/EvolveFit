package com.cairosquad.evolvefit.remote.nutrition.data

import com.cairosquad.evolvefit.remote.nutrition.dto.DailyWaterSummaryDto
import com.cairosquad.evolvefit.remote.utils.Constants.NUTRITION_PATH
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.http.isSuccess

class RemoteWaterIntakeDataSourceImpl(private val client: HttpClient) :
    RemoteWaterIntakeDataSource {
    override suspend fun addConsumedWater(amountLiters: Float): Boolean {
        return client.post("${NUTRITION_PATH}/water") {
            parameter("waterIntake", amountLiters)
        }.status.isSuccess()
    }

    override suspend fun getDailyWaterSummary(): DailyWaterSummaryDto {
        return client.get("${NUTRITION_PATH}/water").body<DailyWaterSummaryDto>()
    }
}