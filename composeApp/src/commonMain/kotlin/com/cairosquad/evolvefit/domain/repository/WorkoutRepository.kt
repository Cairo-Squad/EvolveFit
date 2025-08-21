package com.cairosquad.evolvefit.domain.repository

import com.cairosquad.evolvefit.domain.entity.Workout
import com.cairosquad.evolvefit.domain.entity.WorkoutHistory
import com.cairosquad.evolvefit.domain.entity.WorkoutSuggested
import com.cairosquad.evolvefit.domain.model.FocusArea

interface WorkoutRepository {
    suspend fun getWorkoutById(id: String): Workout
    suspend fun getSuggestedWorkouts(): List<WorkoutSuggested>
    suspend fun getCommunityWorkouts(): List<WorkoutSuggested>
    suspend fun getCommunityWorkoutsByFocusArea(focusArea: FocusArea): List<WorkoutSuggested>
    suspend fun getFavoriteWorkouts(): List<WorkoutSuggested>
    suspend fun createWorkout(workout: Workout)
    suspend fun addWorkoutToFavorites(workoutId: String)
    suspend fun deleteFavoriteWorkout(workoutId: String)
    suspend fun getWorkoutsByFocusArea(focusArea: FocusArea): List<WorkoutSuggested>
    suspend fun getWorkoutHistory(): List<WorkoutHistory>
}