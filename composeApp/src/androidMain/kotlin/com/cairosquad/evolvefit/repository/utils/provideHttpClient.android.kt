package com.cairosquad.evolvefit.repository.utils

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.android.Android
import io.ktor.client.engine.android.AndroidEngineConfig

actual val platformHttpClientEngineFactory: HttpClientEngineFactory<HttpClientEngineConfig>
    get() = Android