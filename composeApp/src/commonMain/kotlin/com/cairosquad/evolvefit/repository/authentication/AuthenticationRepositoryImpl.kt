package com.cairosquad.evolvefit.repository.authentication

import com.cairosquad.evolvefit.domain.entity.Equipment
import com.cairosquad.evolvefit.domain.entity.Profile
import com.cairosquad.evolvefit.domain.model.WeekDay
import com.cairosquad.evolvefit.domain.repository.AuthenticationRepository
import com.cairosquad.evolvefit.repository.local.AuthenticationPreferences
import com.cairosquad.evolvefit.repository.remote.AuthenticationRemoteDataSource
import com.cairosquad.evolvefit.repository.remote.api.safeApiCall
import com.cairosquad.evolvefit.repository.remote.authentication.RegisterRequest

class AuthenticationRepositoryImpl(
    private val remote: AuthenticationRemoteDataSource,
    private val prefs: AuthenticationPreferences
) : AuthenticationRepository {

    override suspend fun login(email: String, password: String) = safeApiCall {
        val response = remote.login(email, password)
        prefs.saveTokens(response.accessToken, response.refreshToken)
    }

    override suspend fun register(
        profile: Profile,
        password: String,
        availableEquipment: Set<Equipment>,
        workoutDays: Set<WeekDay>
    ) {
        val request = RegisterRequest.Companion.fromProfile(
            profile,
            password,
            availableEquipment,
            workoutDays
        )

        val response = safeApiCall {
            remote.register(request)
        }
        prefs.saveTokens(response.accessToken, response.refreshToken)
    }

    override suspend fun logout() {
        prefs.clear()
    }

    override suspend fun isUserLoggedIn(): Boolean {
        return prefs.getAccessToken()?.isNotEmpty() == true
    }
}