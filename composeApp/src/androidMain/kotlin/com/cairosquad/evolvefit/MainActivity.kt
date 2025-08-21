package com.cairosquad.evolvefit

import android.content.Intent
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
import com.cairosquad.evolvefit.domain.model.Language
import com.cairosquad.evolvefit.ui.navigation.WorkoutDetailsRoute
import com.cairosquad.evolvefit.ui.util.changeLanguage
import com.cairosquad.evolvefit.viewmodel.more.MoreScreenState

val LocalLocalization = staticCompositionLocalOf { "en" }
val LocalTheme = staticCompositionLocalOf { MoreScreenState.Theme.LIGHT }

class MainActivity : ComponentActivity() {
    private val settingsManager = AppSettingsManager()
    private var deepLinkRoute: Any? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instance = this
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
                    currentLanguage = languageCodeToLanguage(languageCode),
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

    fun getSavedTheme(): MoreScreenState.Theme {
        return settingsManager.getSavedTheme()
    }

    private fun getSavedLanguage(): String {
        return settingsManager.getSavedLanguage()
    }

    private fun saveTheme(theme: MoreScreenState.Theme) {
        settingsManager.saveTheme(theme)
    }

    private fun saveLanguage(language: String) {
        settingsManager.saveLanguage(language)
    }

    companion object {
        lateinit var instance: MainActivity
            private set
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

private fun languageCodeToLanguage(languageCode: String): Language{
    return when(languageCode){
        "en" -> Language.ENGLISH
        else -> Language.ARABIC
    }
}