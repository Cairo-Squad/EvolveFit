package com.cairosquad.evolvefit.repository.authentication

import com.cairosquad.evolvefit.domain.entity.Profile
import com.cairosquad.evolvefit.domain.model.WeekDay
import com.cairosquad.evolvefit.domain.repository.AuthenticationRepository
import com.cairosquad.evolvefit.repository.authentication.local.AuthenticationPreferences
import com.cairosquad.evolvefit.repository.authentication.remote.AuthenticationRemoteDataSource
import com.cairosquad.evolvefit.repository.authentication.remote.dto.AuthResponse
import com.cairosquad.evolvefit.repository.authentication.remote.toRegisterRequest
import com.cairosquad.evolvefit.repository.utils.safeCallDataSource

class AuthenticationRepositoryImpl(
    private val remote: AuthenticationRemoteDataSource,
    private val authenticationPreferences: AuthenticationPreferences,
) : AuthenticationRepository {

    override suspend fun login(email: String, password: String) = safeCallDataSource {
        val response = remote.login(email, password)
        authenticationPreferences.saveTokens(response.accessToken, response.refreshToken)
    }

    override suspend fun register(
        profile: Profile,
        password: String,
        availableEquipment: Set<Int>,
        workoutDays: Set<WeekDay>
    ) {
        val request = profile.toRegisterRequest(
            password,
            availableEquipment,
            workoutDays
        )

        val response = safeCallDataSource {
            remote.register(request)
        }
        authenticationPreferences.saveTokens(response.accessToken, response.refreshToken)
    }

    override suspend fun logout() {
        authenticationPreferences.clear()
    }

    override suspend fun isUserLoggedIn(): Boolean {
        return authenticationPreferences.getAccessToken()?.isNotEmpty() == true
    }

    override suspend fun refreshToken(refreshToken: String): AuthResponse {
        return safeCallDataSource {
            val response = remote.getRefreshToken(refreshToken)
            authenticationPreferences.saveTokens(response.accessToken, response.refreshToken)
            response
        }
    }
}