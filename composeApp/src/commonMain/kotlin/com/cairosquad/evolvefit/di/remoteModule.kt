package com.cairosquad.evolvefit.di

import com.cairosquad.evolvefit.repository.authentication.remote.AuthenticationRemoteDataSource
import com.cairosquad.evolvefit.repository.authentication.remote.AuthenticationRemoteDataSourceImpl
import com.cairosquad.evolvefit.repository.equipment.remote.EquipmentRemoteDataSourceImpl
import com.cairosquad.evolvefit.repository.equipment.remote.EquipmentsRemoteDataSource
import com.cairosquad.evolvefit.repository.exercise.remote.ExerciseRemoteDataSource
import com.cairosquad.evolvefit.repository.exercise.remote.ExerciseRemoteDataSourceImpl
import com.cairosquad.evolvefit.repository.home.data_source.remote.HomeRemoteDataSource
import com.cairosquad.evolvefit.repository.home.data_source.remote.HomeRemoteDataSourceImpl
import com.cairosquad.evolvefit.repository.nutrition.remote.NutritionRemoteDataSource
import com.cairosquad.evolvefit.repository.nutrition.remote.NutritionRemoteDataSourceImpl
import com.cairosquad.evolvefit.repository.profile.remote.ProfileRemoteDataSource
import com.cairosquad.evolvefit.repository.profile.remote.ProfileRemoteDataSourceImpl
import com.cairosquad.evolvefit.repository.report.remote.ReportRemoteDataSource
import com.cairosquad.evolvefit.repository.report.remote.ReportRemoteDataSourceImpl
import com.cairosquad.evolvefit.repository.utils.HttpClientHolder
import com.cairosquad.evolvefit.repository.workout.remote.WorkoutRemoteDataSource
import com.cairosquad.evolvefit.repository.workout.remote.WorkoutRemoteDataSourceImpl
import io.ktor.client.HttpClient
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val remoteModule = module {
    singleOf (::HttpClientHolder)
    singleOf(::AuthenticationRemoteDataSourceImpl).bind(AuthenticationRemoteDataSource::class)
    singleOf(::ExerciseRemoteDataSourceImpl).bind(ExerciseRemoteDataSource::class)
    singleOf(::EquipmentRemoteDataSourceImpl).bind(EquipmentsRemoteDataSource::class)
    singleOf(::NutritionRemoteDataSourceImpl).bind(NutritionRemoteDataSource::class)
    singleOf(::WorkoutRemoteDataSourceImpl).bind(WorkoutRemoteDataSource::class)
    singleOf(::ReportRemoteDataSourceImpl) bind ReportRemoteDataSource::class
    singleOf(::ProfileRemoteDataSourceImpl) bind ProfileRemoteDataSource::class
    singleOf(::HomeRemoteDataSourceImpl) bind HomeRemoteDataSource::class
}