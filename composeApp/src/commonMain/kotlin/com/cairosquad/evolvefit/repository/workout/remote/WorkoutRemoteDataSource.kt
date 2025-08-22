package com.cairosquad.evolvefit.repository.workout.remote

import com.cairosquad.evolvefit.domain.model.FocusArea
import com.cairosquad.evolvefit.repository.workout.remote.dto.CreateWorkoutRequest
import com.cairosquad.evolvefit.repository.workout.remote.dto.FavoritesWorkoutDto
import com.cairosquad.evolvefit.repository.workout.remote.dto.PlayedWorkoutDto
import com.cairosquad.evolvefit.repository.workout.remote.dto.WorkoutDetailsDto
import com.cairosquad.evolvefit.repository.workout.remote.dto.WorkoutDto
import com.cairosquad.evolvefit.repository.workout.remote.dto.WorkoutHistoryDto

interface WorkoutRemoteDataSource {
    suspend fun getFavoriteWorkout(): List<FavoritesWorkoutDto>
    suspend fun createWorkout(request: CreateWorkoutRequest): WorkoutDetailsDto
    suspend fun getWorkoutDetails(workoutId: String): WorkoutDetailsDto
    suspend fun getSuggestedWorkouts(): List<WorkoutDto>
    suspend fun getCommunityWorkouts(): List<WorkoutDto>
    suspend fun getWorkoutsByFocusArea(focusArea: FocusArea): List<WorkoutDto>
    suspend fun submitPlayedWorkout(playedWorkout: PlayedWorkoutDto)
    suspend fun getCommunityWorkoutsByFocusArea(focusArea: FocusArea): List<WorkoutDto>
    suspend fun getWorkoutHistory(): List<WorkoutHistoryDto>
    suspend fun addFavoriteWorkout(workOutId : String)
    suspend fun deleteFavoriteWorkout(workoutId : String)
    suspend fun uploadWorkoutImage(fileBytes: ByteArray, fileName: String, exerciseId : String): String
}