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
            level = LogLevel.ALL
        }
    }
}

val access="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIwNDE5NzBlNC1lNmJhLTRjYmUtYjBjYS0zM2U2ODBlNmQzNjMiLCJpYXQiOjE3NTQ3NjY2MzIsImV4cCI6MTc1NDc3MDIzMn0.8Ebci3ZVeLYbuxYW-nrT5sGMoSjKHFjbY8YfgCbN6AdkDIKZi6paKdbMZxHUCmeSVef5aJ7ZBJVeD2rEf-sj1Q"