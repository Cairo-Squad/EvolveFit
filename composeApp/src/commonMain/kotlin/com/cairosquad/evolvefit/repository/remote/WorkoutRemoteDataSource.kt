package com.cairosquad.evolvefit.repository.remote

import com.cairosquad.evolvefit.entity.Workout
import com.cairosquad.evolvefit.remote.model.ExerciseDto
import com.cairosquad.evolvefit.remote.model.WorkoutDto

interface WorkoutRemoteDataSource {
    suspend fun createWorkout(workout: Workout)
    suspend fun getAllExercises(): List<ExerciseDto>
}