package com.cairosquad.evolvefit.domain.usecase.workout

import com.cairosquad.evolvefit.domain.repository.WorkoutRepository
import com.cairosquad.evolvefit.entity.Workout

class ManageWorkoutUseCase(
    private val workoutRepository: WorkoutRepository
) {
    suspend fun getWorkoutById(id: String): Workout {
        return workoutRepository.getWorkoutById(id)
    }
}