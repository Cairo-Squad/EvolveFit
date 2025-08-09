package com.cairosquad.evolvefit.domain.usecase.workout

import com.cairosquad.evolvefit.domain.WorkoutRepository
import com.cairosquad.evolvefit.entity.BodyPart
import com.cairosquad.evolvefit.entity.Workout

class ManageWorkoutsUseCase(
    private val repository: WorkoutRepository
) {
    suspend fun getAllWorkouts(): List<Workout> =
        repository.getAllWorkouts()

    suspend fun getWorkoutsByBodyPart(bodyPart: BodyPart): List<Workout> =
        repository.getWorkoutsByBodyPart(bodyPart)
}