package com.cairosquad.evolvefit.domain.usecases

import com.cairosquad.evolvefit.domain.repository.MealRepository
import com.cairosquad.evolvefit.entity.DailyCalorieSummary
import com.cairosquad.evolvefit.entity.ConsumedMeal
import com.cairosquad.evolvefit.entity.SuggestedMeal

class MealUseCase( private val mealRepository: MealRepository) {
    suspend  fun getSuggestedMeals(): List<SuggestedMeal> {
        return mealRepository.getSuggestedMeals()
    }
    suspend  fun getConsumedMealsForToday(): List<ConsumedMeal> {
        val meals= mealRepository.getConsumedMealsForToday()
        return meals
    }
    suspend  fun getAllMealsHistory(): List<ConsumedMeal> {
        val meals= mealRepository.getAllMealsHistory()
        return meals
    }
    suspend  fun addMeal(consumedMeal: ConsumedMeal): Boolean {
        return mealRepository.addConsumedMeal(consumedMeal)
    }
    suspend  fun getDailyCalorieSummary(): DailyCalorieSummary {
        return mealRepository.getDailyCalorieSummary()
    }
}