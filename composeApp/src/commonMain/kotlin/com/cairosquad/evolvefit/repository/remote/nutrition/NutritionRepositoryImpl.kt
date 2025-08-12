package com.cairosquad.evolvefit.repository.remote.nutrition

import com.cairosquad.evolvefit.domain.repository.NutritionRepository
import com.cairosquad.evolvefit.entity.nutrition.ConsumedMeal
import com.cairosquad.evolvefit.entity.nutrition.DailyCalorieSummary
import com.cairosquad.evolvefit.entity.nutrition.DailyWaterSummary
import com.cairosquad.evolvefit.entity.nutrition.Meal
import com.cairosquad.evolvefit.entity.nutrition.SuggestedMeal
import com.cairosquad.evolvefit.remote.nutrition.data.RemoteNutritionDataSource
import com.cairosquad.evolvefit.remote.nutrition.mapper.toDomain
import com.cairosquad.evolvefit.remote.nutrition.mapper.toDto
import com.cairosquad.evolvefit.remote.utils.safeApiCall

class NutritionRepositoryImpl(private val remoteNutritionDataSource: RemoteNutritionDataSource) :
    NutritionRepository {
    override suspend fun getSuggestedMeals(): List<SuggestedMeal> {
        return safeApiCall { remoteNutritionDataSource.getSuggestedMeals() }.map {
            it.toDomain()
        }
    }

    override suspend fun getFavouriteMeals(): List<SuggestedMeal> {
        return safeApiCall { remoteNutritionDataSource.getFavouriteMeals() }.map {
            it.toDomain()
        }
    }

    override suspend fun getMealHistory(): List<ConsumedMeal> {
        return safeApiCall { remoteNutritionDataSource.getMealHistory() }.map {
            it.toDomain()
        }
    }

    override suspend fun getConsumedMealsByDate(
        startDate: String,
        endDate: String
    ): List<ConsumedMeal> {
        return safeApiCall {
            remoteNutritionDataSource.getConsumedMealsByDate(
                startDate,
                endDate
            )
        }.map {
            it.toDomain()
        }
    }

    override suspend fun getMealById(id: String): Meal {
        return safeApiCall { remoteNutritionDataSource.getMealById(id) }
            .toDomain()
    }

    override suspend fun saveConsumedMeal(consumedMeal: ConsumedMeal): Boolean {
        return safeApiCall { remoteNutritionDataSource.saveConsumedMeal(consumedMeal.toDto()) }
    }

    override suspend fun getDailyCalorieSummary(): DailyCalorieSummary {
        return safeApiCall { remoteNutritionDataSource.getDailyCalorieSummary() }.toDomain()
    }

    override suspend fun saveConsumedWater(amountLiters: Float): Boolean {
        return safeApiCall { remoteNutritionDataSource.saveConsumedWater(amountLiters) }
    }

    override suspend fun getDailyWaterSummary(): DailyWaterSummary {
        return safeApiCall { remoteNutritionDataSource.getDailyWaterSummary() }.toDomain()
    }
}