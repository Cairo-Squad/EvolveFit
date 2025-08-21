package com.cairosquad.evolvefit.repository.profile.local

import com.cairosquad.evolvefit.viewmodel.more.MoreScreenState
import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.russhwolf.settings.set

class ProfilePrefrencesImpl(
    private val settings: Settings = Settings()
) : ProfilePrefrences {

    companion object {
        private const val KEY_THEME = "theme"
        private const val KEY_LANGUAGE = "language"
    }

    override fun saveTheme(theme: MoreScreenState.Theme) {
        settings[KEY_THEME] = theme.name
    }

    override fun getSavedTheme(): MoreScreenState.Theme {
        val themeName = settings[KEY_THEME, "LIGHT"]
        return when (themeName) {
            "DARK" -> MoreScreenState.Theme.DARK
            else -> MoreScreenState.Theme.LIGHT
        }
    }

    override fun saveLanguage(language: String) {
        settings[KEY_LANGUAGE] = language
    }

    override fun getSavedLanguage(): String {
        return settings[KEY_LANGUAGE, "en"]
    }
}
