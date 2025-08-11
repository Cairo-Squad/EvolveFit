package com.cairosquad.evolvefit.domain.usecase.workout

import com.cairosquad.evolvefit.domain.repository.WorkoutRepository
import com.cairosquad.evolvefit.entity.Exercise
import com.cairosquad.evolvefit.entity.Tool

class ExerciseUseCase(
    private val workOutRepository: WorkoutRepository
) {
    suspend fun getEquipments(): List<Tool> {
        return workOutRepository.getEquipments()
    }

    suspend fun createExercise(exercise: Exercise) {
        workOutRepository.createExercise(exercise)
    }
}