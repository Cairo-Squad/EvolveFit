package com.cairosquad.evolvefit.repository.home

import com.cairosquad.evolvefit.domain.entity.Workout
import com.cairosquad.evolvefit.domain.repository.HomeRepository
import com.cairosquad.evolvefit.domain.usecase.home.model.NutritionUpdate
import com.cairosquad.evolvefit.domain.usecase.home.model.User
import com.cairosquad.evolvefit.domain.usecase.home.model.WeeklyProgress

class HomeRepositoryImpl : HomeRepository {
    override fun getUser(): User {
        TODO("Not yet implemented")
    }

    override fun getWeeklyProgress(): WeeklyProgress {
        TODO("Not yet implemented")
    }

    override fun getCaloriesNutritionUpdate(): NutritionUpdate {
        TODO("Not yet implemented")
    }

    override fun getWaterNutritionUpdate(): NutritionUpdate {
        TODO("Not yet implemented")
    }

    override fun getPersonalizedWorkouts(): List<Workout> {
        TODO("Not yet implemented")
    }
}