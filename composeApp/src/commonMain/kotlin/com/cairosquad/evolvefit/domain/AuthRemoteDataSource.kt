package com.cairosquad.evolvefit.domain

import com.cairosquad.evolvefit.remote.model.AuthResponse
import com.cairosquad.evolvefit.remote.model.EquipmentDto
import com.cairosquad.evolvefit.remote.model.RegisterRequest

interface AuthRemoteDataSource {
    suspend fun login(email: String, password: String): AuthResponse
    suspend fun register(request: RegisterRequest): AuthResponse
    suspend fun refreshToken(refreshToken: String): AuthResponse
    suspend fun getEquipments(): List<EquipmentDto>
}
