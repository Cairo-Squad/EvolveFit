package com.cairosquad.evolvefit.di

import com.cairosquad.evolvefit.domain.usecase.CreateWorkoutUseCase
import com.cairosquad.evolvefit.domain.usecase.GetAllExercisesUseCase
import com.cairosquad.evolvefit.domain.usecase.GetEquipmentsUseCase
import com.cairosquad.evolvefit.domain.usecase.GetExercisesByIdsUseCase
import com.cairosquad.evolvefit.domain.usecase.authentication.AuthUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { AuthUseCase(get()) }
    factory { CreateWorkoutUseCase(get(),get()) }
    factory { GetEquipmentsUseCase(get()) }
    factory { GetExercisesByIdsUseCase(get())}
    factory { GetAllExercisesUseCase(get()) }
}
