package com.cairosquad.evolvefit.design_system.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.cairosquad.evolvefit.design_system.colors.LocalEvolveFitColor
import com.cairosquad.evolvefit.design_system.colors.darkThemeColors
import com.cairosquad.evolvefit.design_system.colors.lightThemeColors
import com.cairosquad.evolvefit.design_system.text_styles.LatoTextStyle
import com.cairosquad.evolvefit.design_system.text_styles.LocalEvolveFitTextStyle

@Composable
fun EvolveFitTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    UpdateStatusBarIconsForTheme(isDarkTheme)
    val theme = if (isDarkTheme) darkThemeColors else lightThemeColors

    CompositionLocalProvider(
        LocalEvolveFitColor provides theme,
        LocalEvolveFitTextStyle provides LatoTextStyle(),

        ) {
        content()
    }
}

@Composable
expect fun UpdateStatusBarIconsForTheme(darkTheme: Boolean)
