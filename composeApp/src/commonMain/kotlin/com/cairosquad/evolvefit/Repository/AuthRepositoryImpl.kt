package com.cairosquad.evolvefit.Repository

import com.cairosquad.evolvefit.domain.AuthRemoteDataSource
import com.cairosquad.evolvefit.domain.AuthRepository
import com.cairosquad.evolvefit.entity.Tool
import com.cairosquad.evolvefit.entity.User
import com.cairosquad.evolvefit.local.AuthPreferences
import com.cairosquad.evolvefit.remote.model.RegisterRequest
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
            fullName = user.name,
            email = user.email,
            birthdate = user.dateOfBirth,
            password = user.password,
            gender = user.gender.name,
            measurementType = user.unit.name,
            height = user.height,
            weight = user.weight,
            goal = user.goal.name,
            gymEquipments = user.tools.map { it.id },
            workoutDays = user.workoutDays.map { it.name }
        )

        safeApiCall {
            val response=remote.register(request)
            prefs.saveTokens(response.accessToken, response.refreshToken)
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

    override suspend fun getEquipments(): List<Tool> {
        //return remote.getEquipments().map { it.toDomain() }
        TODO()
    }
}




