package com.cairosquad.evolvefit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import coil3.ImageLoader
import coil3.compose.setSingletonImageLoaderFactory
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.repository.profile.local.ProfilePreferences
import com.cairosquad.evolvefit.ui.navigation.NavigationHost
import com.cairosquad.evolvefit.ui.util.LocalizationProvider
import com.cairosquad.evolvefit.ui.util.ThemeProvider
import com.cairosquad.evolvefit.ui.util.changeLanguage
import com.cairosquad.evolvefit.viewmodel.more.MoreScreenState
import io.github.vinceglb.filekit.coil.addPlatformFileSupport
import org.koin.compose.koinInject

@Composable
fun App(
    deepLinkRoute: Any? = null,
    preferences: ProfilePreferences? = null
) {
    val prefs = preferences ?: koinInject<ProfilePreferences>()

    LaunchedEffect(Unit) {
        val savedLanguage = prefs.getSavedLanguage()
        changeLanguage(savedLanguage)
    }

    setSingletonImageLoaderFactory { context ->
        ImageLoader.Builder(context)
            .components {
                addPlatformFileSupport()
            }
            .build()
    }

    LocalizationProvider(prefs) { currentLanguage, onLanguageChange ->
        ThemeProvider(prefs) { currentTheme, onThemeChange ->
            AppTheme(isDarkTheme = currentTheme == MoreScreenState.Theme.DARK) {
                NavigationHost(
                    deepLinkRoute = deepLinkRoute,
                    onLanguageChange = onLanguageChange,
                    onThemeChange = onThemeChange
                )
            }
        }
    }
}