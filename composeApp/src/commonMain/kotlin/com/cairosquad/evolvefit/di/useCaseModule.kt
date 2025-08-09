package com.cairosquad.evolvefit.di

import com.cairosquad.evolvefit.domain.AuthRepository
import com.cairosquad.evolvefit.domain.usecase.authentication.AuthUseCase
import com.cairosquad.evolvefit.domain.usecase.workout.ManageWorkoutsUseCase
import com.cairosquad.evolvefit.repository.FakeAuthRepository
import org.koin.dsl.module

val useCaseModule = module {
    single<AuthRepository> { FakeAuthRepository() }
    single { AuthUseCase(get()) }
    single { ManageWorkoutsUseCase(get()) }
}