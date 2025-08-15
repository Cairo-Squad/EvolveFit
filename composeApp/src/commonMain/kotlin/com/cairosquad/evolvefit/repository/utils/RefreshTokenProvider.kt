package com.cairosquad.evolvefit.repository.utils

import com.cairosquad.evolvefit.repository.authentication.remote.dto.AuthResponse
import com.cairosquad.evolvefit.repository.authentication.remote.dto.RefreshRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.content
import io.ktor.http.ContentType
import io.ktor.http.contentType

class RefreshTokenProvider(private val client: HttpClient) {
    suspend fun getNewTokens(refreshToken: String): AuthResponse {
        return client.post("https://evolve-fit-dev.the-chance.net/auth/refresh") {
            contentType(ContentType.Application.Json)
            setBody(RefreshRequest(refreshToken))
        }.body()
    }
}