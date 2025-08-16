package com.cairosquad.evolvefit.ui.util

import com.cairosquad.evolvefit.domain.model.Language
import com.cairosquad.evolvefit.repository.profile.local.LanguagePreferences
import platform.Foundation.NSUserDefaults

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class LanguageManager actual constructor(
    private val languagePreferences: LanguagePreferences
) {
    actual fun applyLanguage(language: Language) {
        val languageCode = when (language) {
            Language.ARABIC -> "ar"
            Language.ENGLISH -> "en"
        }

        NSUserDefaults.standardUserDefaults.setObject(
            listOf(languageCode),
            "AppleLanguages"
        )
        NSUserDefaults.standardUserDefaults.synchronize()
    }

    actual fun applyStoredLanguage() {
        val savedLanguage = languagePreferences.getLanguage()
        applyLanguage(savedLanguage)
    }
}