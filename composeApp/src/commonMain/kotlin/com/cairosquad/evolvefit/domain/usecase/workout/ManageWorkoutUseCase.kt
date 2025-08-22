package com.cairosquad.evolvefit.domain.usecase.workout

import com.cairosquad.evolvefit.domain.entity.Workout
import com.cairosquad.evolvefit.domain.entity.WorkoutHistory
import com.cairosquad.evolvefit.domain.entity.WorkoutSuggested
import com.cairosquad.evolvefit.domain.model.FocusArea
import com.cairosquad.evolvefit.domain.repository.WorkoutRepository

class ManageWorkoutUseCase(
    private val workoutRepository: WorkoutRepository
) {
    suspend fun getWorkoutById(id: String): Workout {
        return workoutRepository.getWorkoutById(id)
    }

    suspend fun getSuggestedWorkouts(): List<WorkoutSuggested> {
        return workoutRepository.getSuggestedWorkouts()
    }

    suspend fun uploadWorkoutImage(
        fileBytes: ByteArray,
        fileName: String,
        workoutId : String
    ): String {
        return workoutRepository.uploadWorkoutImage(fileBytes, fileName,workoutId)
    }


    suspend fun getCommunityWorkouts(): List<WorkoutSuggested> {
        return workoutRepository.getCommunityWorkouts()
    }

    suspend fun getCommunityWorkoutsByFocusArea(focusArea: FocusArea): List<WorkoutSuggested> {
        return workoutRepository.getCommunityWorkoutsByFocusArea(focusArea)
    }

    suspend fun getFavoriteWorkouts(): List<WorkoutSuggested> {
        return workoutRepository.getFavoriteWorkouts()
    }
    suspend fun deleteFavouriteWorkout(mealId: String) {
        return workoutRepository.deleteFavoriteWorkout(mealId)
    }
    suspend fun createWorkOut(workout: Workout): Workout {
        return workoutRepository.createWorkout(workout)
    }

    suspend fun addWorkoutToFavorites(workoutId: String) {
        workoutRepository.addWorkoutToFavorites(workoutId)
    }

    suspend fun getWorkoutsByFocusArea(focusArea: FocusArea): List<WorkoutSuggested> {
        return workoutRepository.getWorkoutsByFocusArea(focusArea)
    }

    suspend fun getWorkoutHistory(): List<WorkoutHistory> {
        return workoutRepository.getWorkoutHistory()
    }
}