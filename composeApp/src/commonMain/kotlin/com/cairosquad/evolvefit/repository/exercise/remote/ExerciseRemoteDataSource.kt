package com.cairosquad.evolvefit.repository.exercise.remote

import com.cairosquad.evolvefit.repository.exercise.remote.dto.ExerciseDto
import com.cairosquad.evolvefit.repository.exercise.remote.dto.ExerciseResponseDto

interface ExerciseRemoteDataSource {
    suspend fun createExercise(exercise: ExerciseDto)
    suspend fun getAllExercises(): List<ExerciseResponseDto>
}