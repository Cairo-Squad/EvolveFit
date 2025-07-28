package com.cairosquad.evolvefit.design_system.theme

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowInsetsControllerCompat


@Composable
actual fun UpdateStatusBarIconsForTheme(darkTheme: Boolean) {
    val isDarkIcons = !darkTheme
    val view = LocalView.current
    val window = (view.context as? Activity)?.window ?: return
    WindowInsetsControllerCompat(window, view).isAppearanceLightStatusBars = isDarkIcons
}
