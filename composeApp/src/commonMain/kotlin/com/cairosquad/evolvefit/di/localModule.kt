package com.cairosquad.evolvefit.di

import androidx.room.Room
import com.cairosquad.evolvefit.database.getDatabaseBuilder
import com.cairosquad.evolvefit.repository.authentication.local.AuthenticationPreferences
import com.cairosquad.evolvefit.repository.authentication.local.AuthenticationPreferencesImpl
import com.cairosquad.evolvefit.repository.workout.local.AppDatabase
import com.cairosquad.evolvefit.repository.workout.local.PlayedWorkoutDao
import com.russhwolf.settings.Settings
import org.koin.dsl.module

val localModule = module {
    single<Settings> { Settings() }
    single<AuthenticationPreferences> { AuthenticationPreferencesImpl(get()) }
    single<AppDatabase> {
        getDatabaseBuilder().build()
    }
    single<PlayedWorkoutDao> {
        get<AppDatabase>().playedWorkoutDao()
    }
}