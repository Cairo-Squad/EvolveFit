package com.cairosquad.evolvefit.repository.authentication

import com.cairosquad.evolvefit.domain.entity.Profile
import com.cairosquad.evolvefit.domain.model.WeekDay
import com.cairosquad.evolvefit.domain.repository.AuthenticationRepository
import com.cairosquad.evolvefit.repository.authentication.local.AuthenticationPreferences
import com.cairosquad.evolvefit.repository.authentication.remote.AuthenticationRemoteDataSource
import com.cairosquad.evolvefit.repository.authentication.remote.toRegisterRequest
import com.cairosquad.evolvefit.repository.execption.callDataSource


class AuthenticationRepositoryImpl(
    private val remote: AuthenticationRemoteDataSource,
    private val authenticationPreferences: AuthenticationPreferences,
) : AuthenticationRepository {

    override suspend fun login(email: String, password: String) = callDataSource {
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

        val response = callDataSource {
            remote.register(request)
        }
        authenticationPreferences.saveTokens(response.accessToken, response.refreshToken)
    }

    override suspend fun logout() {
        authenticationPreferences.clear()
        remote.logout()
    }

    override suspend fun isUserLoggedIn(): Boolean {
        return authenticationPreferences.getAccessToken()?.isNotEmpty() == true
    }
}