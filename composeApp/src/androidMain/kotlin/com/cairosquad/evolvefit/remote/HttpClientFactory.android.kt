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

val access="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIwNDE5NzBlNC1lNmJhLTRjYmUtYjBjYS0zM2U2ODBlNmQzNjMiLCJpYXQiOjE3NTQ2NTYwMDksImV4cCI6MTc1NDY1OTYwOX0.-qu_fqF_kOPcgJaK24w4t6hriuXh1tV5a4wdpQfQ22afNaRYZaUGtkhlG1z3V3KoaS1ltcypwGSCKkaye0FBNw"