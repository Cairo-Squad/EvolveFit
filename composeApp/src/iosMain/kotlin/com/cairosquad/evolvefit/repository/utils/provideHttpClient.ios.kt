package com.cairosquad.evolvefit.repository.utils

import com.cairosquad.evolvefit.repository.authentication.local.AuthenticationPreferences
import com.cairosquad.evolvefit.repository.authentication.remote.dto.AuthResponse
import com.cairosquad.evolvefit.repository.authentication.remote.dto.RefreshRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.darwin.Darwin
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

actual fun provideHttpClient(
    authenticationPreferences: AuthenticationPreferences,
    refreshTokenProvider: RefreshTokenProvider
): HttpClient {
    return HttpClient(Darwin) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
                encodeDefaults = true
            })
        }

        defaultRequest {
            url("https://evolve-fit-dev.the-chance.net/")
        }

        install(Auth) {
            bearer {
                loadTokens {
                    val access = authenticationPreferences.getAccessToken()
                    val refresh = authenticationPreferences.getRefreshToken()
                    if (access != null && refresh != null) {
                        BearerTokens(access, refresh)
                    } else null
                }

                refreshTokens {
                    val refresh = authenticationPreferences.getRefreshToken()
                    if (refresh != null) {
                        val newTokens = refreshTokenProvider.getNewTokens(refresh)
                        if (newTokens != null) {
                            authenticationPreferences.saveTokens(
                                newTokens.accessToken,
                                newTokens.refreshToken
                            )
                        }
                        newTokens
                    } else null
                }
            }
        }

        install(Logging) {
            level = LogLevel.BODY
        }
    }
}