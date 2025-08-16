package com.cairosquad.evolvefit.ui.util


import com.cairosquad.evolvefit.domain.model.Language
import com.cairosquad.evolvefit.repository.profile.local.LanguagePreferences

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class LanguageManager(
    languagePreferences: LanguagePreferences
) {
    fun applyLanguage(language: Language)
    fun applyStoredLanguage()
}