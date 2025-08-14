package com.cairosquad.evolvefit.di

import com.cairosquad.evolvefit.repository.authentication.remote.AuthenticationRemoteDataSource
import com.cairosquad.evolvefit.repository.authentication.remote.AuthenticationRemoteDataSourceImpl
import com.cairosquad.evolvefit.repository.equipment.remot.EquipmentRemoteDataSourceImpl
import com.cairosquad.evolvefit.repository.equipment.remot.EquipmentsRemoteDataSource
import com.cairosquad.evolvefit.repository.exercise.remot.ExerciseRemoteDataSource
import com.cairosquad.evolvefit.repository.exercise.remot.ExerciseRemoteDataSourceImpl
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
    single<ExerciseRemoteDataSource> { ExerciseRemoteDataSourceImpl(get()) }
    single<EquipmentsRemoteDataSource> { EquipmentRemoteDataSourceImpl(get()) }
    single<WorkoutRemoteDataSource> { WorkoutRemoteDataSourceImpl() }
    single {
        RefreshTokenProvider(HttpClient {
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true })
            }
            install(Logging) {
                level = LogLevel.BODY
            }
        })
    }
    single {
        provideHttpClient(
            authenticationPreferences = get(),
            refreshTokenProvider = get()
        )
    }
}