package com.cairosquad.evolvefit.di

import com.cairosquad.evolvefit.domain.usecase.authentication.AuthUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { AuthUseCase(get()) }
}
