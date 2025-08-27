package com.cairosquad.evolvefit.design_system.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.toArgb
import com.cairosquad.evolvefit.design_system.colors.darkThemeColors
import com.cairosquad.evolvefit.design_system.colors.lightThemeColors
import com.cairosquad.evolvefit.design_system.text_styles.DefaultTextStyle
import com.cairosquad.evolvefit.design_system.text_styles.TextStyle

@Composable
fun AppTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val themeColors = if (isDarkTheme) darkThemeColors else lightThemeColors

    UpdateStatusBarIconsForTheme(isDarkTheme)
    UpdateNavBarColorForTheme(isDarkTheme, themeColors.surfaces.surface.toArgb())
    CompositionLocalProvider(
        LocalThemeColor provides themeColors,
        LocalTextStyle provides TextStyle(),
        LocalIsDark provides isDarkTheme
    ) {
        content()
    }
}

val LocalTextStyle = staticCompositionLocalOf { DefaultTextStyle }
val LocalThemeColor = staticCompositionLocalOf { lightThemeColors }
val LocalIsDark = staticCompositionLocalOf { true }

@Composable
expect fun UpdateStatusBarIconsForTheme(isStatusBarIconsLight: Boolean)

@Composable
expect fun UpdateNavBarColorForTheme(isDark: Boolean, color: Int)