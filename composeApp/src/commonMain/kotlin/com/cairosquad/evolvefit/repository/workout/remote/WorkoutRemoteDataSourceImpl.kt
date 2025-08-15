package com.cairosquad.evolvefit.repository.workout.remote

import com.cairosquad.evolvefit.domain.model.FocusArea
import com.cairosquad.evolvefit.repository.workout.remote.dto.CreateWorkoutRequest
import com.cairosquad.evolvefit.repository.workout.remote.dto.WorkoutDetailsDto
import com.cairosquad.evolvefit.repository.workout.remote.dto.WorkoutDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
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
        return client.post("workout/create") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }

    override suspend fun getSuggestedWorkouts(): List<WorkoutDto> {
        val res= client.get("workout/suggested") {
            contentType(ContentType.Application.Json)
        }
        println(res)
        return res.body()
    }

    override suspend fun getCommunityWorkouts(): List<WorkoutDetailsDto> {
        return client.get("workout/community"){
            contentType(ContentType.Application.Json)
        }.body()
    }

    override suspend fun getCommunityWorkoutsByFocusArea(focusArea: FocusArea): List<WorkoutDetailsDto> {
        return client.get("workout/community"){
            contentType(ContentType.Application.Json)
            parameter("focusArea",focusArea.name)
        }.body()
    }

    override suspend fun getWorkoutsByFocusArea(focusArea: FocusArea): List<WorkoutDetailsDto> {
        return client.get("workout/all") {
            contentType(ContentType.Application.Json)
            parameter("focusArea", focusArea.name)
        }.body()
    }
}