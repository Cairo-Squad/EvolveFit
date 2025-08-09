package com.cairosquad.evolvefit.repository.model

data class AuthTokens(
    val accessToken: String,
    val refreshToken: String
)