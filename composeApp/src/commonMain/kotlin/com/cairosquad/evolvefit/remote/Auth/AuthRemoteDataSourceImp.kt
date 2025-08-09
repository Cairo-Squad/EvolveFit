package com.cairosquad.evolvefit.remote.Auth

import com.cairosquad.evolvefit.repository.remote.AuthRemoteDataSource
import com.cairosquad.evolvefit.repository.remote.auth.AuthResponse
import com.cairosquad.evolvefit.repository.remote.auth.EquipmentDto
import com.cairosquad.evolvefit.repository.remote.auth.LoginRequest
import com.cairosquad.evolvefit.repository.remote.auth.RefreshRequest
import com.cairosquad.evolvefit.repository.remote.auth.RegisterRequest
import com.cairosquad.evolvefit.remote.safeApiCall
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class AuthRemoteDataSourceImp(private val client: HttpClient) : AuthRemoteDataSource {
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

    override suspend fun getEquipments(): List<EquipmentDto> {
        safeApiCall {
            return client.get("/public/equipments").body()
        }
    }
}

