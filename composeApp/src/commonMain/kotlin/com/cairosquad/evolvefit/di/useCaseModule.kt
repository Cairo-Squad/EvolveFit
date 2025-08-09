package com.cairosquad.evolvefit.di

import com.cairosquad.evolvefit.Repository.FakeAuthRepository
import com.cairosquad.evolvefit.domain.AuthRepository
import com.cairosquad.evolvefit.domain.usecase.authentication.AuthUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single<AuthRepository> { FakeAuthRepository() }
    single { AuthUseCase(get()) }
}