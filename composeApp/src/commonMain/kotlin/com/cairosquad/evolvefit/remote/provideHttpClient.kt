package com.cairosquad.evolvefit.remote

import com.cairosquad.evolvefit.local.AuthPreferences
import io.ktor.client.HttpClient
import io.ktor.client.plugins.auth.providers.BearerTokens

expect fun provideHttpClient(
    authPreferences: AuthPreferences,
    refreshTokenProvider: suspend (String) -> BearerTokens?
): HttpClient