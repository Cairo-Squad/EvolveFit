package com.cairosquad.evolvefit.di

import com.cairosquad.evolvefit.repository.authentication.remote.AuthenticationRemoteDataSource
import com.cairosquad.evolvefit.repository.authentication.remote.AuthenticationRemoteDataSourceImpl
import com.cairosquad.evolvefit.repository.workout.remote.WorkoutRemoteDataSource
import com.cairosquad.evolvefit.repository.workout.remote.WorkoutRemoteDataSourceImpl
import org.koin.dsl.module

val remoteModule = module {
    single<AuthenticationRemoteDataSource> { AuthenticationRemoteDataSourceImpl(get()) }
    single<WorkoutRemoteDataSource> { WorkoutRemoteDataSourceImpl() }
}