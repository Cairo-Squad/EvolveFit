package com.cairosquad.evolvefit.domain

import com.cairosquad.evolvefit.entity.Exercise
import com.cairosquad.evolvefit.entity.Tool

interface WorkOutRepository {
    suspend fun getEquipments(): List<Tool>
    suspend fun createExercise(exercise: Exercise)
}