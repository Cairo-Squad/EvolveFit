package com.cairosquad.evolvefit.remote

import com.cairosquad.evolvefit.entity.SuggestedMeal
import com.cairosquad.evolvefit.remote.dto.nutrition.DailyCalorieSummaryDto
import com.cairosquad.evolvefit.remote.dto.nutrition.ConsumedMealDto
import com.cairosquad.evolvefit.remote.dto.nutrition.MealRequestDto
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
        return httpClient.get("${link}/meals/suggested")
            .body()
    }

    override suspend fun getMealsHistoryForToday(startDate: String, endDate: String): List<ConsumedMealDto> {
     return httpClient.get("${link}/meals") {
            parameter("startDate", startDate)
            parameter("endDate", endDate)
        }.body()

    }

    override suspend fun getAllMealsHistory(
        startDate: String,
        endDate: String
    ): List<ConsumedMealDto> {
        return httpClient.get("${link}/meals") {
            parameter("startDate", startDate)
            parameter("endDate", endDate)
        }.body()
    }

    override suspend fun addMeal(meal: MealRequestDto): Boolean {
        return httpClient.post("${link}/meal") {
            contentType(io.ktor.http.ContentType.Application.Json)
            setBody(meal)
        }.status.isSuccess()
    }

    override suspend fun getDailySummary(): DailyCalorieSummaryDto {
        return httpClient.get("${link}/calories")
            .body()
    }
    companion object{
        const val link = "https://evolve-fit-dev.the-chance.net/nutrition"
    }
}