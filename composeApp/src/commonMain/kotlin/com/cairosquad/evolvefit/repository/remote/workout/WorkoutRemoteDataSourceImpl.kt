package com.cairosquad.evolvefit.repository.remote.workout

import com.cairosquad.evolvefit.repository.remote.auth.EquipmentDto

class WorkoutRemoteDataSourceImpl: WorkoutRemoteDataSource {
    override suspend fun getEquipments(): List<EquipmentDto> {
        return emptyList() // TODO("Not yet implemented")
    }

    override suspend fun createExercise(exercise: ExerciseDto) {
        // TODO("Not yet implemented")
    }
}