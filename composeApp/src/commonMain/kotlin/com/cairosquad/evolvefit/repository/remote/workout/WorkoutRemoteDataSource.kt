package com.cairosquad.evolvefit.repository.remote.workout

import com.cairosquad.evolvefit.repository.remote.auth.EquipmentDto

interface WorkoutRemoteDataSource {
    suspend fun getEquipments(): List<EquipmentDto>
    suspend fun createExercise(exercise: ExerciseDto)
}