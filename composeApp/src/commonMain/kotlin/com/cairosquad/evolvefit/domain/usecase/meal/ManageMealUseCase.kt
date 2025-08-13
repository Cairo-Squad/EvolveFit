package com.cairosquad.evolvefit.domain.usecase.meal

import com.cairosquad.evolvefit.domain.entity.Meal
import com.cairosquad.evolvefit.domain.repository.MealRepository

class ManageMealUseCase(
    private val mealRepository: MealRepository
) {
    suspend fun getSuggestedMeals(): List<Meal> {
        return mealRepository.getSuggestedMeals()
    }

    suspend fun getFavoriteMeals(): List<Meal> {
        return mealRepository.getFavoriteMeals()
    }

    suspend fun getMealById(id: String): Meal {
        return mealRepository.getMealById(id)
    }
}