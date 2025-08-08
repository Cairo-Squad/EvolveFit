package com.cairosquad.evolvefit.remote.Auth

import com.cairosquad.evolvefit.domain.AuthRemoteDataSource
import com.cairosquad.evolvefit.remote.model.EquipmentDto
import com.cairosquad.evolvefit.remote.model.LoginRequest
import com.cairosquad.evolvefit.remote.model.LoginResponse
import com.cairosquad.evolvefit.remote.model.RefreshRequest
import com.cairosquad.evolvefit.remote.model.RegisterRequest
import com.cairosquad.evolvefit.remote.safeApiCall
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class AuthRemoteDataSourceImp(private val client: HttpClient) : AuthRemoteDataSource {
    override suspend fun login(email: String, password: String): LoginResponse {
        return client.post("auth/login") {
            contentType(ContentType.Application.Json)
            setBody(LoginRequest(email, password))
        }.body()
    }

    override suspend fun register(request: RegisterRequest) {
        client.post("auth/signup") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }
    }

    override suspend fun refreshToken(refreshToken: String): LoginResponse {
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

