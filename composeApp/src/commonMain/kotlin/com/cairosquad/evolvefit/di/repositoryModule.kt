package com.cairosquad.evolvefit.di

import com.cairosquad.evolvefit.domain.repository.AuthRepository
import com.cairosquad.evolvefit.domain.repository.NutritionRepository
import com.cairosquad.evolvefit.local.AuthPreferences
import com.cairosquad.evolvefit.remote.nutrition.data.RemoteNutritionDataSource
import com.cairosquad.evolvefit.remote.nutrition.data.RemoteNutritionDataSourceImpl
import com.cairosquad.evolvefit.remote.auth.AuthRemoteDataSourceImp
import com.cairosquad.evolvefit.remote.provideHttpClient
import com.cairosquad.evolvefit.repository.AuthRepositoryImpl
import com.cairosquad.evolvefit.repository.remote.AuthRemoteDataSource
import com.cairosquad.evolvefit.repository.remote.nutrition.NutritionRepositoryImpl
import io.ktor.client.plugins.auth.providers.BearerTokens
import org.koin.dsl.module

val repositoryModule = module {
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
    single<RemoteNutritionDataSource> { RemoteNutritionDataSourceImpl(get()) }
    single<NutritionRepository> { NutritionRepositoryImpl(get()) }
}