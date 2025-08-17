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
            Language.ARABIC -> ARABIC_CODE
            Language.ENGLISH -> ENGLISH_CODE
        }

        NSUserDefaults.standardUserDefaults.setObject(
            listOf(languageCode),
            "AppleLanguages"
        )
        NSUserDefaults.standardUserDefaults.synchronize()
    }

    actual fun applyStoredLanguage() {
        applyLanguage(languagePreferences.getLanguage())
    }

    private companion object {
        const val ARABIC_CODE = "ar"
        const val ENGLISH_CODE = "en"
    }
}