package com.cairosquad.evolvefit.domain

import com.cairosquad.evolvefit.entity.BodyPart
import com.cairosquad.evolvefit.entity.Workout

interface WorkoutRepository {
    suspend fun getAllWorkouts(): List<Workout>
    suspend fun getWorkoutsByBodyPart(bodyPart: BodyPart): List<Workout>
}