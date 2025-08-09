package com.cairosquad.evolvefit.remote

import com.cairosquad.evolvefit.repository.remote.AuthRemoteDataSource
import com.cairosquad.evolvefit.local.AuthPreferences
import io.ktor.client.HttpClient
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

actual fun provideHttpClient(
    authPreferences: AuthPreferences,
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
                    authPreferences.getAccessToken()?.let { access ->
                        authPreferences.getRefreshToken()?.let { refresh ->
                            BearerTokens(access, refresh)
                        }
                    }
                }
                refreshTokens {
                    val refresh = authPreferences.getRefreshToken()
                    refresh?.let { refreshTokenProvider(it) }
                }
            }
        }
    }
}