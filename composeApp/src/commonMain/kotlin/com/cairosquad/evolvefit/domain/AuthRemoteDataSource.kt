package com.cairosquad.evolvefit.domain

import com.cairosquad.evolvefit.remote.model.EquipmentDto
import com.cairosquad.evolvefit.remote.model.LoginResponse
import com.cairosquad.evolvefit.remote.model.RegisterRequest

interface AuthRemoteDataSource {
    suspend fun login(email: String, password: String): LoginResponse
    suspend fun register(request: RegisterRequest)
    suspend fun refreshToken(refreshToken: String): LoginResponse
    suspend fun getEquipments(): List<EquipmentDto>
}
