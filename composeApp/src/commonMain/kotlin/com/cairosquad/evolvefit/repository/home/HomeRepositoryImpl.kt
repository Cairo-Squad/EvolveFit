package com.cairosquad.evolvefit.repository.home

import com.cairosquad.evolvefit.domain.entity.Workout
import com.cairosquad.evolvefit.domain.repository.HomeRepository
import com.cairosquad.evolvefit.domain.usecase.home.model.NutritionProgress
import com.cairosquad.evolvefit.domain.usecase.home.model.User
import com.cairosquad.evolvefit.domain.usecase.home.model.WeeklyProgress

class HomeRepositoryImpl : HomeRepository {
    override suspend fun getUser(): User {
        TODO("Not yet implemented")
    }

    override suspend fun getWeeklyProgress(): WeeklyProgress {
        TODO("Not yet implemented")
    }

    override suspend fun getCaloriesNutritionUpdate(): NutritionProgress<Int> {
        TODO("Not yet implemented")
    }

    override suspend fun getWaterNutritionUpdate(): NutritionProgress<Float> {
        TODO("Not yet implemented")
    }

    override suspend fun getPersonalizedWorkouts(): List<Workout> {
        TODO("Not yet implemented")
    }
}