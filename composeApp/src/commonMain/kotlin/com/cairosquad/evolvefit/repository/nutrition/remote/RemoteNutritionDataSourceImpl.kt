package com.cairosquad.evolvefit.repository.nutrition.remote

import com.cairosquad.evolvefit.repository.nutrition.dto.ConsumedMealDto
import com.cairosquad.evolvefit.repository.nutrition.dto.ConsumedMealRequestDto
import com.cairosquad.evolvefit.repository.nutrition.dto.DailyCalorieSummaryDto
import com.cairosquad.evolvefit.repository.nutrition.dto.DailyWaterSummaryDto
import com.cairosquad.evolvefit.repository.nutrition.dto.MealDto
import com.cairosquad.evolvefit.repository.nutrition.dto.SuggestedMealDto
import com.cairosquad.evolvefit.repository.nutrition.utils.MealConstants.MEALS_PATH
import com.cairosquad.evolvefit.repository.nutrition.utils.MealConstants.NUTRITION_PATH
import com.cairosquad.evolvefit.repository.execption.callApi
import com.cairosquad.evolvefit.repository.nutrition.dto.FavouriteMealDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess

class RemoteNutritionDataSourceImpl(
    private val httpClient: HttpClient
) : RemoteNutritionDataSource {

    override suspend fun getSuggestedMeals(): List<SuggestedMealDto> {
        return callApi<List<SuggestedMealDto>> {
            httpClient.get("${MEALS_PATH}/suggested")
                .body()
        }
    }

    override suspend fun getFavouriteMeals(): List<FavouriteMealDto> {
        return callApi<List<FavouriteMealDto>> {
            httpClient.get(FAVORITE_MEAL)
                .body()
        }
    }

    override suspend fun addFavouriteMealById(mealId: String) {
        return callApi {
            httpClient.post(FAVORITE_MEAL) {
                parameter("mealId", mealId)
            }
                .body()
        }
    }

    override suspend fun deleteFavouriteMeal(mealId: String) {
        return callApi {
            httpClient.delete(FAVORITE_MEAL) {
                parameter("mealId", mealId)
                contentType(ContentType.Application.Json)
            }.body()
        }
    }

    override suspend fun getMealHistory(
        startDate: String,
        endDate: String
    ): List<ConsumedMealDto> {
        return callApi<List<ConsumedMealDto>> {
            httpClient.get("${NUTRITION_PATH}/meals") {
                parameter("startDate", startDate)
                parameter("endDate", endDate)

            }.body()
        }
    }

    override suspend fun getConsumedMealsByDate(
        startDate: String,
        endDate: String
    ): List<ConsumedMealDto> {
        return callApi<List<ConsumedMealDto>> {
            httpClient.get("${NUTRITION_PATH}/meals") {
                parameter("startDate", startDate)
                parameter("endDate", endDate)
            }.body()
        }
    }

    override suspend fun getMealById(id: String): MealDto {
        return callApi<MealDto> {
            httpClient.get("${MEALS_PATH}/get") {
                parameter("mealId", id)
            }
                .body()
        }
    }

    override suspend fun saveConsumedMeal(consumedMealRequestDto: ConsumedMealRequestDto): Boolean {
        return httpClient.post("${NUTRITION_PATH}/meal") {
            contentType(ContentType.Application.Json)
            setBody(consumedMealRequestDto)
        }.status.isSuccess()
    }

    override suspend fun getDailyCalorieSummary(): DailyCalorieSummaryDto {
        return callApi<DailyCalorieSummaryDto> {
            httpClient.get("${NUTRITION_PATH}/calories").body()
        }
    }

    override suspend fun saveConsumedWater(amountLiters: Float): Boolean {
        return httpClient.post("${NUTRITION_PATH}/water") {
            parameter("waterIntake", amountLiters)
        }.status.isSuccess()
    }

    override suspend fun getDailyWaterSummary(): DailyWaterSummaryDto {
        return callApi<DailyWaterSummaryDto> {
            httpClient.get("${NUTRITION_PATH}/water").body()
        }
    }

    companion object {
        private const val FAVORITE_MEAL = "favorite/meal"
    }
}