package com.cairosquad.evolvefit.design_system.colors

import androidx.compose.ui.graphics.Color

val lightThemeColors = EvolveFitColors(
    brand = Brand(
        primary = Color(0xFFC6E641),
        onPrimary = Color(0xFF161B07),
        primaryContainer = Color(0xFFF3FAD1),
        onPrimaryContainer = Color(0xFF96871D),
    ),
    surfaces = Surfaces(
        surface = Color(0xFFFFFFFF),
        onSurface = Color(0xFF2C2C2C),
        surfaceContainer = Color(0xFFF8F8F8),
        onSurfaceContainer = Color(0xFF333333),
        textColor = Color(0xFFFFFFFF),
        surfaceVariant = Color(0xFFF2F2F2),
        onSurfaceVariant = Color(0xFF929292),
        outline = Color(0xFF989898),
        outlineVariant = Color(0xFFE9E9E7),
        onSurfaceAt1 = Color(0xDEACABAC),
        onSurfaceAt2 = Color(0x61ACABAC),
        onSurfaceAt3 = Color(0x1FACABAC),
        onSurfaceAt4 = Color(0x66919191),
    ),
    system = System(
        warning = Color(0xFFD9C800),
        success = Color(0xFF2C992A),
        info = Color(0xFF2B60D3),
    )
)
