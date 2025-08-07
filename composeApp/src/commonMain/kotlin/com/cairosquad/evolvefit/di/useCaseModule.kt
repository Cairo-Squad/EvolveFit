package com.cairosquad.evolvefit.di

import com.cairosquad.evolvefit.domain.usecases.MealUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { MealUseCase(get()) }
}