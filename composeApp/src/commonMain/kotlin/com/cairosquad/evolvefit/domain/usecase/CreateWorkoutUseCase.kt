package com.cairosquad.evolvefit.domain.usecase

import com.cairosquad.evolvefit.domain.WorkoutRepository
import com.cairosquad.evolvefit.entity.Exercise
import com.cairosquad.evolvefit.entity.Workout

class CreateWorkoutUseCase(
    private val repository: WorkoutRepository,
) {
    suspend  fun createWorkOut(workout: Workout) {
         repository.createWorkout(workout)
    }

    suspend  fun getAllExercises(): List<Exercise> {
        return repository.getAllExercises()
    }
}


