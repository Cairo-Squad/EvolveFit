package com.cairosquad.evolvefit.repository.exercise.remot

import com.cairosquad.evolvefit.repository.exercise.remot.dto.ExerciseDto
import com.cairosquad.evolvefit.repository.exercise.remot.dto.ExerciseResponseDto

interface ExerciseRemoteDataSource {
    suspend fun createExercise(exercise: ExerciseDto): ExerciseResponseDto
}