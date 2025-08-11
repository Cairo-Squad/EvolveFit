package com.cairosquad.evolvefit.domain.usecase.workout

import com.cairosquad.evolvefit.domain.entity.Equipment
import com.cairosquad.evolvefit.domain.entity.Exercise
import com.cairosquad.evolvefit.domain.repository.WorkoutRepository

class ExerciseUseCase(
    private val workOutRepository: WorkoutRepository
) {
    suspend fun getEquipments(): List<Equipment> {
        return workOutRepository.getEquipments()
    }

    suspend fun createExercise(exercise: Exercise) {
        workOutRepository.createExercise(exercise)
    }
}