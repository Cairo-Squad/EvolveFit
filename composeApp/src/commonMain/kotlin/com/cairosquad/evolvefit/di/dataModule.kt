package com.cairosquad.evolvefit.di

import com.cairosquad.evolvefit.local.AuthPreferences
import com.russhwolf.settings.Settings
import org.koin.dsl.module

val preferencesModule = module {
    single<Settings> { Settings() }
    single { AuthPreferences(get()) }
}