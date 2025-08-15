package com.cairosquad.evolvefit.repository.workout.remote

import com.cairosquad.evolvefit.repository.execption.callApi
import com.cairosquad.evolvefit.repository.workout.remote.remote.WorkoutDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class WorkoutRemoteDataSourceImpl(private val client: HttpClient): WorkoutRemoteDataSource {
    override suspend fun getFavoriteWorkout(): List<WorkoutDto> {
        return callApi { client.get("favorite/workout") }
    }

}