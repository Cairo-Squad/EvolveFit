package com.cairosquad.evolvefit.repository.authentication.remote

import com.cairosquad.evolvefit.repository.authentication.remote.dto.AuthResponse
import com.cairosquad.evolvefit.repository.authentication.remote.dto.LoginRequest
import com.cairosquad.evolvefit.repository.authentication.remote.dto.RefreshRequest
import com.cairosquad.evolvefit.repository.authentication.remote.dto.RegisterRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class AuthenticationRemoteDataSourceImpl(private val client: HttpClient) :
    AuthenticationRemoteDataSource {
    override suspend fun login(email: String, password: String): AuthResponse {
        return client.post("auth/login") {
            contentType(ContentType.Application.Json)
            setBody(LoginRequest(email, password))
        }.body()
    }

    override suspend fun register(request: RegisterRequest): AuthResponse {
        return client.post("auth/signup") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body<AuthResponse>()
    }

    override suspend fun getRefreshToken(refreshToken: String): AuthResponse {
        return client.post("auth/refresh") {
            contentType(ContentType.Application.Json)
            setBody(RefreshRequest(refreshToken))
        }.body()
    }
}