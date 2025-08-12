package com.cairosquad.evolvefit.di

import com.cairosquad.evolvefit.repository.workout.remote.WorkoutRemoteDataSource
import org.koin.dsl.module

val remoteModule = module {

    single<WorkoutRemoteDataSource> { WorkoutRemoteDataSourceImpl() }
}