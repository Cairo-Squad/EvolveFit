package com.cairosquad.evolvefit.domain.usecase.workout

import com.cairosquad.evolvefit.domain.entity.Workout
import com.cairosquad.evolvefit.domain.repository.WorkoutRepository

class ManageWorkoutUseCase(
    private val workoutRepository: WorkoutRepository
) {
    suspend fun getWorkoutById(id: String): Workout {
        return workoutRepository.getWorkoutById(id)
    }
}