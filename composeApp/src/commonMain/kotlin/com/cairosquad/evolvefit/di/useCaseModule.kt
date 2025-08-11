package com.cairosquad.evolvefit.di

import com.cairosquad.evolvefit.domain.usecase.authentication.AuthUseCase
import com.cairosquad.evolvefit.domain.usecase.report.ReportUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { AuthUseCase(get()) }
    factory { ReportUseCase(get()) }
}
