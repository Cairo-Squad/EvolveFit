package com.cairosquad.evolvefit.ui.util

import android.content.Context
import android.content.res.Configuration
import com.cairosquad.evolvefit.domain.model.Language
import com.cairosquad.evolvefit.repository.profile.local.LanguagePreferences
import org.koin.java.KoinJavaComponent.get
import java.util.Locale

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING", "DEPRECATION")
actual class LanguageManager actual constructor(
    private val languagePreferences: LanguagePreferences
) {
    actual fun applyLanguage(language: Language) {
        val context = get<Context>(Context::class.java)
        val locale = when (language) {
            Language.ARABIC -> Locale("ar")
            Language.ENGLISH -> Locale("en")
        }

        Locale.setDefault(locale)
        val config = Configuration(context.resources.configuration)
        config.setLocale(locale)
        context.resources.updateConfiguration(config, context.resources.displayMetrics)
    }

    actual fun applyStoredLanguage() {
        val savedLanguage = languagePreferences.getLanguage()
        applyLanguage(savedLanguage)
    }
}