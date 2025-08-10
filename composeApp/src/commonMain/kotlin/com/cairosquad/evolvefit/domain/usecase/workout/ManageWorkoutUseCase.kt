package com.cairosquad.evolvefit.domain.usecase.workout

import com.cairosquad.evolvefit.domain.WorkoutRepository

class ManageWorkoutsUseCase(private val repository: WorkoutRepository) {
    suspend fun getAllWorkouts() = repository.getAllWorkouts()
    suspend fun getWorkoutsByBodyPart(bodyPartName: String) =
        repository.getWorkoutsByBodyPart(bodyPartName)
}