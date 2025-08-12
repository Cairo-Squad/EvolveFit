package com.cairosquad.evolvefit.domain.repository

import com.cairosquad.evolvefit.entity.Exercise
import com.cairosquad.evolvefit.entity.Tool
import com.cairosquad.evolvefit.entity.Workout

interface WorkoutRepository {
    suspend fun getEquipments(): List<Tool>
    suspend fun createExercise(exercise: Exercise)
    suspend fun getWorkoutById(id: String): Workout
}