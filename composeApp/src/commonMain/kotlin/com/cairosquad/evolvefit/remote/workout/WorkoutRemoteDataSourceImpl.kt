package com.cairosquad.evolvefit.remote.workout

import com.cairosquad.evolvefit.repository.remote.auth.EquipmentDto
import com.cairosquad.evolvefit.repository.remote.workout.ExerciseDto
import com.cairosquad.evolvefit.repository.remote.workout.WorkoutRemoteDataSource

class WorkoutRemoteDataSourceImpl: WorkoutRemoteDataSource {
    override suspend fun getEquipments(): List<EquipmentDto> {
        return emptyList() // TODO("Not yet implemented")
    }

    override suspend fun createExercise(exercise: ExerciseDto) {
        // TODO("Not yet implemented")
    }
}