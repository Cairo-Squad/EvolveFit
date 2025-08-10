package com.cairosquad.evolvefit.di

import com.cairosquad.evolvefit.domain.usecase.authentication.AuthUseCase
import com.cairosquad.evolvefit.domain.usecase.workout.ManageWorkoutsUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { AuthUseCase(get()) }
    single { AuthUseCase(get()) }
    single { ManageWorkoutsUseCase(get()) }
}