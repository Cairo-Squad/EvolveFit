package com.cairosquad.evolvefit.domain

import com.cairosquad.evolvefit.Repository.model.AuthTokens
import com.cairosquad.evolvefit.entity.User

// domain/repository/AuthRepositoryImpl.kt
interface AuthRepository {
    suspend fun login(email: String, password: String)
    suspend fun register(user: User)
    suspend fun refreshToken()
    suspend fun logout()
    suspend fun isUserLoggedIn(): Boolean
}

