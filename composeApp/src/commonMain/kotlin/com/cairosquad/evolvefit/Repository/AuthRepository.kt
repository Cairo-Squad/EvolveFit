package com.cairosquad.evolvefit.Repository

import com.cairosquad.evolvefit.domain.AuthRepository
import com.cairosquad.evolvefit.entity.AuthTokens
import com.cairosquad.evolvefit.entity.User

class FakeAuthRepository : AuthRepository {

    private var tokens: AuthTokens? = null

    override suspend fun register(user: User): AuthTokens {
        tokens = AuthTokens(
            accessToken = "fake_access_token",
            refreshToken = "fake_refresh_token"
        )
        return tokens!!
    }

    override suspend fun login(email: String, password: String): AuthTokens {
        tokens = AuthTokens(
            accessToken = "fake_access_token",
            refreshToken = "fake_refresh_token"
        )
        return tokens!!
    }

    override suspend fun isUserLoggedIn(): Boolean {
        return tokens != null
    }

    override suspend fun logout() {
        tokens = null
    }
}


