package com.cairosquad.evolvefit

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.cairosquad.evolvefit.repository.profile.local.ProfilePreferences
import com.cairosquad.evolvefit.ui.navigation.WorkoutDetailsRoute
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val preferences: ProfilePreferences by inject()
    private var deepLinkRoute: Any? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instance = this
        installSplashScreen()
        enableEdgeToEdge()

        deepLinkRoute = getDeepLinkRoute(intent)

        setContent {
            App(
                deepLinkRoute = deepLinkRoute,
                preferences = preferences
            )
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
