package com.cairosquad.evolvefit.repository.authentication.remote

import com.cairosquad.evolvefit.repository.authentication.remote.dto.AuthResponse
import com.cairosquad.evolvefit.repository.authentication.remote.dto.LoginRequest
import com.cairosquad.evolvefit.repository.authentication.remote.dto.RegisterRequest
import com.cairosquad.evolvefit.repository.execption.callApi
import com.cairosquad.evolvefit.repository.utils.HttpClientHolder
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class AuthenticationRemoteDataSourceImpl(
    private val client: HttpClientHolder
) :
    AuthenticationRemoteDataSource {
    override suspend fun login(email: String, password: String): AuthResponse {
        return callApi<AuthResponse> {
            client.post("auth/login") {
                contentType(ContentType.Application.Json)
                setBody(LoginRequest(email, password))
            }
        }
    }

    override suspend fun register(request: RegisterRequest): AuthResponse {
        return callApi<AuthResponse> {
            client.post("auth/signup") {
                contentType(ContentType.Application.Json)
                setBody(request)
            }
        }
    }

    override suspend fun logout() {
        client.clearTokens()
    }
}