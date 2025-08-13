package com.cairosquad.evolvefit.di

import com.cairosquad.evolvefit.domain.usecase.authentication.AuthenticationUseCase
import com.cairosquad.evolvefit.domain.usecase.equipment.ManageEquipmentUseCase
import com.cairosquad.evolvefit.domain.usecase.exercise.ManageExerciseUseCase
import com.cairosquad.evolvefit.domain.usecase.report.ReportUseCase
import com.cairosquad.evolvefit.domain.usecase.workout.ManageWorkoutUseCase
import com.cairosquad.evolvefit.domain.usecase.nutrition.ManageNutritionUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val useCaseModule = module {
    singleOf(::AuthenticationUseCase)
    singleOf(::ManageExerciseUseCase)
    singleOf(::ReportUseCase)
    singleOf(::ManageWorkoutUseCase)
    single { ManageNutritionUseCase(get()) }
    singleOf(::ManageEquipmentUseCase)
}
