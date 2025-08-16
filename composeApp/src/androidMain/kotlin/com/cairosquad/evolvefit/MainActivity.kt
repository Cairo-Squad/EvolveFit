package com.cairosquad.evolvefit

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.cairosquad.evolvefit.ui.navigation.WorkoutDetailsRoute
import com.cairosquad.evolvefit.ui.util.LanguageManager
import org.koin.android.ext.android.get

class MainActivity : ComponentActivity() {
    private var deepLinkRoute: Any? = null

    companion object {
        lateinit var instance: MainActivity
            private set
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instance = this
        installSplashScreen()
        enableEdgeToEdge()
        val languageManager = get<LanguageManager>()
        languageManager.applyStoredLanguage()
        deepLinkRoute = getDeepLinkRoute(intent)
        setContent {
            App(deepLinkRoute)
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