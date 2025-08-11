package com.cairosquad.evolvefit.repository

import com.cairosquad.evolvefit.domain.entity.Equipment
import com.cairosquad.evolvefit.domain.entity.User
import com.cairosquad.evolvefit.domain.repository.AuthRepository
import com.cairosquad.evolvefit.repository.local.AuthPreferences
import com.cairosquad.evolvefit.repository.remote.AuthRemoteDataSource
import com.cairosquad.evolvefit.repository.remote.auth.RegisterRequest
import com.cairosquad.evolvefit.repository.remote.safeApiCall

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
            gymEquipments = user.equipments.map { it.id },
            workoutDays = user.workoutDays.map { it.name }
        )

        safeApiCall {
            val response=remote.register(request)
            prefs.saveTokens(response.accessToken, response.refreshToken)
        }
    }

    override suspend fun refreshToken() = safeApiCall{
        val newToken = remote.getRefreshToken(prefs.getRefreshToken() ?: throw Exception("No refresh token"))
        prefs.saveTokens(newToken.accessToken, newToken.refreshToken)
    }

    override suspend fun logout() {
        prefs.clear()
    }

    override suspend fun isUserLoggedIn(): Boolean {
        return prefs.getAccessToken()?.isNotEmpty() == true
    }

    override suspend fun getEquipments(): List<Equipment> {
        return emptyList() // TODO
    }
}




