package com.cairosquad.evolvefit.repository.workout

import com.cairosquad.evolvefit.domain.entity.Workout
import com.cairosquad.evolvefit.domain.entity.WorkoutHistory
import com.cairosquad.evolvefit.domain.entity.WorkoutSuggested
import com.cairosquad.evolvefit.domain.model.FocusArea
import com.cairosquad.evolvefit.domain.model.PlayedWorkout
import com.cairosquad.evolvefit.domain.repository.WorkoutRepository
import com.cairosquad.evolvefit.repository.execption.callDataSource
import com.cairosquad.evolvefit.repository.workout.remote.WorkoutRemoteDataSource
import com.cairosquad.evolvefit.repository.workout.remote.dto.PlayedWorkoutDto
import com.cairosquad.evolvefit.repository.workout.remote.toCreateRequest
import com.cairosquad.evolvefit.repository.workout.remote.toDomain
import com.cairosquad.evolvefit.repository.workout.remote.toDto

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

    override suspend fun createWorkout(workout: Workout): Workout {
        return callDataSource {
            val response = workoutRemoteDataSource.createWorkout(workout.toCreateRequest())
            response.toDomain()
        }
    }

    override suspend fun addWorkoutToFavorites(workoutId: String) {
        callDataSource {
            workoutRemoteDataSource.addFavoriteWorkout(workoutId)
        }
    }

    override suspend fun deleteFavoriteWorkout(workoutId: String) {
        callDataSource {
            workoutRemoteDataSource.deleteFavoriteWorkout(workoutId)
        }
    }

    override suspend fun getWorkoutsByFocusArea(focusArea: FocusArea): List<WorkoutSuggested> {
        return callDataSource {
            workoutRemoteDataSource.getWorkoutsByFocusArea(focusArea).map { it.toDomain() }
        }
    }

    override suspend fun submitPlayedWorkout(playedWorkout: PlayedWorkout) {
        callDataSource {
            workoutRemoteDataSource.submitPlayedWorkout(playedWorkout.toDto())
        }
    }

    override suspend fun getWorkoutHistory(): List<WorkoutHistory> {
        return callDataSource { workoutRemoteDataSource.getWorkoutHistory().map { it.toDomain() } }
    }

    override suspend fun uploadWorkoutImage(
        fileBytes: ByteArray,
        fileName: String,
        workoutId : String
    ): String {
        return workoutRemoteDataSource.uploadWorkoutImage(fileBytes,fileName,workoutId)
    }
}