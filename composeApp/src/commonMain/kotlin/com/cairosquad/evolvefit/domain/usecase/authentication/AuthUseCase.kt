package com.cairosquad.evolvefit.domain.usecase.authentication

import com.cairosquad.evolvefit.domain.AuthRepository
import com.cairosquad.evolvefit.entity.User

class AuthUseCase(
    private val repository: AuthRepository
) {

    suspend  fun login(email: String, password: String) {
        return repository.login(email, password)
    }

    suspend  fun isUserLoggedIn(): Boolean {
        return repository.isUserLoggedIn()
    }

    suspend  fun logout() {
        repository.logout()
    }
    suspend  fun register(user: User) {
        return repository.register(user)
    }
}