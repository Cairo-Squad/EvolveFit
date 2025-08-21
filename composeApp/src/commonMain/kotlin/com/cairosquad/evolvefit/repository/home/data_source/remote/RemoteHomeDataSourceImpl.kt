package com.cairosquad.evolvefit.repository.home.data_source.remote

import com.cairosquad.evolvefit.repository.execption.callApi
import com.cairosquad.evolvefit.repository.home.data_source.remote.dto.WeeklyProgressResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class RemoteHomeDataSourceImpl(
    private val httpClient: HttpClient
) : RemoteHomeDataSource {
    override suspend fun getWeeklyProgress(
        startDate: String,
        endDate: String
    ): WeeklyProgressResponse {
        return callApi {
            httpClient.get("report/workoutProgress") {
                parameter("startDate", startDate)
                parameter("endDate", endDate)
            }
        }
    }
}