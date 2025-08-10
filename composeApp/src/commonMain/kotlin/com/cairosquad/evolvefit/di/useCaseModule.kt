package com.cairosquad.evolvefit.di

import com.cairosquad.evolvefit.domain.usecases.MealUseCase
import com.cairosquad.evolvefit.domain.usecases.WaterIntakeUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { MealUseCase(get()) }
    single { WaterIntakeUseCase(get()) }
}