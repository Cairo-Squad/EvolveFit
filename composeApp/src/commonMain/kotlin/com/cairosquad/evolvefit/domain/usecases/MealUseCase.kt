package com.cairosquad.evolvefit.domain.usecases

import com.cairosquad.evolvefit.domain.repository.MealRepository
import com.cairosquad.evolvefit.entity.DailySummary
import com.cairosquad.evolvefit.entity.Meal
import com.cairosquad.evolvefit.entity.SuggestedMeal

class MealUseCase( private val mealRepository: MealRepository) {
    suspend  fun getSuggestedMeals(): List<SuggestedMeal> {
        return mealRepository.getSuggestedMeals()
    }
    suspend  fun getMealHistoryForToday(): List<Meal> {
        return mealRepository.getMealHistoryForToday()
    }
    suspend  fun addMeal(meal: Meal): Boolean {
        return mealRepository.addMeal(meal)
    }
    suspend  fun getDailySummary(): DailySummary {
        return mealRepository.getDailySummary()
    }
}