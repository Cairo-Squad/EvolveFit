package com.cairosquad.evolvefit.domain.usecase.workOut

import com.cairosquad.evolvefit.domain.WorkOutRepository
import com.cairosquad.evolvefit.entity.Exercise
import com.cairosquad.evolvefit.entity.Tool

class ExerciseUseCase(
    private val workOutRepository: WorkOutRepository
) {
    suspend fun getEquipments(): List<Tool> {
        return workOutRepository.getEquipments()
    }

    suspend fun createExercise(exercise: Exercise) {
        workOutRepository.createExercise(exercise)
    }
}