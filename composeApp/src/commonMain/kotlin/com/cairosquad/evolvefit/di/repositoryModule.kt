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
import com.cairosquad.evolvefit.repository.WorkoutRepositoryImpl
import com.cairosquad.evolvefit.domain.AuthRepository
import com.cairosquad.evolvefit.repository.remote.WorkoutRemoteDataSource
import com.cairosquad.evolvefit.domain.WorkoutRepository
import com.cairosquad.evolvefit.local.AuthPreferences
import com.cairosquad.evolvefit.remote.auth.AuthRemoteDataSourceImp
import com.cairosquad.evolvefit.remote.provideHttpClient
import com.cairosquad.evolvefit.remote.workOut.WorkoutRemoteDataSourceImpl
import com.cairosquad.evolvefit.repository.AuthRepositoryImpl
import com.cairosquad.evolvefit.repository.remote.AuthRemoteDataSource
import io.ktor.client.plugins.auth.providers.BearerTokens
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::AuthenticationRepositoryImpl) bind AuthenticationRepository::class
    singleOf(::WorkoutRepositoryImpl) bind WorkoutRepository::class
    singleOf(::FakeReportRepositoryImpl) bind ReportRepository::class
    singleOf(::EquipmentRepositoryImpl) bind EquipmentRepository::class
}