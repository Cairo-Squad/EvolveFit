package com.cairosquad.evolvefit.di

import com.cairosquad.evolvefit.domain.usecase.CreateWorkoutUseCase
import com.cairosquad.evolvefit.domain.usecase.authentication.AuthUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { AuthUseCase(get()) }
    factory { CreateWorkoutUseCase(get()) }
    //factory { GetEquipmentsUseCase(get()) }
    factory { AuthUseCase(get()) }
}
