package com.cairosquad.evolvefit.repository.workout.remote

import com.cairosquad.evolvefit.domain.model.FocusArea
import com.cairosquad.evolvefit.repository.workout.remote.dto.CreateWorkoutRequest
import com.cairosquad.evolvefit.repository.workout.remote.dto.WorkoutDetailsDto
import com.cairosquad.evolvefit.repository.workout.remote.dto.WorkoutDto

interface WorkoutRemoteDataSource {
    suspend fun createWorkout(request: CreateWorkoutRequest)
    suspend fun getWorkoutDetails(workoutId: String): WorkoutDetailsDto
    suspend fun getSuggestedWorkouts(): List<WorkoutDto>
    suspend fun getCommunityWorkouts(): List<WorkoutDto>
    suspend fun getWorkoutsByFocusArea(focusArea: FocusArea): List<WorkoutDto>
    suspend fun getCommunityWorkoutsByFocusArea(focusArea: FocusArea): List<WorkoutDetailsDto>
}