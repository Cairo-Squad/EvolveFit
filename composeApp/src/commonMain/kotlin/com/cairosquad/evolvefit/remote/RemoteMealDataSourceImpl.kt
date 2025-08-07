package com.cairosquad.evolvefit.remote

import com.cairosquad.evolvefit.entity.DailySummary
import com.cairosquad.evolvefit.entity.SuggestedMeal
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.contentType
import io.ktor.http.isSuccess

class RemoteMealDataSourceImpl(
    private val httpClient: HttpClient
) : RemoteMealDataSource {

    override suspend fun getSuggestedMeals(): List<SuggestedMeal> {
        return httpClient.get("https://evolve-fit-dev.the-chance.net/nutrition/water")
            .body()
    }

    override suspend fun getMeals(startDate: String, endDate: String): List<MealDto> {
        return httpClient.get("https://evolve-fit-dev.the-chance.net/nutrition/meals") {
            parameter("startDate", startDate)
            parameter("endDate", endDate)
        }.body()
    }

    override suspend fun addMeal(meal: MealRequestDto): Boolean {
        return httpClient.post("https://evolve-fit-dev.the-chance.net/nutrition/meal") {
            contentType(io.ktor.http.ContentType.Application.Json)
            setBody(meal)
        }.status.isSuccess()
    }

    override suspend fun getDailySummary(): CaloriesDto {
        return httpClient.get("https://evolve-fit-dev.the-chance.net/nutrition/calories")
            .body()
    }
}