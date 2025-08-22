package com.cairosquad.evolvefit.repository.exercise.remote

import com.cairosquad.evolvefit.domain.entity.Exercise
import com.cairosquad.evolvefit.repository.exercise.remote.dto.ExerciseDto
import com.cairosquad.evolvefit.repository.exercise.remote.dto.ExerciseResponseDto

interface ExerciseRemoteDataSource {
    suspend fun createExercise(exercise: ExerciseDto) : ExerciseResponseDto
    suspend fun uploadExerciseImage(fileBytes: ByteArray, fileName: String,exerciseId : String): String
    suspend fun getExercisesByQuery(query: String): List<ExerciseResponseDto>
    suspend fun getAllExercises(): List<ExerciseResponseDto>
}