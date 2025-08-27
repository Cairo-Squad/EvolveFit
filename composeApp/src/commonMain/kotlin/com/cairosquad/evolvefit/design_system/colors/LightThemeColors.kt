package com.cairosquad.evolvefit.design_system.colors

import androidx.compose.ui.graphics.Color

val lightThemeColors = ThemeColors(
    brand = Brand(
        primary = Color(0xFF9BE03A),
        onPrimary = Color(0xFF161B07),
        primaryContainer = Color(0xFFF3FAD1),
        onPrimaryContainer = Color(0xFFD0F898),
    ),
    surfaces = Surfaces(
        surface = Color(0xFFFDFDFD),
        onSurface = Color(0xFF2C2C2C),
        surfaceContainer = Color(0xFFF8F8F8),
        onSurfaceContainer = Color(0xFF333333),
        textColor = Color(0xFFFFFFFF),
        surfaceVariant = Color(0xFFFFFFFF),
        onSurfaceVariant = Color(0xFF929292),
        outline = Color(0xFF989898),
        outlineVariant = Color(0xFFE6E9DB),
        onSurfaceAt1 = Color(0xAD000000),
        onSurfaceAt2 = Color(0x66000000),
        onSurfaceAt3 = Color(0x3D000000),
        onSurfaceAt4 = Color(0x66919191),
        dropShadow = Color(0x29000000)
    ),
    system = System(
        warning = Color(0xFFD9CB00),
        error = Color(0xFFF65659),
        success = Color(0xFF8DD876),
        info = Color(0xFF4E95FF),
    ),
    gradiant = Gradiant(
        barGradiant = listOf(
            Color(0xFF86D01D),
            Color(0x03FFFFFF)
        ),
        iconGradiant =listOf(
            Color(0xFF86D01D),
            Color(0xFFCAE99F)
        ),
        shimmerGradientColors = listOf(
            Color.Transparent,
            Color(0xFFFFFFFF),
            Color.Transparent,
        ),
        loadingGradientColors = listOf(
            Color.Transparent,
            Color(0xFFF8F8F8),
            Color.Transparent,
        ),
    ),
    isDark = false
)
