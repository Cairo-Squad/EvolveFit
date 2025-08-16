package com.cairosquad.evolvefit.repository.exercise.remote

import com.cairosquad.evolvefit.repository.exercise.remote.dto.ExerciseDto
import com.cairosquad.evolvefit.repository.exercise.remote.dto.ExerciseResponseDto

interface ExerciseRemoteDataSource {
    suspend fun createExercise(exercise: ExerciseDto): ExerciseResponseDto
    suspend fun getAllExercises(): List<ExerciseResponseDto>
}