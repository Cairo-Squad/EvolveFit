package com.cairosquad.evolvefit.repository.utils

import com.cairosquad.evolvefit.repository.local.AuthenticationPreferences
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
    authenticationPreferences: AuthenticationPreferences,
    refreshTokenProvider: suspend (String) -> BearerTokens?
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
                    authenticationPreferences.getAccessToken()?.let { access ->
                        authenticationPreferences.getRefreshToken()?.let { refresh ->
                            BearerTokens(access, refresh)
                        }
                    }
                }
                refreshTokens {
                    val refresh = authenticationPreferences.getRefreshToken()
                    refresh?.let { refreshTokenProvider(it) }
                }
            }
        }
    }
}