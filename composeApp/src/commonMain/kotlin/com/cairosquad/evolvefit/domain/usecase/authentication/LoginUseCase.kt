package com.cairosquad.evolvefit.domain.usecase.authentication

import com.cairosquad.evolvefit.domain.AuthRepository
import com.cairosquad.evolvefit.entity.AuthTokens
import com.cairosquad.evolvefit.entity.User

class LoginUseCase(
    private val repository: AuthRepository
) {
    suspend  fun login(username: String, password: String): AuthTokens {
        return repository.login(username, password)
    }

    suspend  fun isUserLoggedIn(): Boolean {
        return repository.isUserLoggedIn()
    }

    suspend  fun logout() {
        repository.logout()
    }
}

