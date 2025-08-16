package com.cairosquad.evolvefit.repository.profile.remote

import com.cairosquad.evolvefit.repository.execption.callApi
import com.cairosquad.evolvefit.repository.profile.dto.ProfileResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class RemoteProfileDataSourceImpl(
    private val httpClient: HttpClient
) : RemoteProfileDataSource {
    override suspend fun getProfile(): ProfileResponse {
        return callApi {
            httpClient.get("user/profile").body()
        }
    }
}