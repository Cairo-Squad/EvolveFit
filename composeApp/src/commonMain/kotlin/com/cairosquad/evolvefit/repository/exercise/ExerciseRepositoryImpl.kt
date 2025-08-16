package com.cairosquad.evolvefit.repository.exercise

import com.cairosquad.evolvefit.domain.entity.Exercise
import com.cairosquad.evolvefit.domain.repository.ExerciseRepository
import com.cairosquad.evolvefit.repository.exercise.remote.ExerciseRemoteDataSource
import com.cairosquad.evolvefit.repository.exercise.remote.toDto
import com.cairosquad.evolvefit.repository.exercise.remote.toDomain

class ExerciseRepositoryImpl(
    private val remoteDataSource: ExerciseRemoteDataSource
) : ExerciseRepository {

    override suspend fun getAllExercises(): List<Exercise> {
        return remoteDataSource.getAllExercises().map { it.toDomain() }
    }

    override suspend fun createExercise(exercise: Exercise) {
        val exerciseDto = exercise.toDto()
        remoteDataSource.createExercise(exerciseDto)
    }



    override suspend fun getExercisesByQuery(query: String): List<Exercise> {
        // TODO: implement search filtering if needed
        return emptyList()
    }
}