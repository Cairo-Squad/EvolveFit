package com.cairosquad.evolvefit.di

import com.cairosquad.evolvefit.repository.remote.workout.WorkoutRemoteDataSource
import com.cairosquad.evolvefit.repository.remote.workout.WorkoutRemoteDataSourceImpl
import org.koin.dsl.module

val remoteModule = module {

    single<WorkoutRemoteDataSource> { WorkoutRemoteDataSourceImpl() }
}