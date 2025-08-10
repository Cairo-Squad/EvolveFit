package com.cairosquad.evolvefit.remote

import com.cairosquad.evolvefit.remote.dto.nutrition.DailyWaterIntakeDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.http.contentType
import io.ktor.http.isSuccess

class RemoteWaterIntakeDataSourceImpl(private val client: HttpClient) :
    RemoteWaterIntakeDataSource {
    override suspend fun recordWaterIntake(waterAmount: Float): Boolean {
    return client.post("${link}/water?waterIntake=${waterAmount}") {
            contentType(io.ktor.http.ContentType.Application.Json)
        }.status.isSuccess()
    }

    override suspend fun getDailyWaterIntake(): DailyWaterIntakeDto {
    return client.get("${link}/water").body<DailyWaterIntakeDto>()
    }

    companion object {
        const val link = "https://evolve-fit-dev.the-chance.net/nutrition"
    }
}