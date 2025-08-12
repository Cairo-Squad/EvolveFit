package com.cairosquad.evolvefit.domain.usecase.authentication

import com.cairosquad.evolvefit.domain.entity.Profile
import com.cairosquad.evolvefit.domain.model.WeekDay
import com.cairosquad.evolvefit.domain.repository.AuthenticationRepository

class AuthenticationUseCase(
    private val authenticationRepository: AuthenticationRepository
) {

    suspend fun login(email: String, password: String) {
        return authenticationRepository.login(email, password)
    }

    suspend fun isUserLoggedIn(): Boolean {
        return authenticationRepository.isUserLoggedIn()
    }

    suspend fun logout() {
        authenticationRepository.logout()
    }

    suspend fun register(
        profile: Profile,
        password: String,
        availableEquipment: Set<Int>,
        workoutDays: Set<WeekDay>
    ) {
        return authenticationRepository.register(
            profile,
            password,
            availableEquipment,
            workoutDays
        )
    }
}