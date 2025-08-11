package com.cairosquad.evolvefit.repository

import com.cairosquad.evolvefit.domain.repository.MealRepository
import com.cairosquad.evolvefit.entity.ConsumedMeal
import com.cairosquad.evolvefit.entity.DailyCalorieSummary
import com.cairosquad.evolvefit.entity.SuggestedMeal
import com.cairosquad.evolvefit.entity.SuggestedMealDetails
import com.cairosquad.evolvefit.remote.nutrition.data.RemoteMealDataSource
import com.cairosquad.evolvefit.remote.nutrition.mapper.toDomain
import com.cairosquad.evolvefit.remote.nutrition.mapper.toEntity
import com.cairosquad.evolvefit.remote.nutrition.mapper.toRequestDto

class MealRepositoryImpl(private val remoteMealDataSource: RemoteMealDataSource) : MealRepository {
    override suspend fun getSuggestedMeals(): List<SuggestedMeal> {
        return remoteMealDataSource.getSuggestedMeals().map { it.toDomain() }
    }

    override suspend fun getConsumedMealsToday(
        sartDate: String,
        endDate: String
    ): List<ConsumedMeal> {
        val meals = remoteMealDataSource.getConsumedMealsToday(sartDate, endDate)
            .map { it.toEntity() }
        return meals
    }

    override suspend fun getSuggestedMealDetailsById(mealId: String): SuggestedMealDetails {
        return remoteMealDataSource.getSuggestedMealDetailsById(mealId).toDomain()
    }

    override suspend fun getAllConsumedMeals(): List<ConsumedMeal> {
        val meals = remoteMealDataSource.getAllConsumedMeals()
            .map { it.toEntity() }
        return meals
    }

    override suspend fun addConsumedMeal(consumedMeal: ConsumedMeal): Boolean {
        return remoteMealDataSource.addConsumedMeal(consumedMeal.toRequestDto())
    }

    override suspend fun getDailyCalorieSummary(): DailyCalorieSummary {
        return remoteMealDataSource.getDailyCalorieSummary().toEntity()
    }
}