package com.cairosquad.evolvefit.repository.utils

import com.cairosquad.evolvefit.repository.authentication.local.AuthenticationPreferences
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
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
    refreshTokenProvider: RefreshTokenProvider
): HttpClient {
    return HttpClient(Android) {
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                    encodeDefaults = true
                }
            )
        }

        defaultRequest { url("https://evolve-fit-dev.the-chance.net/") }

        install(Auth) {
            bearer {
                loadTokens {
                    val access = authenticationPreferences.getAccessToken()
                    println("accessTokens from  loadTokens : $access")
                    val refresh = authenticationPreferences.getRefreshToken()
                    println("refreshTokens from  loadTokens : $refresh")
                    if (access != null && refresh != null)
                        BearerTokens(access, refresh)
                    else null
                }
                refreshTokens {
                    val refresh = authenticationPreferences.getRefreshToken()
                    println("refreshTokens from  provideHttpClient : $refresh")
                      if (refresh != null) {
                    println("refreshTokens is not null")
                    try {
                        val newTokens = refreshTokenProvider.getNewTokens(refresh)
                        authenticationPreferences.saveTokens(
                            newTokens.accessToken,
                            newTokens.refreshToken
                        )
                        BearerTokens(newTokens.accessToken, newTokens.refreshToken)
                    } catch (e: Exception) {
                        e.printStackTrace()
                        null
                    }
                }
                  else null
            }}
        }

        install(Logging) {
            level = LogLevel.ALL
        }
    }
}