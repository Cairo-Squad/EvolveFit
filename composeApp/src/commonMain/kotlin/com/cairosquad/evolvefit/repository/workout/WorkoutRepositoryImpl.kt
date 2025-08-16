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
        return workoutRemoteDataSource.getWorkoutDetails(id).toDomain()
    }

    override suspend fun getSuggestedWorkouts(): List<WorkoutSuggested> {
        val result = workoutRemoteDataSource.getSuggestedWorkouts().map { it.toDomain() }
        return result
    }


    override suspend fun getCommunityWorkouts(): List<WorkoutSuggested> {
        return workoutRemoteDataSource.getCommunityWorkouts().map { it.toDomain() }
    }

    override suspend fun getCommunityWorkoutsByFocusArea(focusArea: FocusArea): List<Workout> {
        TODO("Not yet implemented")
    }

    override suspend fun getFavoriteWorkouts(): List<WorkoutSuggested> =
        callDataSource { workoutRemoteDataSource.getFavoriteWorkout().map { it.toDomain() } }


    override suspend fun createWorkout(workout: Workout) {
        workoutRemoteDataSource.createWorkout(workout.toCreateRequest())
    }

    override suspend fun addWorkoutToFavorites(workoutId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getWorkoutsByFocusArea(focusArea: FocusArea): List<WorkoutSuggested> {
        return workoutRemoteDataSource.getWorkoutsByFocusArea(focusArea).map { it.toDomain() }
    }
}