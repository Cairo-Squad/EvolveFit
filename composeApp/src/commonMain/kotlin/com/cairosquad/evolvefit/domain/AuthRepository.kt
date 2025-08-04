package com.cairosquad.evolvefit.domain

import com.cairosquad.evolvefit.entity.User

interface AuthRepository {
    suspend fun register(data: User): Result<Unit>

    suspend fun login(username: String, password: String) : Result<User>

    suspend fun isUserLoggedIn(): Boolean

    suspend fun logout()
}
