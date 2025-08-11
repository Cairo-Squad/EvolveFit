package com.cairosquad.evolvefit.domain.usecase.workout

import com.cairosquad.evolvefit.domain.WorkoutRepo
import com.cairosquad.evolvefit.entity.Workout

class ManageWorkoutsUseCase(private val workoutRepository: WorkoutRepo) {
    suspend fun getAllWorkouts(): List<Workout> {
        return workoutRepository.getAllWorkouts()
    }

    suspend fun getWorkoutsByBodyPart(bodyPartName: String): List<Workout> {
        return workoutRepository.getWorkoutsByBodyPart(bodyPartName)
    }
}