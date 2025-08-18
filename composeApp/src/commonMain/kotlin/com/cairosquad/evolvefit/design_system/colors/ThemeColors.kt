package com.cairosquad.evolvefit.design_system.colors

import androidx.compose.ui.graphics.Color

data class ThemeColors(
    val brand: Brand,
    val surfaces: Surfaces,
    val system: System,
    val gradiant: Gradiant
)
data class Surfaces(
    val surface: Color,
    val onSurface: Color,
    val surfaceContainer: Color,
    val onSurfaceContainer: Color,
    val textColor : Color,
    val surfaceVariant: Color,
    val onSurfaceVariant: Color,
    val outline: Color,
    val outlineVariant: Color,
    val onSurfaceAt1: Color,
    val onSurfaceAt2: Color,
    val onSurfaceAt3: Color,
    val onSurfaceAt4: Color
)
data class Brand(
    val primary: Color,
    val onPrimary: Color,
    val primaryContainer: Color,
    val onPrimaryContainer: Color,
)
data class System(
    val warning: Color,
    val error: Color,
    val success: Color,
    val info: Color,
)

data class Gradiant(
    val barGradiant: List<Color>,
    val shimmerGradientColors: List<Color>,
    val loadingGradientColors: List<Color>
)
