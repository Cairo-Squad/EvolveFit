package com.cairosquad.evolvefit.di

import org.koin.core.module.Module
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    includes(
        viewModelModule,
        repositoryModule,
        // TODO: add the rest of the modules
    )
}