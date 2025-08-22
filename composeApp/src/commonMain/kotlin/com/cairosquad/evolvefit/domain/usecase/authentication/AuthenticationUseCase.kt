package com.cairosquad.evolvefit.domain.usecase.authentication

import com.cairosquad.evolvefit.domain.entity.Profile
import com.cairosquad.evolvefit.domain.exception.InvalidEmailFormatException
import com.cairosquad.evolvefit.domain.exception.InvalidPasswordException
import com.cairosquad.evolvefit.domain.model.WeekDay
import com.cairosquad.evolvefit.domain.repository.AuthenticationRepository

class AuthenticationUseCase(
    private val authenticationRepository: AuthenticationRepository
) {

    suspend fun login(email: String, password: String) {
        if (!isValidEmail(email)) {
            throw InvalidEmailFormatException()
        }

        if (!isValidPassword(password)) {
            throw InvalidPasswordException()
        }

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
        if (!isValidEmail(profile.email)) {
            throw InvalidEmailFormatException()
        }
        if (!isValidPassword(password)) {
            throw InvalidPasswordException()
        }
        return authenticationRepository.register(
            profile,
            password,
            availableEquipment,
            workoutDays
        )
    }

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
        return email.trim().matches(emailRegex)
    }

    private fun isValidPassword(password: String): Boolean {
        return password.length >= 8
    }
}