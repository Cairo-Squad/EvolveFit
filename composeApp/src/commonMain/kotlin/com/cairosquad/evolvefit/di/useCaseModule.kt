package com.cairosquad.evolvefit.di

import com.cairosquad.evolvefit.domain.usecase.nutrition.MealUseCase
import com.cairosquad.evolvefit.domain.usecase.nutrition.WaterIntakeUseCase
import com.cairosquad.evolvefit.domain.usecase.authentication.AuthUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { MealUseCase(get()) }
    single { WaterIntakeUseCase(get()) }
    factory { AuthUseCase(get()) }
}