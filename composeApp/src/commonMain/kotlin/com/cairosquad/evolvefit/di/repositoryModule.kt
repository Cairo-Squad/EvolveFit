package com.cairosquad.evolvefit.di

import com.cairosquad.evolvefit.domain.WorkoutRepository
import com.cairosquad.evolvefit.repository.FakeWorkoutRepository
import org.koin.dsl.module

val repositoryModule = module {
    // TODO: instantiate repositories
    single<WorkoutRepository> { FakeWorkoutRepository() }
}