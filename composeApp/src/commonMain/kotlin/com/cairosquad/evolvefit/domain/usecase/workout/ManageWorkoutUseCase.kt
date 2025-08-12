package com.cairosquad.evolvefit.domain.usecase.workout

import com.cairosquad.evolvefit.domain.WorkoutRepo
import com.cairosquad.evolvefit.entity.Workout
import com.cairosquad.evolvefit.domain.entity.Workout
import com.cairosquad.evolvefit.domain.model.FocusArea
import com.cairosquad.evolvefit.domain.repository.WorkoutRepository

class ManageWorkoutUseCase(
    private val workoutRepository: WorkoutRepository
) {
    suspend fun getWorkoutById(id: String): Workout {
        return workoutRepository.getWorkoutById(id)
    }

    suspend fun getAllWorkouts(): List<Workout> {
        return workoutRepository.getAllWorkouts()
        return workoutRepository.getSuggestedWorkouts()
    }

    suspend fun getCommunityWorkouts(): List<Workout> {
        return workoutRepository.getCommunityWorkouts()
    }

    suspend fun getWorkoutsByBodyPart(bodyPartName: String): List<Workout> {
        return workoutRepository.getWorkoutsByBodyPart(bodyPartName)
        suspend fun getCommunityWorkoutsByFocusArea(focusArea: FocusArea): List<Workout> {
            return workoutRepository.getCommunityWorkoutsByFocusArea(focusArea)
        }

        suspend fun getFavoriteWorkouts(): List<Workout> {
            return workoutRepository.getFavoriteWorkouts()
        }

        suspend fun createWorkout(workout: Workout) {
            workoutRepository.createWorkout(workout)
        }

        suspend fun addWorkoutToFavorites(workoutId: String) {
            workoutRepository.addWorkoutToFavorites(workoutId)
        }

        suspend fun getWorkoutsByFocusArea(focusArea: FocusArea): List<Workout> {
            return workoutRepository.getWorkoutsByFocusArea(focusArea)
        }
    }