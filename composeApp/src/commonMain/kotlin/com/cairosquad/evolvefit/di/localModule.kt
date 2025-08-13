package com.cairosquad.evolvefit.di

import com.cairosquad.evolvefit.repository.authentication.local.AuthenticationPreferences
import com.cairosquad.evolvefit.repository.authentication.local.AuthenticationPreferencesImpl
import com.russhwolf.settings.Settings
import org.koin.dsl.module

val localModule = module {
    single<Settings> { Settings() }
    single<AuthenticationPreferences> { AuthenticationPreferencesImpl(get()) }
}