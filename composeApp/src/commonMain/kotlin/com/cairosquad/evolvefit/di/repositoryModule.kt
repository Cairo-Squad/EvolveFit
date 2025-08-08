package com.cairosquad.evolvefit.di

import com.cairosquad.evolvefit.Repository.AuthRepositoryImpl
import com.cairosquad.evolvefit.Repository.WorkoutRepositoryImpl
import com.cairosquad.evolvefit.domain.AuthRemoteDataSource
import com.cairosquad.evolvefit.domain.AuthRepository
import com.cairosquad.evolvefit.domain.WorkoutRemoteDataSource
import com.cairosquad.evolvefit.domain.WorkoutRepository
import com.cairosquad.evolvefit.remote.Auth.AuthRemoteDataSourceImp
import com.cairosquad.evolvefit.remote.provideHttpClient
import com.cairosquad.evolvefit.remote.workOut.WorkoutRemoteDataSourceImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<WorkoutRepository> { WorkoutRepositoryImpl(get()) }
    single<WorkoutRemoteDataSource>{ WorkoutRemoteDataSourceImpl(get()) }
    single<AuthRemoteDataSource> { AuthRemoteDataSourceImp(get()) }
    single<AuthRepository> { AuthRepositoryImpl(get(),get()) }
    single { provideHttpClient(get(),get()) }
}