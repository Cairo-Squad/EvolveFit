package com.cairosquad.evolvefit.domain.repository

import com.cairosquad.evolvefit.domain.entity.Workout
import com.cairosquad.evolvefit.domain.entity.WorkoutSuggested
import com.cairosquad.evolvefit.domain.model.FocusArea

interface WorkoutRepository {
    suspend fun getWorkoutById(id: String): Workout
    suspend fun getSuggestedWorkouts(): List<WorkoutSuggested>
    suspend fun getCommunityWorkouts(): List<Workout>
    suspend fun getCommunityWorkoutsByFocusArea(focusArea: FocusArea): List<Workout>
    suspend fun getFavoriteWorkouts(): List<Workout>
    suspend fun createWorkout(workout: Workout)
    suspend fun addWorkoutToFavorites(workoutId: String)
    suspend fun getWorkoutsByFocusArea(focusArea: FocusArea): List<Workout>
}