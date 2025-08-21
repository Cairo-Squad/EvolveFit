package com.cairosquad.evolvefit.design_system.colors

import androidx.compose.ui.graphics.Color

val darkThemeColors = ThemeColors(
    brand = Brand(
        primary = Color(0xFF86D01D),
        onPrimary = Color(0xFF181B14),
        primaryContainer = Color(0xFF151814),
        onPrimaryContainer = Color(0xFFBDDF8C),
    ),
    surfaces = Surfaces(
        surface = Color(0xFF090A09),
        onSurface = Color(0xFFF0F5FF),
        surfaceContainer = Color(0xFF1C1C1C),
        onSurfaceContainer = Color(0xFFFDFDFD),
        surfaceVariant = Color(0xFF373737),
        onSurfaceVariant = Color(0xFF828282),
        outline = Color(0xFF9C9C9C),
        outlineVariant = Color(0xFF313131),
        textColor = Color(0xFFFFFFFF),
        onSurfaceAt1 = Color(0xDE000000),
        onSurfaceAt2 = Color(0xAD0B0B0B),
        onSurfaceAt3 = Color(0x61000000),
        onSurfaceAt4 = Color(0x1F000000),
        dropShadowLight = Color(0x29FFFFFF)
    ),
    system = System(
        warning = Color(0xFFF5D02D),
        error = Color(0xFFE65858),
        success = Color(0xFF2DAD58),
        info = Color(0xFF2B60D3),
    ),
    gradiant = Gradiant(
        barGradiant = listOf(
            Color(0xFF86D01D),
            Color(0x03000000),
        ),
        shimmerGradientColors = listOf(
            Color.Transparent,
            Color(0xFF1A1A1A),
            Color.Transparent,
        ),
        iconGradiant =listOf(
            Color(0xFF86D01D),
            Color(0xFFCAE99F)
        ),
        loadingGradientColors = listOf(
            Color.Transparent,
            Color(0xFF1C1C1C),
            Color.Transparent,
        )
    )
)