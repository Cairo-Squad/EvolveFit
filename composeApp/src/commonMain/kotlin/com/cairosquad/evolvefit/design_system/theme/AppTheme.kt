package com.cairosquad.evolvefit.design_system.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import com.cairosquad.evolvefit.design_system.colors.darkThemeColors
import com.cairosquad.evolvefit.design_system.colors.lightThemeColors
import com.cairosquad.evolvefit.design_system.text_styles.DefaultTextStyle
import com.cairosquad.evolvefit.design_system.text_styles.TextStyle

@Composable
fun AppTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    UpdateStatusBarIconsForTheme(isDarkTheme)
    val theme = if (isDarkTheme) darkThemeColors else lightThemeColors
    CompositionLocalProvider(
        LocalThemeColor provides theme,
        LocalTextStyle provides TextStyle()
    ) {
        content()
    }
}
val LocalTextStyle = staticCompositionLocalOf { DefaultTextStyle }
val LocalThemeColor = staticCompositionLocalOf { lightThemeColors }
@Composable
expect fun UpdateStatusBarIconsForTheme(darkTheme: Boolean)
