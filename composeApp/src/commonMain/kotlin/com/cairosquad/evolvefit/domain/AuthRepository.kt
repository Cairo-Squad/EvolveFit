package com.cairosquad.evolvefit.domain

import com.cairosquad.evolvefit.entity.AuthTokens
import com.cairosquad.evolvefit.entity.User

interface AuthRepository {
    suspend fun register(user: User): AuthTokens
    suspend fun login(email: String, password: String): AuthTokens
    suspend fun isUserLoggedIn(): Boolean
    suspend fun logout()
}

