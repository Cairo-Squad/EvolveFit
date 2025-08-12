package com.cairosquad.evolvefit.di

import com.cairosquad.evolvefit.repository.authentication.local.AuthenticationPreferences
import com.cairosquad.evolvefit.repository.authentication.remote.AuthenticationRemoteDataSource
import com.cairosquad.evolvefit.repository.authentication.remote.AuthenticationRemoteDataSourceImpl
import com.cairosquad.evolvefit.repository.utils.provideHttpClient
import com.cairosquad.evolvefit.repository.workout.remote.WorkoutRemoteDataSource
import com.cairosquad.evolvefit.repository.workout.remote.WorkoutRemoteDataSourceImpl
import io.ktor.client.plugins.auth.providers.BearerTokens
import org.koin.dsl.module

val remoteModule = module {
    single<AuthenticationRemoteDataSource> { AuthenticationRemoteDataSourceImpl(get()) }
    single<WorkoutRemoteDataSource> { WorkoutRemoteDataSourceImpl() }
    single {
        provideHttpClient(
            authenticationPreferences = get(),
            refreshTokenProvider = { token ->
                val newTokens = get<AuthenticationRemoteDataSource>().getRefreshToken(token)
                get<AuthenticationPreferences>().saveTokens(newTokens.accessToken, newTokens.refreshToken)
                BearerTokens(newTokens.accessToken, newTokens.refreshToken)
            }
        )
    }
}