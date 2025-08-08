package com.cairosquad.evolvefit.Repository

import com.cairosquad.evolvefit.domain.AuthRepository
import com.cairosquad.evolvefit.domain.AuthRemoteDataSource
import com.cairosquad.evolvefit.remote.model.RegisterRequest
import com.cairosquad.evolvefit.entity.User
import com.cairosquad.evolvefit.local.AuthPreferences
import com.cairosquad.evolvefit.remote.safeApiCall

class AuthRepositoryImpl(
    private val remote: AuthRemoteDataSource,
    private val prefs: AuthPreferences
) : AuthRepository {

    override suspend fun login(email: String, password: String) = safeApiCall {
        val response = remote.login(email, password)
        prefs.saveTokens(response.accessToken, response.refreshToken)
    }

    override suspend fun register(user: User)  {
        val request = RegisterRequest(
            name = user.name,
            email = user.email,
            dateOfBirth = user.dateOfBirth,
            password = user.password,
            gender = user.gender.name.lowercase(),
            unit = user.unit.name.lowercase(),
            height = user.height,
            weight = user.weight,
            goal = user.goal.name.lowercase(),
            tools = user.tools.map { it.name.lowercase() },
            workoutDays = user.workoutDays.map { it.name.lowercase() }
        )

        safeApiCall {
            remote.register(request)
        }
    }

    override suspend fun refreshToken() = safeApiCall{
        val newToken = remote.refreshToken(prefs.getRefreshToken() ?: throw Exception("No refresh token"))
        prefs.saveTokens(newToken.accessToken, newToken.refreshToken)
    }

    override suspend fun logout() {
        prefs.clear()
    }

    override suspend fun isUserLoggedIn(): Boolean {
        return prefs.getAccessToken()?.isNotEmpty() == true
    }
}




