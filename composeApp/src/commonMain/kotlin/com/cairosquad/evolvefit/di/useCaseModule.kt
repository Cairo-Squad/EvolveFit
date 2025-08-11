package com.cairosquad.evolvefit.di

import com.cairosquad.evolvefit.domain.usecase.authentication.AuthUseCase
import com.cairosquad.evolvefit.domain.usecase.report.ReportUseCase
import com.cairosquad.evolvefit.domain.usecase.workout.ExerciseUseCase
import com.cairosquad.evolvefit.domain.usecase.workout.ManageWorkoutUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { AuthUseCase(get()) }
    factory { ExerciseUseCase(get()) }
    factory { ReportUseCase(get()) }
    factory { ManageWorkoutUseCase(get()) }
}
