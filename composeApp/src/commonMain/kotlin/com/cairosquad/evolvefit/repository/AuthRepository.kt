package com.cairosquad.evolvefit.repository

import com.cairosquad.evolvefit.domain.AuthRepository
import com.cairosquad.evolvefit.repository.model.AuthTokens
import com.cairosquad.evolvefit.entity.User

class FakeAuthRepository : AuthRepository {

    private var tokens: AuthTokens? = null

    override suspend fun register(user: User) {
        tokens = AuthTokens(
            accessToken = "fake_access_token",
            refreshToken = "fake_refresh_token"
        )
    }

    override suspend fun login(email: String, password: String) {
        tokens = AuthTokens(
            accessToken = "fake_access_token",
            refreshToken = "fake_refresh_token"
        )
    }

    override suspend fun isUserLoggedIn(): Boolean {
        return tokens != null
    }

    override suspend fun logout() {
        tokens = null
    }
}


