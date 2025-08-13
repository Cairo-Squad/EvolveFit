package com.cairosquad.evolvefit.repository.utils

import com.cairosquad.evolvefit.repository.authentication.local.AuthenticationPreferences
import io.ktor.client.HttpClient
import io.ktor.client.plugins.auth.providers.BearerTokens

expect fun provideHttpClient(
    authenticationPreferences: AuthenticationPreferences,
    refreshTokenProvider: RefreshTokenProvider
): HttpClient