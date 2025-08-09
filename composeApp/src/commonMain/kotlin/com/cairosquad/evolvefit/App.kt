package com.cairosquad.evolvefit

import androidx.compose.runtime.Composable
import coil3.ImageLoader
import coil3.compose.setSingletonImageLoaderFactory
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.local.AuthPreferences
import com.cairosquad.evolvefit.ui.navigation.NavigationHost
import io.github.vinceglb.filekit.coil.addPlatformFileSupport

@Composable
fun App(authPreferences: AuthPreferences, initialAccessToken: String?) {
    setSingletonImageLoaderFactory { context ->
        ImageLoader.Builder(context)
            .components {
                addPlatformFileSupport()
            }
            .build()
    }
    AppTheme {
        NavigationHost(authPreferences = authPreferences, initialAccessToken = initialAccessToken)
    }
}