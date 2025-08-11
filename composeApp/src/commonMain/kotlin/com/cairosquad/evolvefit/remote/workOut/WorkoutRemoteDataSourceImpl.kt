package com.cairosquad.evolvefit.remote.workOut

import com.cairosquad.evolvefit.entity.Workout
import com.cairosquad.evolvefit.remote.model.ExerciseDto
import com.cairosquad.evolvefit.repository.remote.WorkoutRemoteDataSource
import io.ktor.client.HttpClient

class WorkoutRemoteDataSourceImpl(
    private val client: HttpClient
) : WorkoutRemoteDataSource {
    override suspend fun createWorkout(workout: Workout) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllExercises(): List<ExerciseDto> {
        TODO("Not yet implemented")
    }
}