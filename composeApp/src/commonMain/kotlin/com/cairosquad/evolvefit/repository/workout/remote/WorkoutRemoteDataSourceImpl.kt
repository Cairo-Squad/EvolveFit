package com.cairosquad.evolvefit.repository.workout.remote

import com.cairosquad.evolvefit.domain.model.FocusArea
import com.cairosquad.evolvefit.repository.execption.callApi
import com.cairosquad.evolvefit.repository.workout.remote.dto.CreateWorkoutRequest
import com.cairosquad.evolvefit.repository.workout.remote.dto.FavoritesWorkoutDto
import com.cairosquad.evolvefit.repository.workout.remote.dto.WorkoutDetailsDto
import com.cairosquad.evolvefit.repository.workout.remote.dto.WorkoutDto
import com.cairosquad.evolvefit.repository.workout.remote.dto.WorkoutHistoryDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class WorkoutRemoteDataSourceImpl(
    private val client: HttpClient
) : WorkoutRemoteDataSource {

    override suspend fun createWorkout(request: CreateWorkoutRequest) {
        return callApi {
            client.post("$WORKOUT_PATH/create") {
                contentType(ContentType.Application.Json)
                setBody(request)
            }.body()
        }
    }

    override suspend fun getSuggestedWorkouts(): List<WorkoutDto> {
        return callApi {
            client.get("$WORKOUT_PATH/suggested") {
                contentType(ContentType.Application.Json)
            }.body()
        }
    }

    override suspend fun getCommunityWorkouts(): List<WorkoutDto> {
        return callApi {
            client.get("$WORKOUT_PATH/community") {
                contentType(ContentType.Application.Json)
            }.body()
        }
    }

    override suspend fun getFavoriteWorkout(): List<FavoritesWorkoutDto> {
        return callApi {
            client.get(FAVORITE_WORKOUT).body()
        }
    }

    override suspend fun getCommunityWorkoutsByFocusArea(focusArea: FocusArea): List<WorkoutDto> {
        return callApi {
            client.get("$WORKOUT_PATH/community") {
                contentType(ContentType.Application.Json)
                parameter("focusArea", focusArea.name)
            }.body()
        }
    }

    override suspend fun addFavoriteWorkOut(workOutId: String) {
        return callApi {
            client.post(FAVORITE_WORKOUT) {
                parameter("workoutId", workOutId)
                contentType(ContentType.Application.Json)
            }.body()
        }
    }

    override suspend fun deleteFavoriteWorkOut(workOutId: String) {
        return callApi {
            client.delete(FAVORITE_WORKOUT) {
                parameter("workoutId", workOutId)
                contentType(ContentType.Application.Json)
            }.body()
        }
    }

    override suspend fun getWorkoutHistory(): List<WorkoutHistoryDto> {
        return callApi { client.get("$WORKOUT_PATH/history").body() }
    }

    override suspend fun getWorkoutsByFocusArea(focusArea: FocusArea): List<WorkoutDto> {
        return callApi {
            client.get("$WORKOUT_PATH/suggested") {
                contentType(ContentType.Application.Json)
                parameter("focusArea", focusArea.name)
            }.body()
        }
    }

    override suspend fun getWorkoutDetails(workoutId: String): WorkoutDetailsDto {
        return callApi {
            client.get("$WORKOUT_PATH/details") {
                contentType(ContentType.Application.Json)
                parameter("workoutId", workoutId)
            }.body()
        }
    }

    companion object {
        private const val FAVORITE_WORKOUT = "favorite/workout"
        private const val WORKOUT_PATH = "workout"
    }
}