package com.cairosquad.evolvefit.repository.nutrition

import com.cairosquad.evolvefit.domain.repository.NutritionRepository
import com.cairosquad.evolvefit.entity.nutrition.ConsumedMeal
import com.cairosquad.evolvefit.entity.nutrition.DailyCalorieSummary
import com.cairosquad.evolvefit.entity.nutrition.DailyWaterSummary
import com.cairosquad.evolvefit.entity.nutrition.Meal
import com.cairosquad.evolvefit.entity.nutrition.SuggestedMeal
import com.cairosquad.evolvefit.repository.nutrition.remote.RemoteNutritionDataSource
import com.cairosquad.evolvefit.repository.nutrition.remote.toDomain
import com.cairosquad.evolvefit.repository.nutrition.remote.toDto
import com.cairosquad.evolvefit.repository.utils.safeCallDataSource

class NutritionRepositoryImpl(private val remoteNutritionDataSource: RemoteNutritionDataSource) :
    NutritionRepository {
    override suspend fun getSuggestedMeals(): List<SuggestedMeal> {
        return safeCallDataSource { remoteNutritionDataSource.getSuggestedMeals() }.map {
            it.toDomain()
        }
    }

    override suspend fun getFavouriteMeals(): List<SuggestedMeal> {
        return safeCallDataSource { remoteNutritionDataSource.getFavouriteMeals() }.map {
            it.toDomain()
        }
    }

    override suspend fun getMealHistory(): List<ConsumedMeal> {
        return safeCallDataSource { remoteNutritionDataSource.getMealHistory() }.map {
            it.toDomain()
        }
    }

    override suspend fun getConsumedMealsByDate(
        startDate: String,
        endDate: String
    ): List<ConsumedMeal> {
        return safeCallDataSource {
            remoteNutritionDataSource.getConsumedMealsByDate(
                startDate,
                endDate
            )
        }.map {
            it.toDomain()
        }
    }

    override suspend fun getMealById(id: String): Meal {
        return safeCallDataSource { remoteNutritionDataSource.getMealById(id) }
            .toDomain()
    }

    override suspend fun saveConsumedMeal(consumedMeal: ConsumedMeal): Boolean {
        return safeCallDataSource { remoteNutritionDataSource.saveConsumedMeal(consumedMeal.toDto()) }
    }

    override suspend fun getDailyCalorieSummary(): DailyCalorieSummary {
        return safeCallDataSource { remoteNutritionDataSource.getDailyCalorieSummary() }.toDomain()
    }

    override suspend fun saveConsumedWater(amountLiters: Float): Boolean {
        return safeCallDataSource { remoteNutritionDataSource.saveConsumedWater(amountLiters) }
    }

    override suspend fun getDailyWaterSummary(): DailyWaterSummary {
        return safeCallDataSource { remoteNutritionDataSource.getDailyWaterSummary() }.toDomain()
    }
}