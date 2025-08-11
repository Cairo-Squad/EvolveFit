package com.cairosquad.evolvefit.domain.repository

import com.cairosquad.evolvefit.domain.entity.Equipment
import com.cairosquad.evolvefit.domain.entity.Exercise
import com.cairosquad.evolvefit.domain.entity.Workout

interface WorkoutRepository {
    suspend fun getEquipments(): List<Equipment>
    suspend fun createExercise(exercise: Exercise)
    suspend fun getWorkoutById(id: String): Workout
}