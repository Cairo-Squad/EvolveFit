package com.cairosquad.evolvefit.di

import com.cairosquad.evolvefit.domain.repository.AuthRepository
import com.cairosquad.evolvefit.domain.repository.MealRepository
import com.cairosquad.evolvefit.domain.repository.WaterIntakeRepository
import com.cairosquad.evolvefit.local.AuthPreferences
import com.cairosquad.evolvefit.remote.nutrition.data.RemoteMealDataSource
import com.cairosquad.evolvefit.remote.nutrition.data.RemoteMealDataSourceImpl
import com.cairosquad.evolvefit.remote.nutrition.data.RemoteWaterIntakeDataSource
import com.cairosquad.evolvefit.remote.nutrition.data.RemoteWaterIntakeDataSourceImpl
import com.cairosquad.evolvefit.remote.auth.AuthRemoteDataSourceImp
import com.cairosquad.evolvefit.remote.provideHttpClient
import com.cairosquad.evolvefit.repository.AuthRepositoryImpl
import com.cairosquad.evolvefit.repository.MealRepositoryImpl
import com.cairosquad.evolvefit.repository.WaterIntakeRepositoryImpl
import com.cairosquad.evolvefit.repository.remote.AuthRemoteDataSource
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
    single<RemoteMealDataSource> { RemoteMealDataSourceImpl(get()) }
    single<RemoteWaterIntakeDataSource> { RemoteWaterIntakeDataSourceImpl(get()) }
    single<MealRepository> { MealRepositoryImpl(get()) }
    single<WaterIntakeRepository> { WaterIntakeRepositoryImpl(get()) }
}