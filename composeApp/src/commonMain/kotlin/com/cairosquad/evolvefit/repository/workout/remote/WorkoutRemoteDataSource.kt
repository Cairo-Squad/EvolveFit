package com.cairosquad.evolvefit.repository.workout.remote

import com.cairosquad.evolvefit.repository.workout.remote.remote.WorkoutDto

interface WorkoutRemoteDataSource {
    suspend fun getFavoriteWorkout(): List<WorkoutDto>
}