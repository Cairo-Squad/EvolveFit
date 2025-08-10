package com.cairosquad.evolvefit.di

import com.cairosquad.evolvefit.domain.repository.MealRepository
import com.cairosquad.evolvefit.domain.repository.WaterIntakeRepository
import com.cairosquad.evolvefit.remote.RemoteMealDataSource
import com.cairosquad.evolvefit.remote.RemoteMealDataSourceImpl
import com.cairosquad.evolvefit.remote.RemoteWaterIntakeDataSource
import com.cairosquad.evolvefit.remote.RemoteWaterIntakeDataSourceImpl
import com.cairosquad.evolvefit.remote.createHttpClient
import com.cairosquad.evolvefit.repository.MealRepositoryImpl
import com.cairosquad.evolvefit.repository.WaterIntakeRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single { createHttpClient() }
    single<RemoteMealDataSource> { RemoteMealDataSourceImpl(get()) }
    single<RemoteWaterIntakeDataSource> { RemoteWaterIntakeDataSourceImpl(get()) }
    single<MealRepository> { MealRepositoryImpl(get()) }
    single<WaterIntakeRepository> { WaterIntakeRepositoryImpl(get()) }
}