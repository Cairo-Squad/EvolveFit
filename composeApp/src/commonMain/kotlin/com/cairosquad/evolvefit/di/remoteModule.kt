package com.cairosquad.evolvefit.di

import com.cairosquad.evolvefit.repository.authentication.remote.AuthenticationRemoteDataSource
import com.cairosquad.evolvefit.repository.authentication.remote.AuthenticationRemoteDataSourceImpl
import com.cairosquad.evolvefit.repository.equipment.remot.EquipmentRemoteDataSourceImpl
import com.cairosquad.evolvefit.repository.equipment.remot.EquipmentsRemoteDataSource
import com.cairosquad.evolvefit.repository.exercise.remote.ExerciseRemoteDataSource
import com.cairosquad.evolvefit.repository.exercise.remote.ExerciseRemoteDataSourceImpl
import com.cairosquad.evolvefit.repository.home.data_source.remote.RemoteHomeDataSource
import com.cairosquad.evolvefit.repository.home.data_source.remote.RemoteHomeDataSourceImpl
import com.cairosquad.evolvefit.repository.nutrition.remote.RemoteNutritionDataSource
import com.cairosquad.evolvefit.repository.nutrition.remote.RemoteNutritionDataSourceImpl
import com.cairosquad.evolvefit.repository.profile.remote.RemoteProfileDataSource
import com.cairosquad.evolvefit.repository.profile.remote.RemoteProfileDataSourceImpl
import com.cairosquad.evolvefit.repository.report.remote.ReportRemoteDataSource
import com.cairosquad.evolvefit.repository.report.remote.ReportRemoteDataSourceImpl
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
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val remoteModule = module {
    single<AuthenticationRemoteDataSource> { AuthenticationRemoteDataSourceImpl(get()) }
    single<ExerciseRemoteDataSource> { ExerciseRemoteDataSourceImpl(get()) }
    single<EquipmentsRemoteDataSource> { EquipmentRemoteDataSourceImpl(get()) }
    single<RemoteNutritionDataSource> { RemoteNutritionDataSourceImpl(get()) }
    single<WorkoutRemoteDataSource> { WorkoutRemoteDataSourceImpl(get()) }
    singleOf(::ReportRemoteDataSourceImpl) bind ReportRemoteDataSource::class
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

    singleOf(::RemoteProfileDataSourceImpl) bind RemoteProfileDataSource::class
    singleOf(::RemoteHomeDataSourceImpl) bind RemoteHomeDataSource::class
}