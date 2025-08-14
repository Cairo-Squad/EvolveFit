package com.cairosquad.evolvefit.di

import com.cairosquad.evolvefit.repository.authentication.remote.AuthenticationRemoteDataSource
import com.cairosquad.evolvefit.repository.authentication.remote.AuthenticationRemoteDataSourceImpl
import com.cairosquad.evolvefit.repository.nutrition.remote.RemoteNutritionDataSource
import com.cairosquad.evolvefit.repository.nutrition.remote.RemoteNutritionDataSourceImpl
import com.cairosquad.evolvefit.repository.utils.RefreshTokenProvider
import com.cairosquad.evolvefit.repository.utils.provideHttpClient
import com.cairosquad.evolvefit.repository.workout.remote.WorkoutRemoteDataSource
import com.cairosquad.evolvefit.repository.workout.remote.WorkoutRemoteDataSourceImpl
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val remoteModule = module {
    single<AuthenticationRemoteDataSource> { AuthenticationRemoteDataSourceImpl(get()) }
    single<WorkoutRemoteDataSource> { WorkoutRemoteDataSourceImpl() }
    single<RemoteNutritionDataSource> { RemoteNutritionDataSourceImpl(get()) }
    single { RefreshTokenProvider(HttpClient {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
        install(Logging) {
            level = LogLevel.BODY
        }
    }) }
    single {
        provideHttpClient(
            authenticationPreferences = get(),
            refreshTokenProvider = get()
        )
    }
}