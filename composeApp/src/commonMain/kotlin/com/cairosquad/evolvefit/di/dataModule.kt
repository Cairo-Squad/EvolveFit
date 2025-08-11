package com.cairosquad.evolvefit.di

import com.cairosquad.evolvefit.repository.local.AuthPreferences
import com.russhwolf.settings.Settings
import org.koin.dsl.module

val preferencesModule = module {
    single<Settings> { Settings() }
    single { AuthPreferences(get()) }
}