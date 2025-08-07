package com.cairosquad.evolvefit.remote

import io.ktor.client.HttpClient
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

actual fun createHttpClient(): HttpClient {
    return HttpClient(Android) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                prettyPrint = true })}
        defaultRequest {
            header("Authorization", "Bearer ${access}")
        }
        install(Logging) {
            logger = Logger.SIMPLE
            level = LogLevel.ALL // هذا يطبع كل شيء: headers + body
        }
    }
}

val access="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIwNDE5NzBlNC1lNmJhLTRjYmUtYjBjYS0zM2U2ODBlNmQzNjMiLCJpYXQiOjE3NTQ1Nzk5NTEsImV4cCI6MTc1NDU4MzU1MX0.6Lpo-wmjFTDBTTQhEqHKeHD555XDWaQazblZZ26lHkKGxmuSUOp5d6afzWYHTYg9hMdialAe1Cjjc3RYNumlLA"