package com.cairosquad.evolvefit.domain.repository

import com.cairosquad.evolvefit.domain.entity.Workout
import com.cairosquad.evolvefit.domain.usecase.home.model.NutritionProgress
import com.cairosquad.evolvefit.domain.usecase.home.model.User
import com.cairosquad.evolvefit.domain.usecase.home.model.WeeklyProgress

interface HomeRepository {
    suspend fun getUser(): User
    suspend fun getWeeklyProgress(): WeeklyProgress
    suspend fun getCaloriesNutritionUpdate(): NutritionProgress<Int>
    suspend fun getWaterNutritionUpdate(): NutritionProgress<Float>
    suspend fun getPersonalizedWorkouts(): List<Workout>
}