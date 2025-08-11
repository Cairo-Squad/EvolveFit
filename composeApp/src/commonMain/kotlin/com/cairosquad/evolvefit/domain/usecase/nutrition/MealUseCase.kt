package com.cairosquad.evolvefit.domain.usecase.nutrition

import com.cairosquad.evolvefit.domain.repository.MealRepository
import com.cairosquad.evolvefit.entity.ConsumedMeal
import com.cairosquad.evolvefit.entity.DailyCalorieSummary
import com.cairosquad.evolvefit.entity.SuggestedMeal
import com.cairosquad.evolvefit.entity.SuggestedMealDetails

class MealUseCase(private val mealRepository: MealRepository) {
    suspend fun getSuggestedMeals(): List<SuggestedMeal> {
        return mealRepository.getSuggestedMeals()
    }

    suspend fun getConsumedMealsToday(sartDate: String, endDate: String): List<ConsumedMeal> {
        val meals = mealRepository.getConsumedMealsToday(sartDate, endDate)
        return meals
    }

    suspend fun getSuggestedMealDetailsById(mealId: String): SuggestedMealDetails {
        return mealRepository.getSuggestedMealDetailsById(mealId)
    }

    suspend fun getAllConsumedMeals(): List<ConsumedMeal> {
        val meals = mealRepository.getAllConsumedMeals()
        return meals
    }

    suspend fun addConsumedMeal(consumedMeal: ConsumedMeal): Boolean {
        return mealRepository.addConsumedMeal(consumedMeal)
    }

    suspend fun getDailyCalorieSummary(): DailyCalorieSummary {
        return mealRepository.getDailyCalorieSummary()
    }
}