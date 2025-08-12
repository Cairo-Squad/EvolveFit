package com.cairosquad.evolvefit.di

import com.cairosquad.evolvefit.domain.usecase.authentication.AuthUseCase
import com.cairosquad.evolvefit.domain.usecase.nutrition.ManageNutritionUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { ManageNutritionUseCase(get()) }
    factory { AuthUseCase(get()) }
}