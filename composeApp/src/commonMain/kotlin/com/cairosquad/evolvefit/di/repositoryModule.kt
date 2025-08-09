package com.cairosquad.evolvefit.di

import com.cairosquad.evolvefit.Repository.AuthRepositoryImpl
import com.cairosquad.evolvefit.domain.AuthRemoteDataSource
import com.cairosquad.evolvefit.domain.AuthRepository
import com.cairosquad.evolvefit.local.AuthPreferences
import com.cairosquad.evolvefit.remote.Auth.AuthRemoteDataSourceImp
import com.cairosquad.evolvefit.remote.provideHttpClient
import io.ktor.client.plugins.auth.providers.BearerTokens
import org.koin.dsl.module

val repositoryModule = module {
    single {
        provideHttpClient(
            authPreferences = get(),
            refreshTokenProvider = { token ->
                val newTokens = get<AuthRemoteDataSource>().refreshToken(token)
                get<AuthPreferences>().saveTokens(newTokens.accessToken, newTokens.refreshToken)
                BearerTokens(newTokens.accessToken, newTokens.refreshToken)
            }
        )
    }
    single<AuthRemoteDataSource> { AuthRemoteDataSourceImp(get()) }
    single<AuthRepository> { AuthRepositoryImpl(get(),get()) }
}