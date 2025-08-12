package com.cairosquad.evolvefit.domain.repository

import com.cairosquad.evolvefit.domain.entity.Meal

interface MealRepository {
    suspend fun getSuggestedMeals(): List<Meal>
    suspend fun getFavoriteMeals(): List<Meal>
    suspend fun getMealById(id: String): Meal
}