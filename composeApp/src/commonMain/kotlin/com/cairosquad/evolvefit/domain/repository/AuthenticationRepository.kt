package com.cairosquad.evolvefit.domain.repository

import com.cairosquad.evolvefit.domain.entity.Profile
import com.cairosquad.evolvefit.domain.model.WeekDay
import com.cairosquad.evolvefit.repository.authentication.remote.dto.AuthResponse

interface AuthenticationRepository {
    suspend fun login(email: String, password: String)
    suspend fun register(
        profile: Profile,
        password: String,
        availableEquipment: Set<Int>,
        workoutDays: Set<WeekDay>
    )
    suspend fun logout()
    suspend fun isUserLoggedIn(): Boolean
}