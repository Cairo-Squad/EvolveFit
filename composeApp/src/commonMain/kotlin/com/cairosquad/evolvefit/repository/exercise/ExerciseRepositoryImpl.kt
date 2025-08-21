package com.cairosquad.evolvefit.repository.exercise

import com.cairosquad.evolvefit.domain.entity.Exercise
import com.cairosquad.evolvefit.domain.repository.ExerciseRepository
import com.cairosquad.evolvefit.repository.execption.callDataSource
import com.cairosquad.evolvefit.repository.exercise.remote.ExerciseRemoteDataSource
import com.cairosquad.evolvefit.repository.exercise.remote.toDto
import com.cairosquad.evolvefit.repository.exercise.remote.toDomain

class ExerciseRepositoryImpl(
    private val remoteDataSource: ExerciseRemoteDataSource
) : ExerciseRepository {

    override suspend fun getAllExercises(): List<Exercise> {
        return callDataSource { remoteDataSource.getAllExercises().map { it.toDomain() } }
    }

    override suspend fun createExercise(exercise: Exercise) {
        callDataSource {
            remoteDataSource.createExercise(exercise.toDto())
        }
    }

    override suspend fun uploadExerciseImage(
        fileBytes: ByteArray,
        fileName: String
    ): String {
       return remoteDataSource.uploadExerciseImage(fileBytes,fileName)
    }

    override suspend fun getExercisesByQuery(query: String): List<Exercise> {
     return remoteDataSource.getExercisesByQuery(query).map { it.toDomain() }
    }
}