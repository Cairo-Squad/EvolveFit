package com.cairosquad.evolvefit.repository.nutrition

import com.cairosquad.evolvefit.domain.repository.NutritionRepository
import com.cairosquad.evolvefit.domain.entity.ConsumedMeal
import com.cairosquad.evolvefit.domain.entity.DailyCalorieSummary
import com.cairosquad.evolvefit.domain.entity.DailyWaterSummary
import com.cairosquad.evolvefit.domain.entity.Meal
import com.cairosquad.evolvefit.domain.entity.SuggestedMeal
import com.cairosquad.evolvefit.repository.execption.callDataSource
import com.cairosquad.evolvefit.repository.nutrition.remote.NutritionRemoteDataSource
import com.cairosquad.evolvefit.repository.nutrition.remote.toDomain
import com.cairosquad.evolvefit.repository.nutrition.remote.toDto

class NutritionRepositoryImpl(private val nutritionRemoteDataSource: NutritionRemoteDataSource) :
    NutritionRepository {
    override suspend fun getSuggestedMeals(): List<SuggestedMeal> {
        return callDataSource { nutritionRemoteDataSource.getSuggestedMeals() }.map {
            it.toDomain()
        }
    }

    override suspend fun getFavouriteMeals(): List<SuggestedMeal> {
        return callDataSource { nutritionRemoteDataSource.getFavouriteMeals() }.map {
            it.toDomain()
        }
    }

    override suspend fun addFavouriteMealById(mealId: String) {
        return callDataSource {
            nutritionRemoteDataSource.addFavouriteMealById(mealId)
        }
    }

    override suspend fun deleteFavouriteMeal(mealId: String) {
        callDataSource {
            nutritionRemoteDataSource.deleteFavouriteMeal(mealId)
        }
    }

    override suspend fun getMealHistory(startDate: String, endDate: String): List<ConsumedMeal> {
        return callDataSource { nutritionRemoteDataSource.getMealHistory(startDate,endDate) }.map {
            it.toDomain()
        }
    }

    override suspend fun getConsumedMealsByDate(
        startDate: String,
        endDate: String
    ): List<ConsumedMeal> {
        return callDataSource {
            nutritionRemoteDataSource.getConsumedMealsByDate(
                startDate,
                endDate
            )
        }.map {
            it.toDomain()
        }
    }

    override suspend fun getMealById(id: String): Meal {
        return callDataSource { nutritionRemoteDataSource.getMealById(id) }
            .toDomain()
    }

    override suspend fun saveConsumedMeal(consumedMeal: ConsumedMeal): Boolean {
        return callDataSource { nutritionRemoteDataSource.saveConsumedMeal(consumedMeal.toDto()) }
    }

    override suspend fun getDailyCalorieSummary(): DailyCalorieSummary {
        return callDataSource { nutritionRemoteDataSource.getDailyCalorieSummary() }.toDomain()
    }

    override suspend fun saveConsumedWater(amountLiters: Float): Boolean {
        return callDataSource { nutritionRemoteDataSource.saveConsumedWater(amountLiters) }
    }

    override suspend fun getDailyWaterSummary(): DailyWaterSummary {
        return callDataSource { nutritionRemoteDataSource.getDailyWaterSummary() }.toDomain()
    }
}