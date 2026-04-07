package com.cairosquad.evolvefit.repository.utils

import com.cairosquad.evolvefit.repository.authentication.local.AuthenticationPreferences
import com.cairosquad.evolvefit.repository.authentication.remote.dto.AuthResponse
import com.cairosquad.evolvefit.repository.authentication.remote.dto.RefreshRequest
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.engine.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.serialization.json.Json
import kotlin.concurrent.Volatile

expect val platformHttpClientEngineFactory: HttpClientEngineFactory<HttpClientEngineConfig>

class HttpClientHolder(
    private val authPrefs: AuthenticationPreferences,
    private val baseUrlProvider: BaseUrlProvider
) {

    private val initMutex = Mutex()

    @Volatile
    private var client: HttpClient = createClient()

    suspend fun get(url: String, block: HttpRequestBuilder.() -> Unit = {}) =
        execute { it.get(url, block) }

    suspend fun post(url: String, block: HttpRequestBuilder.() -> Unit = {}) =
        execute { it.post(url, block) }

    suspend fun put(url: String, block: HttpRequestBuilder.() -> Unit = {}) =
        execute { it.put(url, block) }

    suspend fun delete(url: String, block: HttpRequestBuilder.() -> Unit = {}) =
        execute { it.delete(url, block) }

    fun clearTokens() {
        rebuildClient(baseUrlProvider.baseUrl)
    }

    private suspend fun execute(block: suspend (HttpClient) -> HttpResponse): HttpResponse {
        ensureInitialized()
        return block(client)
    }

    private suspend fun ensureInitialized() {
        if (baseUrlProvider.baseUrl.isNotBlank()) return

        initMutex.withLock {
            if (baseUrlProvider.baseUrl.isNotBlank()) return
            val url = fetchBaseUrl()
            rebuildClient(url)
        }
    }

    private fun rebuildClient(newBaseUrl: String) {
        client.close()
        baseUrlProvider.baseUrl = newBaseUrl
        client = createClient()
    }

    private suspend fun fetchBaseUrl(): String {
        return simpleClient().use { temp ->
            val response: Map<String, String> =
                temp.get("https://evolve-fit-registry.vercel.app/tunnel-url").body()

            response["url"] ?: error("Missing base URL")
        }
    }

    private suspend fun refreshTokens(oldRefresh: String): BearerTokens? {
        return runCatching {
            simpleClient().use { temp ->
                temp.post(REFRESH_ENDPOINT) {
                    contentType(ContentType.Application.Json)
                    setBody(RefreshRequest(oldRefresh))
                }.body<AuthResponse>()
            }.toTokens().also {
                authPrefs.saveTokens(it.accessToken, it.refreshToken)
            }
        }.getOrNull()
    }

    private fun createClient(): HttpClient {
        return HttpClient(platformHttpClientEngineFactory) {
            installCommon()

            defaultRequest {
                url(baseUrlProvider.baseUrl)
            }

            install(Auth) {
                bearer {
                    loadTokens {
                        authPrefs.getAccessToken()?.let { access ->
                            authPrefs.getRefreshToken()?.let { refresh ->
                                BearerTokens(access, refresh)
                            }
                        }
                    }

                    refreshTokens {
                        val refresh = oldTokens?.refreshToken ?: return@refreshTokens null
                        refreshTokens(refresh)
                    }
                }
            }
        }
    }

    private fun simpleClient(): HttpClient {
        return HttpClient(platformHttpClientEngineFactory) {
            installCommon()
        }
    }

    private fun HttpClientConfig<*>.installCommon() {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                encodeDefaults = true
                isLenient = true
            })
        }

        install(Logging) {
            level = LogLevel.ALL
        }

        install(HttpTimeout) {
            requestTimeoutMillis = TIMEOUT
            connectTimeoutMillis = TIMEOUT
            socketTimeoutMillis = TIMEOUT
        }
    }

    private fun AuthResponse.toTokens() = BearerTokens(accessToken, refreshToken)

    companion object {
        private const val REFRESH_ENDPOINT = "auth/refresh"
        private const val TIMEOUT = 15_000L
    }
}