package com.cairosquad.evolvefit.domain

import com.cairosquad.evolvefit.entity.User

interface AuthRepository {
    suspend fun register(user: User)
    suspend fun login(email: String, password: String)
    suspend fun isUserLoggedIn(): Boolean
    suspend fun logout()
}

