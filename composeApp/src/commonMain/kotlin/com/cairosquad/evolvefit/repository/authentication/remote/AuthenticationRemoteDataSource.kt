package com.cairosquad.evolvefit.repository.authentication.remote

import com.cairosquad.evolvefit.repository.remote.authentication.AuthResponse
import com.cairosquad.evolvefit.repository.remote.authentication.EquipmentDto
import com.cairosquad.evolvefit.repository.remote.authentication.RegisterRequest

interface AuthenticationRemoteDataSource {
    suspend fun login(email: String, password: String): AuthResponse
    suspend fun register(request: RegisterRequest): AuthResponse
    suspend fun getRefreshToken(refreshToken: String): AuthResponse
    suspend fun getEquipments(): List<EquipmentDto>
}

