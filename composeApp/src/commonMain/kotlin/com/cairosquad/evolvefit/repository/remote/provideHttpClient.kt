package com.cairosquad.evolvefit.repository.remote

import com.cairosquad.evolvefit.repository.local.AuthPreferences
import io.ktor.client.HttpClient
import io.ktor.client.plugins.auth.providers.BearerTokens

expect fun provideHttpClient(
    authPreferences: AuthPreferences,
    refreshTokenProvider: suspend (String) -> BearerTokens?
): HttpClient