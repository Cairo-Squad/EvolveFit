package com.cairosquad.evolvefit.domain.repository

import com.cairosquad.evolvefit.domain.entity.Exercise

interface ExerciseRepository {
    suspend fun createExercise(exercise: Exercise)
    suspend fun uploadExerciseImage(fileBytes: ByteArray, fileName: String): String
    suspend fun getAllExercises(): List<Exercise>
    suspend fun getExercisesByQuery(query: String): List<Exercise>
}