package com.cairosquad.evolvefit.design_system.theme

import android.os.Build
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat

@Composable
actual fun UpdateStatusBarIconsForTheme(isStatusBarIconsLight: Boolean) {
    val view = LocalView.current
    val window = (view.context as? ComponentActivity)?.window ?: return

    WindowInsetsControllerCompat(window, view).apply {
        isAppearanceLightStatusBars = !isStatusBarIconsLight
    }
}

@Composable
actual fun UpdateNavBarColorForTheme(isDark: Boolean, color: Int) {
    val view = LocalView.current
    val window = (view.context as? ComponentActivity)?.window ?: return

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        window.setNavigationBarContrastEnforced(false)
    }

    WindowInsetsControllerCompat(window, view).apply {
        isAppearanceLightNavigationBars = !isDark
    }

    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.navigationBarColor = color //Theme.color.surfaces.surface.toArgb()
    }
}