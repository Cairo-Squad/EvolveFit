package com.cairosquad.evolvefit.di

import com.cairosquad.evolvefit.domain.repository.AuthenticationRepository
import com.cairosquad.evolvefit.domain.repository.ReportRepository
import com.cairosquad.evolvefit.domain.repository.WorkoutRepository
import com.cairosquad.evolvefit.repository.FakeReportRepositoryImpl
import com.cairosquad.evolvefit.repository.authentication.AuthenticationRepositoryImpl
import com.cairosquad.evolvefit.repository.local.AuthenticationPreferences
import com.cairosquad.evolvefit.repository.remote.AuthenticationRemoteDataSource
import com.cairosquad.evolvefit.repository.remote.api.provideHttpClient
import com.cairosquad.evolvefit.repository.remote.authentication.AuthenticationRemoteDataSourceImpl
import com.cairosquad.evolvefit.repository.workout.WorkoutRepositoryImpl
import io.ktor.client.plugins.auth.providers.BearerTokens
import org.koin.dsl.module

val repositoryModule = module {
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
    single<AuthenticationRemoteDataSource> { AuthenticationRemoteDataSourceImpl(get()) }
    single<AuthenticationRepository> { AuthenticationRepositoryImpl(get(),get()) }
    single<WorkoutRepository> { WorkoutRepositoryImpl(get()) }
    single<ReportRepository> { FakeReportRepositoryImpl() }
}