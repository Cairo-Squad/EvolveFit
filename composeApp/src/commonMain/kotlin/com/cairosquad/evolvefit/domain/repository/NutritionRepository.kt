package com.cairosquad.evolvefit.domain.repository

interface NutritionRepository {
    suspend fun getWaterIntakeInLiters(): Float
    suspend fun getWaterGoalInLiters(): Float
    suspend fun getCaloriesIntakeInKcal(): Int
    suspend fun getCaloriesGoalInKcal(): Int
}