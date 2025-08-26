package com.cairosquad.evolvefit.repository.home.data_source.remote

import com.cairosquad.evolvefit.repository.execption.callApi
import com.cairosquad.evolvefit.repository.home.data_source.remote.dto.WeeklyProgressResponse
import com.cairosquad.evolvefit.repository.utils.HttpClientHolder
import io.ktor.client.request.parameter

class HomeRemoteDataSourceImpl(
    private val httpClient: HttpClientHolder
) : HomeRemoteDataSource {
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