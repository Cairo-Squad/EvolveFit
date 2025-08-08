package com.cairosquad.evolvefit.remote

import com.cairosquad.evolvefit.domain.AuthRemoteDataSource
import com.cairosquad.evolvefit.local.AuthPreferences
import io.ktor.client.HttpClient
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

actual fun provideHttpClient(
    authPreferences: AuthPreferences,
    authRemoteDataSource: AuthRemoteDataSource
): HttpClient {
    return HttpClient {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
        install(Logging) {
            level = LogLevel.ALL
        }
        defaultRequest {
            url("https://evolve-fit-dev.the-chance.net/")
        }
        install(Auth) {
            bearer {
                loadTokens {
                    authPreferences.getAccessToken()?.let { accessToken ->
                        authPreferences.getRefreshToken()?.let { refreshToken ->
                            BearerTokens(accessToken, refreshToken)
                        }
                    }
                }
                refreshTokens {
                    val refreshToken = authPreferences.getRefreshToken()
                    if (refreshToken != null) {
                        val newTokens = authRemoteDataSource.refreshToken(refreshToken)
                        authPreferences.saveTokens(newTokens.accessToken, newTokens.refreshToken)
                        BearerTokens(newTokens.accessToken, newTokens.refreshToken)
                    } else {
                        null
                    }
                }
            }
        }
    }
}