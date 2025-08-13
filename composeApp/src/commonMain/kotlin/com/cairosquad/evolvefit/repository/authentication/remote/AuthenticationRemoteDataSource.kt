package com.cairosquad.evolvefit.repository.authentication.remote

import com.cairosquad.evolvefit.repository.authentication.remote.dto.AuthResponse
import com.cairosquad.evolvefit.repository.authentication.remote.dto.RegisterRequest

interface AuthenticationRemoteDataSource {
    suspend fun login(email: String, password: String): AuthResponse
    suspend fun register(request: RegisterRequest): AuthResponse
}