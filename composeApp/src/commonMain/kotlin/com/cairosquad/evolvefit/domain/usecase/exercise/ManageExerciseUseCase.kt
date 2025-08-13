package com.cairosquad.evolvefit.domain.usecase.exercise

import com.cairosquad.evolvefit.domain.entity.Exercise
import com.cairosquad.evolvefit.domain.repository.ExerciseRepository

class ManageExerciseUseCase(
    private val exerciseRepository: ExerciseRepository
) {
    suspend fun createExercise(exercise: Exercise) {
        exerciseRepository.createExercise(exercise)
    }

    suspend fun getAllExercises(): List<Exercise> {
        return exerciseRepository.getAllExercises()
    }

    suspend fun getExercisesByQuery(query: String): List<Exercise> {
        return exerciseRepository.getExercisesByQuery(query)
    }
}