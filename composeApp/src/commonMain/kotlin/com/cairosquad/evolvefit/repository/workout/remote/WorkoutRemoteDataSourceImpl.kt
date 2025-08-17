package com.cairosquad.evolvefit.repository.workout.remote

import com.cairosquad.evolvefit.domain.model.FocusArea
import com.cairosquad.evolvefit.repository.execption.callApi
import com.cairosquad.evolvefit.repository.workout.remote.dto.CreateWorkoutRequest
import com.cairosquad.evolvefit.repository.workout.remote.dto.WorkoutDetailsDto
import com.cairosquad.evolvefit.repository.workout.remote.dto.WorkoutDto
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
            client.post("workout/create") {
                contentType(ContentType.Application.Json)
                setBody(request)
            }.body()
        }
    }

    override suspend fun getSuggestedWorkouts(): List<WorkoutDto> {
        return callApi<List<WorkoutDto>> {
            client.get("workout/suggested") {
                contentType(ContentType.Application.Json)
            }.body()
        }
    }

    override suspend fun getCommunityWorkouts(): List<WorkoutDto> {
        return callApi<List<WorkoutDto>> {
            client.get("workout/community") {
                contentType(ContentType.Application.Json)
            }.body()
        }
    }

    override suspend fun getFavoriteWorkout(): List<WorkoutDto> {
        return callApi<List<WorkoutDto>> { client.get("favorite/workout") }
    }

    override suspend fun getCommunityWorkoutsByFocusArea(focusArea: FocusArea): List<WorkoutDto> {
        return callApi<List<WorkoutDto>> {
            client.get("workout/community") {
                contentType(ContentType.Application.Json)
                parameter("focusArea", focusArea.name)
            }.body()
        }
    }

    override suspend fun addFavoriteWorkOut(workOutId: String) {
        return callApi {
            client.post("favorite/workout") {
                parameter("workoutId", workOutId)
                contentType(ContentType.Application.Json)
            }.body()
        }
    }

    override suspend fun deleteFavoriteWorkOut(workOutId: String) {
        return callApi {
            client.delete("favorite/workout") {
                parameter("workoutId", workOutId)
                contentType(ContentType.Application.Json)
            }.body()
        }
    }

    override suspend fun getWorkoutsByFocusArea(focusArea: FocusArea): List<WorkoutDto> {
        return callApi<List<WorkoutDto>> {
            client.get("workout/suggested") {
                contentType(ContentType.Application.Json)
                parameter("focusArea", focusArea.name)
            }.body()
        }
    }

    override suspend fun getWorkoutDetails(workoutId: String): WorkoutDetailsDto {
        return callApi<WorkoutDetailsDto> {
            client.get("workout/details") {
                contentType(ContentType.Application.Json)
                parameter("workoutId", workoutId)
            }.body()
        }
    }
}