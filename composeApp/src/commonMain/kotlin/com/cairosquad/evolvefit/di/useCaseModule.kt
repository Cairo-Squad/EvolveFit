package com.cairosquad.evolvefit.di

import com.cairosquad.evolvefit.domain.usecase.authentication.AuthenticationUseCase
import com.cairosquad.evolvefit.domain.usecase.exercise.ManageExerciseUseCase
import com.cairosquad.evolvefit.domain.usecase.report.ReportUseCase
import com.cairosquad.evolvefit.domain.usecase.workout.ManageWorkoutUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { AuthenticationUseCase(get()) }
    factory { ManageExerciseUseCase(get()) }
    factory { ReportUseCase(get()) }
    factory { ManageWorkoutUseCase(get()) }
}
