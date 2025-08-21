package com.cairosquad.evolvefit

import com.cairosquad.evolvefit.viewmodel.more.MoreScreenState
import com.russhwolf.settings.Settings
import com.russhwolf.settings.set
import com.russhwolf.settings.get

class AppSettingsManager {
    private val settings = Settings()

    companion object {
        private const val KEY_THEME = "theme"
        private const val KEY_LANGUAGE = "language"
    }

    fun saveTheme(theme: MoreScreenState.Theme) {
        settings[KEY_THEME] = theme.name
    }

    fun getSavedTheme(): MoreScreenState.Theme {
        val themeName = settings[KEY_THEME, "LIGHT"]
        return when (themeName) {
            "DARK" -> MoreScreenState.Theme.DARK
            else -> MoreScreenState.Theme.LIGHT
        }
    }

    fun saveLanguage(language: String) {
        settings[KEY_LANGUAGE] = language
    }

    fun getSavedLanguage(): String {
        return settings[KEY_LANGUAGE, "en"]
    }
}