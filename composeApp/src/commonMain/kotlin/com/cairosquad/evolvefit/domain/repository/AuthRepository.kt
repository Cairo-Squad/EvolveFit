package com.cairosquad.evolvefit.domain.repository

import com.cairosquad.evolvefit.domain.entity.Equipment
import com.cairosquad.evolvefit.domain.entity.User

interface AuthRepository {
    suspend fun login(email: String, password: String)
    suspend fun register(user: User)
    suspend fun refreshToken()
    suspend fun logout()
    suspend fun isUserLoggedIn(): Boolean
    suspend fun getEquipments(): List<Equipment>
}