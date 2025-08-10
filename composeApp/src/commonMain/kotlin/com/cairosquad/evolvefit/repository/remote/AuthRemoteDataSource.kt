package com.cairosquad.evolvefit.repository.remote

import com.cairosquad.evolvefit.repository.remote.auth.AuthResponse
import com.cairosquad.evolvefit.repository.remote.auth.EquipmentDto
import com.cairosquad.evolvefit.repository.remote.auth.RegisterRequest

interface AuthRemoteDataSource {
    suspend fun login(email: String, password: String): AuthResponse
    suspend fun register(request: RegisterRequest): AuthResponse
    suspend fun getRefreshToken(refreshToken: String): AuthResponse
    suspend fun getEquipments(): List<EquipmentDto>
}

