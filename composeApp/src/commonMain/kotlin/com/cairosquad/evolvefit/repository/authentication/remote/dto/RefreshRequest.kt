package com.cairosquad.evolvefit.repository.authentication.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class RefreshRequest(
    val refreshToken: String
)

