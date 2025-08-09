package com.cairosquad.evolvefit.domain

import com.cairosquad.evolvefit.entity.Tool
import com.cairosquad.evolvefit.entity.User

interface AuthRepository {
    suspend fun login(email: String, password: String)
    suspend fun register(user: User)
    suspend fun refreshToken()
    suspend fun logout()
    suspend fun isUserLoggedIn(): Boolean
    suspend fun getEquipments(): List<Tool>
}

