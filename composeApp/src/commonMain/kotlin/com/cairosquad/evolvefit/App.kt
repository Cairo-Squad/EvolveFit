package com.cairosquad.evolvefit

import androidx.compose.runtime.Composable
import coil3.ImageLoader
import coil3.compose.setSingletonImageLoaderFactory
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.ui.navigation.NavigationHost
import com.cairosquad.evolvefit.viewmodel.more.MoreScreenState
import io.github.vinceglb.filekit.coil.addPlatformFileSupport

@Composable
fun App(
    deepLinkRoute: Any? = null,
    currentTheme: MoreScreenState.Theme = MoreScreenState.Theme.LIGHT,
    onLanguageChange: (String) -> Unit = {},
    onThemeChange: (MoreScreenState.Theme) -> Unit = {}
    ) {
    setSingletonImageLoaderFactory { context ->
        ImageLoader.Builder(context)
            .components {
                addPlatformFileSupport()
            }
            .build()
    }
    AppTheme(isDarkTheme = currentTheme == MoreScreenState.Theme.DARK) {
        NavigationHost(deepLinkRoute = deepLinkRoute, onLanguageChange = onLanguageChange, onThemeChange = onThemeChange)
    }
}