package com.cairosquad.evolvefit.di

import com.cairosquad.evolvefit.repository.profile.local.AndroidLanguagePreferences
import com.cairosquad.evolvefit.repository.profile.local.LanguagePreferences
import com.cairosquad.evolvefit.ui.util.LanguageManager
import com.russhwolf.settings.Settings
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val platformModule: Module
    get() = module {
        single<Settings> { Settings() }
        single<LanguagePreferences> { AndroidLanguagePreferences(get()) }
        singleOf(::LanguageManager)

    }
