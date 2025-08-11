package com.cairosquad.evolvefit.domain.usecase.workout

import com.cairosquad.evolvefit.domain.WorkoutRepository
import com.cairosquad.evolvefit.entity.Workout

class ManageWorkoutsUseCase(private val workoutRepository: WorkoutRepository) {
    suspend fun getAllWorkouts(): List<Workout> {
        return workoutRepository.getAllWorkouts()
    }

    suspend fun getWorkoutsByBodyPart(bodyPartName: String): List<Workout> {
        return workoutRepository.getWorkoutsByBodyPart(bodyPartName)
    }
}