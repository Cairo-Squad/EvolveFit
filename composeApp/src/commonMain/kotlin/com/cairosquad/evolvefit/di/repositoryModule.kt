package com.cairosquad.evolvefit.di

import com.cairosquad.evolvefit.domain.repository.AuthenticationRepository
import com.cairosquad.evolvefit.domain.repository.EquipmentRepository
import com.cairosquad.evolvefit.domain.repository.ExerciseRepository
import com.cairosquad.evolvefit.domain.repository.HomeRepository
import com.cairosquad.evolvefit.domain.repository.NutritionRepository
import com.cairosquad.evolvefit.domain.repository.ProfileRepository
import com.cairosquad.evolvefit.domain.repository.LanguageRepository
import com.cairosquad.evolvefit.domain.repository.ReportRepository
import com.cairosquad.evolvefit.domain.repository.WorkoutRepository
import com.cairosquad.evolvefit.repository.authentication.AuthenticationRepositoryImpl
import com.cairosquad.evolvefit.repository.equipment.EquipmentRepositoryImpl
import com.cairosquad.evolvefit.repository.exercise.ExerciseRepositoryImpl
import com.cairosquad.evolvefit.repository.home.HomeRepositoryImpl
import com.cairosquad.evolvefit.repository.nutrition.NutritionRepositoryImpl
import com.cairosquad.evolvefit.repository.profile.ProfileRepositoryImpl
import com.cairosquad.evolvefit.repository.profile.LanguageRepositoryImpl
import com.cairosquad.evolvefit.repository.report.ReportRepositoryImpl
import com.cairosquad.evolvefit.repository.workout.WorkoutRepositoryImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::AuthenticationRepositoryImpl) bind AuthenticationRepository::class
    singleOf(::WorkoutRepositoryImpl) bind WorkoutRepository::class
    singleOf(::ReportRepositoryImpl) bind ReportRepository::class
    singleOf(::HomeRepositoryImpl) bind HomeRepository::class
    singleOf(::EquipmentRepositoryImpl) bind EquipmentRepository::class
    singleOf(::ProfileRepositoryImpl) bind ProfileRepository::class
    singleOf(::ExerciseRepositoryImpl) bind ExerciseRepository::class
    singleOf(::LanguageRepositoryImpl) bind LanguageRepository::class
    singleOf(::NutritionRepositoryImpl) bind NutritionRepository::class
    singleOf(::ProfileRepositoryImpl) bind ProfileRepository::class
}