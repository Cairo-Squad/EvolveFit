package com.cairosquad.evolvefit.repository.workout

import com.cairosquad.evolvefit.domain.entity.Workout
import com.cairosquad.evolvefit.domain.entity.WorkoutSuggested
import com.cairosquad.evolvefit.domain.model.FocusArea
import com.cairosquad.evolvefit.domain.repository.WorkoutRepository
import com.cairosquad.evolvefit.repository.execption.callDataSource
import com.cairosquad.evolvefit.repository.workout.remote.WorkoutRemoteDataSource
import com.cairosquad.evolvefit.repository.workout.remote.toCreateRequest
import com.cairosquad.evolvefit.repository.workout.remote.toDomain

class WorkoutRepositoryImpl(
    private val workoutRemoteDataSource: WorkoutRemoteDataSource,
) : WorkoutRepository {

    override suspend fun getWorkoutById(id: String): Workout {
        return callDataSource {
            workoutRemoteDataSource.getWorkoutDetails(id).toDomain()
        }
    }

    override suspend fun getSuggestedWorkouts(): List<WorkoutSuggested> {
        return callDataSource {
            workoutRemoteDataSource.getSuggestedWorkouts().map { it.toDomain() }
        }
    }


    override suspend fun getCommunityWorkouts(): List<WorkoutSuggested> {
        return callDataSource {
            workoutRemoteDataSource.getCommunityWorkouts().map { it.toDomain() }
        }
    }

    override suspend fun getCommunityWorkoutsByFocusArea(focusArea: FocusArea): List<WorkoutSuggested> {
        return callDataSource {
            workoutRemoteDataSource.getCommunityWorkoutsByFocusArea(focusArea).map { it.toDomain() }
        }
    }

    override suspend fun getFavoriteWorkouts(): List<WorkoutSuggested> {
        return callDataSource {
            workoutRemoteDataSource.getFavoriteWorkout().map { it.toDomain() }
        }
    }


    override suspend fun createWorkout(workout: Workout) {
        callDataSource {
            workoutRemoteDataSource.createWorkout(workout.toCreateRequest())
        }
    }

    override suspend fun addWorkoutToFavorites(workoutId: String) {
        callDataSource {
            workoutRemoteDataSource.addFavoriteWorkOut(workoutId)
        }
    }

    override suspend fun deleteWorkoutToFavorites(workoutId: String) {
        callDataSource {
            workoutRemoteDataSource.deleteFavoriteWorkOut(workoutId)
        }
    }

    override suspend fun getWorkoutsByFocusArea(focusArea: FocusArea): List<WorkoutSuggested> {
        return callDataSource {
            workoutRemoteDataSource.getWorkoutsByFocusArea(focusArea).map { it.toDomain() }
        }
    }
}