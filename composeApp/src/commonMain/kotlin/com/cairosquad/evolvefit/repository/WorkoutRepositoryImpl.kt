package com.cairosquad.evolvefit.repository

import com.cairosquad.evolvefit.repository.remote.WorkoutRemoteDataSource
import com.cairosquad.evolvefit.domain.WorkoutRepository
import com.cairosquad.evolvefit.entity.Exercise
import com.cairosquad.evolvefit.entity.Workout
import com.cairosquad.evolvefit.remote.safeApiCall

class WorkoutRepositoryImpl(
    private val workoutRemoteDataSource: WorkoutRemoteDataSource
) : WorkoutRepository {

    override suspend fun createWorkout(workout: Workout) {
        safeApiCall {
             workoutRemoteDataSource.createWorkout(workout)
        }
    }

    override suspend fun getAllExercises(): List<Exercise> {
        safeApiCall {
            return workoutRemoteDataSource.getAllExercises().map { it.toDomain() }
        }
    }
}
