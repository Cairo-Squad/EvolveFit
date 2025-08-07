package com.cairosquad.evolvefit.di

import com.cairosquad.evolvefit.domain.repository.MealRepository
import com.cairosquad.evolvefit.repository.MealRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single <MealRepository>{ MealRepositoryImpl() }
}