package com.cairosquad.evolvefit.di

import com.cairosquad.evolvefit.domain.WorkoutRepository
import com.cairosquad.evolvefit.domain.usecase.workout.GetAllWorkoutsUseCase
import com.cairosquad.evolvefit.domain.usecase.workout.GetWorkoutsByBodyPartUseCase
import com.cairosquad.evolvefit.repository.FakeWorkoutRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<WorkoutRepository> { FakeWorkoutRepository() } //todo: replace with real one
    single { GetAllWorkoutsUseCase(get()) }
    single { GetWorkoutsByBodyPartUseCase(get()) }
}