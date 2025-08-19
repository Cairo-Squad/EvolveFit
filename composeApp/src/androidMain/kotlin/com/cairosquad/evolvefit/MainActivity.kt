package com.cairosquad.evolvefit

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.cairosquad.evolvefit.ui.navigation.WorkoutDetailsRoute
import com.cairosquad.evolvefit.viewmodel.more.MoreScreenState
import androidx.core.content.edit
import com.cairosquad.evolvefit.ui.util.changeLanguage

val LocalLocalization = staticCompositionLocalOf { "en" }
val LocalTheme = staticCompositionLocalOf { MoreScreenState.Theme.LIGHT }

class MainActivity : ComponentActivity() {
    private lateinit var preferences: SharedPreferences
    private var deepLinkRoute: Any? = null

    companion object {
        lateinit var instance: MainActivity
            private set
        private const val PREFS_NAME = "evolvefit_preferences"
        private const val KEY_THEME = "theme"
        private const val KEY_LANGUAGE = "language"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instance = this
        preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        installSplashScreen()
        enableEdgeToEdge()
        deepLinkRoute = getDeepLinkRoute(intent)
        setContent {
            val savedTheme = getSavedTheme()
            val savedLanguage = getSavedLanguage()

            var languageCode by remember { mutableStateOf(savedLanguage) }
            var appTheme by remember { mutableStateOf(savedTheme) }
            CompositionLocalProvider(
                LocalLocalization provides languageCode,
                LocalTheme provides appTheme
            ) {
                App(
                    deepLinkRoute = deepLinkRoute,
                    currentTheme = appTheme,
                    onLanguageChange = { newLang ->
                        languageCode = newLang
                        saveLanguage(newLang)
                        changeLanguage(newLang)
                    },
                    onThemeChange = { newTheme ->
                        appTheme = newTheme
                        saveTheme(newTheme)
                    }
                )
            }
        }
    }

    private fun getSavedTheme(): MoreScreenState.Theme {
        val themeName = preferences.getString(KEY_THEME, "LIGHT")
        return when (themeName) {
            "DARK" -> MoreScreenState.Theme.DARK
            else -> MoreScreenState.Theme.LIGHT
        }
    }

    private fun getSavedLanguage(): String {
        return preferences.getString(KEY_LANGUAGE, "en") ?: "en"
    }

    private fun saveTheme(theme: MoreScreenState.Theme) {
        preferences.edit {
            putString(KEY_THEME, theme.name)
        }
    }

    private fun saveLanguage(language: String) {
        preferences.edit {
            putString(KEY_LANGUAGE, language)
        }
    }
}

private fun getDeepLinkRoute(intent: Intent?): Any? {
    val data = intent?.data ?: return null
    if (data.host == "evolvefit.com" && data.pathSegments.firstOrNull() == "workouts") {
        val workoutId = data.lastPathSegment ?: return null
        return WorkoutDetailsRoute(workoutId)
    }
    return null
}