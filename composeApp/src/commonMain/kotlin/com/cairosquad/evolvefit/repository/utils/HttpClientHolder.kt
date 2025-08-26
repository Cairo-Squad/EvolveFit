package com.cairosquad.evolvefit.repository.utils

import com.cairosquad.evolvefit.repository.authentication.local.AuthenticationPreferences
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

expect val platformHttpClientEngineFactory: HttpClientEngineFactory<HttpClientEngineConfig>

class HttpClientHolder(
    private val authenticationPreferences: AuthenticationPreferences,
) {
    private var client: HttpClient = buildClient()

    fun clearTokens() {
        client.close()
        client = buildClient()
    }

    suspend fun post(
        urlString: String,
        block: HttpRequestBuilder.() -> Unit = {}
    ): HttpResponse {
        return client.post(urlString, block)
    }

    suspend fun get(
        urlString: String,
        block: HttpRequestBuilder.() -> Unit = {}
    ): HttpResponse {
        return client.get(urlString, block)
    }

    suspend fun put(
        urlString: String,
        block: HttpRequestBuilder.() -> Unit = {}
    ): HttpResponse {
        return client.put(urlString, block)
    }

    suspend fun delete(
        urlString: String,
        block: HttpRequestBuilder.() -> Unit = {}
    ): HttpResponse {
        return client.delete(urlString, block)
    }

    private suspend fun getNewTokens(
        refreshToken: String,
        client: HttpClient
    ): BearerTokens? {
        return try {
            client.post(REFRESH_TOKENS_ENDPOINT) {
                contentType(ContentType.Application.Json)
                setBody(object { val refreshToken = refreshToken })
            }.body()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun buildClient(): HttpClient {
        return HttpClient(platformHttpClientEngineFactory) {
            defaultRequest { url(BASE_URL) }

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

            install(Logging) { level = LogLevel.ALL }

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
                        val oldRefreshToken = oldTokens?.refreshToken ?: ""
                        getNewTokens(oldRefreshToken, client)
                    }
                }
            }
        }
    }

    companion object {
        private const val BASE_URL = "https://evolve-fit-dev.the-chance.net/"
        private const val REFRESH_TOKENS_ENDPOINT = "auth/refresh"
    }
}