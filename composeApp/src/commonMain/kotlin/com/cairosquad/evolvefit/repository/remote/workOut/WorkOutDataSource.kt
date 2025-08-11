package com.cairosquad.evolvefit.repository.remote.workOut

import com.cairosquad.evolvefit.repository.remote.auth.EquipmentDto

interface WorkOutDataSource {
    suspend fun getEquipments(): List<EquipmentDto>
    suspend fun createExercise(exercise: ExerciseDto)
}