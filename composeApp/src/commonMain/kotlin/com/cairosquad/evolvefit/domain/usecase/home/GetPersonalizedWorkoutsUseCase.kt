package com.cairosquad.evolvefit.domain.usecase.home

import com.cairosquad.evolvefit.domain.entity.Workout
import com.cairosquad.evolvefit.domain.repository.WorkoutRepository

class GetPersonalizedWorkoutsUseCase(
    private val workoutRepository: WorkoutRepository
) {

    suspend fun getWorkouts(): List<Workout> {
        return workoutRepository.getSuggestedWorkouts()
    }
}