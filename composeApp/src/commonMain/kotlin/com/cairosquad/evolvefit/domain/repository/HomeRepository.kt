package com.cairosquad.evolvefit.domain.repository

import com.cairosquad.evolvefit.domain.entity.Workout
import com.cairosquad.evolvefit.domain.usecase.home.model.NutritionUpdate
import com.cairosquad.evolvefit.domain.usecase.home.model.User
import com.cairosquad.evolvefit.domain.usecase.home.model.WeeklyProgress

interface HomeRepository {
    fun getUser(): User
    fun getWeeklyProgress(): WeeklyProgress
    fun getCaloriesNutritionUpdate(): NutritionUpdate
    fun getWaterNutritionUpdate(): NutritionUpdate
    fun getPersonalizedWorkouts(): List<Workout>
}