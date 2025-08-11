package com.cairosquad.evolvefit.domain

import com.cairosquad.evolvefit.entity.Workout

interface WorkoutRepo {
    suspend fun getAllWorkouts(): List<Workout>
    suspend fun getWorkoutsByBodyPart(bodyPartName: String): List<Workout>
}