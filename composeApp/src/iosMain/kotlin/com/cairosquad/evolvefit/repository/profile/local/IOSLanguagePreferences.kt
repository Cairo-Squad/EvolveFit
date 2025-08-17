package com.cairosquad.evolvefit.repository.profile.local

import com.cairosquad.evolvefit.domain.model.Language

class IOSLanguagePreferences(
    private val settings: com.russhwolf.settings.Settings
) : LanguagePreferences {
    override fun saveLanguage(language: Language) {
        settings.putString(KEY_LANGUAGE, languageToCode(language))
    }

    override fun getLanguage(): Language {
        val code = settings.getString(KEY_LANGUAGE, languageToCode(Language.ENGLISH))
        return Language.entries.firstOrNull { languageToCode(it) == code } ?: Language.ENGLISH
    }

    private fun languageToCode(language: Language): String {
        return when (language) {
            Language.ARABIC -> ARABIC_CODE
            else -> ENGLISH_CODE
        }
    }

    companion object {
        private const val KEY_LANGUAGE = "app_language"
        const val ARABIC_CODE = "ar"
        const val ENGLISH_CODE = "en"
    }
}
