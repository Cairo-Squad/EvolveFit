package com.cairosquad.evolvefit.domain

import com.cairosquad.evolvefit.domain.model.BodyPart
import com.cairosquad.evolvefit.domain.model.WorkoutModel

interface WorkoutRepository {
    suspend fun getAllWorkouts(): List<WorkoutModel>
    suspend fun getWorkoutsByBodyPart(bodyPart: BodyPart): List<WorkoutModel>
}