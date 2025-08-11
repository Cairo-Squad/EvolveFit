package com.cairosquad.evolvefit.di

import com.cairosquad.evolvefit.remote.workout.WorkoutRemoteDataSourceImpl
import com.cairosquad.evolvefit.repository.remote.workout.WorkoutRemoteDataSource
import org.koin.dsl.module

val remoteModule = module {

    single<WorkoutRemoteDataSource> { WorkoutRemoteDataSourceImpl() }
}