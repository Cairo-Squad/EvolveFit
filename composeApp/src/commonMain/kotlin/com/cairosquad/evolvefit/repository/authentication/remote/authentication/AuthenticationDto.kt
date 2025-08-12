package com.cairosquad.evolvefit.repository.authentication.remote.authentication

import com.cairosquad.evolvefit.domain.entity.Equipment
import com.cairosquad.evolvefit.domain.entity.Profile
import com.cairosquad.evolvefit.domain.model.WeekDay
import com.cairosquad.evolvefit.repository.utils.toRemoteDto
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val email: String,
    val password: String
)

@Serializable
data class AuthResponse(
    val accessToken: String,
    val refreshToken: String
)

@Serializable
data class RegisterRequest(
    val fullName: String,
    val email: String,
    val birthdate: String,
    val password: String,
    val gender: String,
    val measurementType: String,
    val height: Float,
    val weight: Float,
    val goal: String,
    val gymEquipments: List<Long>,
    val workoutDays: List<String>
) {
    companion object {
        fun fromProfile(
            profile: Profile,
            password: String,
            availableEquipment: Set<Equipment>,
            workoutDays: Set<WeekDay>
        ): RegisterRequest {
            return RegisterRequest(
                fullName = profile.name,
                email = profile.email,
                birthdate = profile.dateOfBirth.toRemoteDto(),
                password = password,
                gender = profile.gender.name,
                measurementType = profile.preferredMeasurementStandard.name,
                height = profile.height,
                weight = profile.weight,
                goal = profile.goal.name,
                gymEquipments = availableEquipment.map { it.id },
                workoutDays = workoutDays.map { it.name }
            )
        }
    }
}

@Serializable
data class RefreshRequest(val refreshToken: String)

@Serializable
data class EquipmentDto(
    val id: String,
    val name: String
)