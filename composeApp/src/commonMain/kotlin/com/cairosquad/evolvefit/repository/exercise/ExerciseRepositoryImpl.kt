package com.cairosquad.evolvefit.repository.exercise

import com.cairosquad.evolvefit.domain.entity.Exercise
import com.cairosquad.evolvefit.domain.repository.ExerciseRepository

class ExerciseRepositoryImpl() : ExerciseRepository {


    override suspend fun createExercise(exercise: Exercise) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllExercises(): List<Exercise> {
        TODO("Not yet implemented")
    }

    override suspend fun getExercisesByQuery(query: String): List<Exercise> {
        TODO("Not yet implemented")
    }
}