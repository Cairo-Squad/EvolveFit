package com.cairosquad.evolvefit.di

import com.cairosquad.evolvefit.domain.repository.MealRepository
import com.cairosquad.evolvefit.remote.RemoteMealDataSource
import com.cairosquad.evolvefit.remote.RemoteMealDataSourceImpl
import com.cairosquad.evolvefit.remote.createHttpClient
import com.cairosquad.evolvefit.repository.MealRepositoryImpl
import io.ktor.client.HttpClient
import org.koin.dsl.module

val repositoryModule = module {
    single { createHttpClient() }
    single<RemoteMealDataSource> { RemoteMealDataSourceImpl(get()) }
    single<MealRepository> { MealRepositoryImpl(get()) }
}