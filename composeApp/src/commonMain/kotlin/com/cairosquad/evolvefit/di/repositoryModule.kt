package com.cairosquad.evolvefit.di

import com.cairosquad.evolvefit.domain.repository.AuthenticationRepository
import com.cairosquad.evolvefit.domain.repository.EquipmentRepository
import com.cairosquad.evolvefit.domain.repository.ReportRepository
import com.cairosquad.evolvefit.domain.repository.WorkoutRepository
import com.cairosquad.evolvefit.repository.FakeReportRepositoryImpl
import com.cairosquad.evolvefit.repository.authentication.AuthenticationRepositoryImpl
import com.cairosquad.evolvefit.repository.equipment.EquipmentRepositoryImpl
import com.cairosquad.evolvefit.repository.workout.WorkoutRepositoryImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import com.cairosquad.evolvefit.domain.repository.NutritionRepository
import com.cairosquad.evolvefit.repository.nutrition.remote.RemoteNutritionDataSource
import com.cairosquad.evolvefit.repository.nutrition.remote.RemoteNutritionDataSourceImpl
import com.cairosquad.evolvefit.repository.nutrition.NutritionRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::AuthenticationRepositoryImpl) bind AuthenticationRepository::class
    singleOf(::WorkoutRepositoryImpl) bind WorkoutRepository::class
    singleOf(::FakeReportRepositoryImpl) bind ReportRepository::class
    singleOf(::EquipmentRepositoryImpl) bind EquipmentRepository::class
    single<RemoteNutritionDataSource> { RemoteNutritionDataSourceImpl(get()) }
    single<NutritionRepository> { NutritionRepositoryImpl(get()) }
}