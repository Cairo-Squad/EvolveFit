package com.cairosquad.evolvefit.repository.authentication.remote.dto

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
    val gymEquipments: List<Int>,
    val workoutDays: List<String>
)

@Serializable
data class RefreshRequest(val refreshToken: String)

@Serializable
data class EquipmentDto(
    val id: Int,
    val name: String
)