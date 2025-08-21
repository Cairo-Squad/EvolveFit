package com.cairosquad.evolvefit.repository.nutrition

import com.cairosquad.evolvefit.domain.repository.NutritionRepository
import com.cairosquad.evolvefit.domain.entity.ConsumedMeal
import com.cairosquad.evolvefit.domain.entity.DailyCalorieSummary
import com.cairosquad.evolvefit.domain.entity.DailyWaterSummary
import com.cairosquad.evolvefit.domain.entity.Meal
import com.cairosquad.evolvefit.domain.entity.SuggestedMeal
import com.cairosquad.evolvefit.repository.execption.callDataSource
import com.cairosquad.evolvefit.repository.nutrition.remote.RemoteNutritionDataSource
import com.cairosquad.evolvefit.repository.nutrition.remote.toDomain
import com.cairosquad.evolvefit.repository.nutrition.remote.toDto

class NutritionRepositoryImpl(private val remoteNutritionDataSource: RemoteNutritionDataSource) :
    NutritionRepository {
    override suspend fun getSuggestedMeals(): List<SuggestedMeal> {
        return callDataSource { remoteNutritionDataSource.getSuggestedMeals() }.map {
            it.toDomain()
        }
    }

    override suspend fun getFavouriteMeals(): List<SuggestedMeal> {
        return callDataSource { remoteNutritionDataSource.getFavouriteMeals() }.map {
            it.toDomain()
        }
    }

    override suspend fun addFavouriteMealById(mealId: String) {
        return callDataSource {
            remoteNutritionDataSource.addFavouriteMealById(mealId)
        }
    }

    override suspend fun deleteFavouriteMeal(mealId: String) {
        return callDataSource {
            remoteNutritionDataSource.deleteFavouriteMeal(mealId)
        }
    }

    override suspend fun getMealHistory(): List<ConsumedMeal> {
        return callDataSource { remoteNutritionDataSource.getMealHistory() }.map {
            it.toDomain()
        }
    }

    override suspend fun getConsumedMealsByDate(
        startDate: String,
        endDate: String
    ): List<ConsumedMeal> {
        return callDataSource {
            remoteNutritionDataSource.getConsumedMealsByDate(
                startDate,
                endDate
            )
        }.map {
            it.toDomain()
        }
    }

    override suspend fun getMealById(id: String): Meal {
        return callDataSource { remoteNutritionDataSource.getMealById(id) }
            .toDomain()
    }

    override suspend fun saveConsumedMeal(consumedMeal: ConsumedMeal): Boolean {
        return callDataSource { remoteNutritionDataSource.saveConsumedMeal(consumedMeal.toDto()) }
    }

    override suspend fun getDailyCalorieSummary(): DailyCalorieSummary {
        return callDataSource { remoteNutritionDataSource.getDailyCalorieSummary() }.toDomain()
    }

    override suspend fun saveConsumedWater(amountLiters: Float): Boolean {
        return callDataSource { remoteNutritionDataSource.saveConsumedWater(amountLiters) }
    }

    override suspend fun getDailyWaterSummary(): DailyWaterSummary {
        return callDataSource { remoteNutritionDataSource.getDailyWaterSummary() }.toDomain()
    }
}