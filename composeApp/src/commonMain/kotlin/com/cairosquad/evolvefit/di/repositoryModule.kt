package com.cairosquad.evolvefit.di

import com.cairosquad.evolvefit.Repository.AuthRepositoryImpl
import com.cairosquad.evolvefit.domain.AuthRemoteDataSource
import com.cairosquad.evolvefit.domain.AuthRepository
import com.cairosquad.evolvefit.remote.Auth.AuthRemoteDataSourceImp
import com.cairosquad.evolvefit.remote.provideHttpClient
import org.koin.dsl.module

val repositoryModule = module {
    single { provideHttpClient(get()) }
//    single<WorkoutRepository> { WorkoutRepositoryImpl(get()) }
//    single<WorkoutRemoteDataSource>{ WorkoutRemoteDataSourceImpl(get()) }
    single<AuthRemoteDataSource> { AuthRemoteDataSourceImp(get()) }
    single<AuthRepository> { AuthRepositoryImpl(get(),get()) }
}