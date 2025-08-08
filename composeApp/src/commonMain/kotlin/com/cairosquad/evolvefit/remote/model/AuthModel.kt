package com.cairosquad.evolvefit.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val email: String,
    val password: String
)

@Serializable
data class LoginResponse(
    val accessToken: String,
    val refreshToken: String
)

@Serializable
data class RegisterRequest(
    val name: String,
    val email: String,
    val dateOfBirth: String,
    val password: String,
    val gender: String,
    val unit: String,
    val height: Float,
    val weight: Float,
    val goal: String,
    val tools: List<String>,
    val workoutDays: List<String>
)

@Serializable
data class RefreshRequest(val refreshToken: String)

@Serializable
data class EquipmentDto(
    val id: String,
    val name: String
)