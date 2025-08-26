package com.cairosquad.evolvefit.repository.authentication.remote.dto

import kotlinx.serialization.Serializable

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