package com.cairosquad.evolvefit.domain.usecase.authentication

import com.cairosquad.evolvefit.domain.entity.Equipment
import com.cairosquad.evolvefit.domain.entity.User
import com.cairosquad.evolvefit.domain.repository.AuthRepository

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

    suspend fun getEquipments(): List<Equipment> {
        return authRepository.getEquipments()
    }
}