package com.cairosquad.evolvefit.di

import com.cairosquad.evolvefit.domain.AuthRepository
import com.cairosquad.evolvefit.local.AuthPreferences
import com.cairosquad.evolvefit.remote.auth.AuthRemoteDataSourceImp
import com.cairosquad.evolvefit.remote.provideHttpClient
import com.cairosquad.evolvefit.repository.AuthRepositoryImpl
import com.cairosquad.evolvefit.repository.remote.AuthRemoteDataSource
import io.ktor.client.plugins.auth.providers.BearerTokens
import com.cairosquad.evolvefit.domain.WorkoutRepo
import com.cairosquad.evolvefit.repository.FakeWorkoutRepository
import org.koin.dsl.module

val repositoryModule = module {
    // TODO: instantiate repositories
    single {
        provideHttpClient(
            authPreferences = get(),
            refreshTokenProvider = { token ->
                val newTokens = get<AuthRemoteDataSource>().getRefreshToken(token)
                get<AuthPreferences>().saveTokens(newTokens.accessToken, newTokens.refreshToken)
                BearerTokens(newTokens.accessToken, newTokens.refreshToken)
            }
        )
    }
    single<AuthRemoteDataSource> { AuthRemoteDataSourceImp(get()) }
    single<AuthRepository> { AuthRepositoryImpl(get(),get()) }
    single<WorkoutRepo> { FakeWorkoutRepository() }
}