package com.cairosquad.evolvefit.domain.usecase.authentication

import com.cairosquad.evolvefit.domain.AuthRepository
import com.cairosquad.evolvefit.entity.Tool
import com.cairosquad.evolvefit.entity.User

class AuthUseCase(
    private val authRepository: AuthRepository
) {

    suspend  fun login(email: String, password: String) {
        return authRepository.login(email, password)
    }
    suspend  fun isUserLoggedIn(): Boolean {
        return authRepository.isUserLoggedIn()
    }
    suspend  fun logout() {
        authRepository.logout()
    }
    suspend  fun register(user: User) {
        return authRepository.register(user)
    }

    suspend fun getEquipments(): List<Tool> {
        return authRepository.getEquipments()
    }
}