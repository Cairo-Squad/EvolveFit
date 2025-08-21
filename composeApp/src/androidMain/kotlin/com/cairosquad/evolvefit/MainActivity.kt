package com.cairosquad.evolvefit

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.cairosquad.evolvefit.ui.navigation.WorkoutDetailsRoute
import com.cairosquad.evolvefit.repository.profile.local.ProfilePrefrences
import com.cairosquad.evolvefit.ui.util.LocalizationProvider
import com.cairosquad.evolvefit.ui.util.ThemeProvider
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val preferences: ProfilePrefrences by inject()
    private var deepLinkRoute: Any? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instance = this

        installSplashScreen()
        enableEdgeToEdge()

        deepLinkRoute = getDeepLinkRoute(intent)

        setContent {
            LocalizationProvider(preferences) { currentLanguage, onLanguageChange ->
                ThemeProvider(preferences) { currentTheme, onThemeChange ->
                    App(
                        deepLinkRoute = deepLinkRoute,
                        currentTheme = currentTheme,
                        currentLanguage = currentLanguage,
                        onLanguageChange = onLanguageChange,
                        onThemeChange = onThemeChange
                    )
                }
            }
        }
    }

    companion object {
        lateinit var instance: MainActivity
            private set
    }
}

private fun getDeepLinkRoute(intent: Intent?): Any? {
    val data = intent?.data ?: return null
    return if (data.host == "evolvefit.com" && data.pathSegments.firstOrNull() == "workouts") {
        val workoutId = data.lastPathSegment ?: return null
        WorkoutDetailsRoute(workoutId)
    } else null
}
