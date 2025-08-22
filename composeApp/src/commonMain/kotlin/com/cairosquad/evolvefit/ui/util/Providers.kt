package com.cairosquad.evolvefit.ui.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import com.cairosquad.evolvefit.domain.model.Language
import com.cairosquad.evolvefit.repository.profile.local.ProfilePreferences
import com.cairosquad.evolvefit.viewmodel.more.MoreScreenState

val LocalLocalization = staticCompositionLocalOf { Language.ENGLISH }
val LocalTheme = staticCompositionLocalOf { MoreScreenState.Theme.LIGHT }

@Composable
fun LocalizationProvider(
    settingsManager: ProfilePreferences,
    content: @Composable (Language, (String) -> Unit) -> Unit
) {
    var languageCode by remember { mutableStateOf(settingsManager.getSavedLanguage()) }
    val currentLanguage = when (languageCode) {
        "en" -> Language.ENGLISH
        else -> Language.ARABIC
    }

    CompositionLocalProvider(LocalLocalization provides currentLanguage) {
        content(
            currentLanguage
        ) { newLang ->
            languageCode = newLang
            settingsManager.saveLanguage(newLang)
            changeLanguage(newLang)
        }
    }
}

@Composable
fun ThemeProvider(
    settingsManager: ProfilePreferences,
    content: @Composable (MoreScreenState.Theme, (MoreScreenState.Theme) -> Unit) -> Unit
) {
    var appTheme by remember { mutableStateOf(settingsManager.getSavedTheme()) }

    CompositionLocalProvider(LocalTheme provides appTheme) {
        content(
            appTheme
        ) { newTheme ->
            appTheme = newTheme
            settingsManager.saveTheme(newTheme)
        }
    }
}
