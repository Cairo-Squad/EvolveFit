package com.cairosquad.evolvefit.remote.nutrition.data

import com.cairosquad.evolvefit.remote.nutrition.dto.ConsumedMealDto
import com.cairosquad.evolvefit.remote.nutrition.dto.ConsumedMealRequestDto
import com.cairosquad.evolvefit.remote.nutrition.dto.DailyCalorieSummaryDto
import com.cairosquad.evolvefit.remote.nutrition.dto.SuggestedMealDetailsDto
import com.cairosquad.evolvefit.remote.nutrition.dto.SuggestedMealDto
import com.cairosquad.evolvefit.remote.utils.Constants.MEALS_PATH
import com.cairosquad.evolvefit.remote.utils.Constants.NUTRITION_PATH
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess

class RemoteMealDataSourceImpl(
    private val httpClient: HttpClient
) : RemoteMealDataSource {

    override suspend fun getSuggestedMeals(): List<SuggestedMealDto> {
        return httpClient.get("${MEALS_PATH}/suggested")
            .body()
    }

    override suspend fun getSuggestedMealDetailsById(mealId: String): SuggestedMealDetailsDto {
        return httpClient.get("${MEALS_PATH}/get") {
            parameter("mealId", mealId)
        }
            .body()
    }

    override suspend fun getConsumedMealsToday(
        startDate: String,
        endDate: String
    ): List<ConsumedMealDto> {
        return httpClient.get("${NUTRITION_PATH}/meals") {
            parameter("startDate", startDate)
            parameter("endDate", endDate)
        }.body()

    }

    override suspend fun getAllConsumedMeals(
    ): List<ConsumedMealDto> {
        return httpClient.get("${NUTRITION_PATH}/meals").body()
    }

    override suspend fun addConsumedMeal(meal: ConsumedMealRequestDto): Boolean {
        return httpClient.post("${NUTRITION_PATH}/meal") {
            contentType(ContentType.Application.Json)
            setBody(meal)
        }.status.isSuccess()
    }

    override suspend fun getDailyCalorieSummary(): DailyCalorieSummaryDto {
        return httpClient.get("${NUTRITION_PATH}/calories")
            .body()
    }
}