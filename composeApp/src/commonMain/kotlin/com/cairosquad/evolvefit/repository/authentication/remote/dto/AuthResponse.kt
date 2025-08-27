package com.cairosquad.evolvefit.repository.authentication.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(
    val accessToken: String,
    val refreshToken: String
)
