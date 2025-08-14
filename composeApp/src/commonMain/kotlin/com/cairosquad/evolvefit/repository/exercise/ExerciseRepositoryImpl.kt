package com.cairosquad.evolvefit.repository.exercise

import com.cairosquad.evolvefit.domain.entity.Exercise
import com.cairosquad.evolvefit.domain.repository.ExerciseRepository
import com.cairosquad.evolvefit.repository.exercise.remote.ExerciseRemoteDataSource
import com.cairosquad.evolvefit.repository.exercise.remote.toDto

class ExerciseRepositoryImpl(
    private val remote: ExerciseRemoteDataSource
) : ExerciseRepository {
    override suspend fun createExercise(exercise: Exercise) {
        val exerciseDto = exercise.toDto()
        print("ExerciseRepositoryImpl createExercise called with exerciseDto: ${remote.createExercise(exerciseDto)}")
        remote.createExercise(exerciseDto)
    }

    override suspend fun getAllExercises(): List<Exercise> {
        TODO("Not yet implemented")
    }

    override suspend fun getExercisesByQuery(query: String): List<Exercise> {
        TODO("Not yet implemented")
    }
}