package com.cairosquad.evolvefit.domain

import com.cairosquad.evolvefit.entity.Exercise
import com.cairosquad.evolvefit.entity.Tool
import com.cairosquad.evolvefit.entity.Workout

interface WorkoutRepository {
    suspend fun createWorkout(workout: Workout)
    suspend fun getAllExercises(): List<Exercise>
}
