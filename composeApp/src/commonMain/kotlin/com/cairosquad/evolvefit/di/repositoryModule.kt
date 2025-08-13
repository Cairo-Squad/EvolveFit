package com.cairosquad.evolvefit.di

import com.cairosquad.evolvefit.domain.repository.AuthenticationRepository
import com.cairosquad.evolvefit.domain.repository.EquipmentRepository
import com.cairosquad.evolvefit.domain.repository.ProfileRepository
import com.cairosquad.evolvefit.domain.repository.ReportRepository
import com.cairosquad.evolvefit.domain.repository.WorkoutRepository
import com.cairosquad.evolvefit.repository.FakeReportRepositoryImpl
import com.cairosquad.evolvefit.repository.authentication.AuthenticationRepositoryImpl
import com.cairosquad.evolvefit.repository.equipment.EquipmentRepositoryImpl
import com.cairosquad.evolvefit.repository.workout.WorkoutRepositoryImpl
import com.cairosquad.evolvefit.repository.profile.ProfileRepositoryImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::AuthenticationRepositoryImpl) bind AuthenticationRepository::class
    singleOf(::WorkoutRepositoryImpl) bind WorkoutRepository::class
    singleOf(::FakeReportRepositoryImpl) bind ReportRepository::class
    singleOf(::EquipmentRepositoryImpl) bind EquipmentRepository::class
    singleOf(::ProfileRepositoryImpl) bind ProfileRepository::class
}