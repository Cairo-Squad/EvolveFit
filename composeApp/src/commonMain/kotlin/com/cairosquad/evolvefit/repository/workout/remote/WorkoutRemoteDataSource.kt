package com.cairosquad.evolvefit.repository.workout.remote

import com.cairosquad.evolvefit.domain.model.FocusArea
import com.cairosquad.evolvefit.repository.workout.remote.dto.CreateWorkoutRequest
import com.cairosquad.evolvefit.repository.workout.remote.dto.WorkoutDetailsDto
import com.cairosquad.evolvefit.repository.workout.remote.dto.WorkoutDto

interface WorkoutRemoteDataSource {
    suspend fun getFavoriteWorkout(): List<WorkoutDto>
    suspend fun createWorkout(request: CreateWorkoutRequest)
    suspend fun getSuggestedWorkouts(): List<WorkoutDto>
    suspend fun getCommunityWorkouts(): List<WorkoutDetailsDto>
    suspend fun getWorkoutsByFocusArea(focusArea: FocusArea): List<WorkoutDetailsDto>
    suspend fun getCommunityWorkoutsByFocusArea(focusArea: FocusArea): List<WorkoutDetailsDto>
}