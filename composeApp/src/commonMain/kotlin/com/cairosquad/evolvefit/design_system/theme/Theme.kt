package com.cairosquad.evolvefit.design_system.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import com.cairosquad.evolvefit.design_system.colors.EvolveFitColors
import com.cairosquad.evolvefit.design_system.colors.LocalEvolveFitColor
import com.cairosquad.evolvefit.design_system.text_styles.EvolveFitTextStyle
import com.cairosquad.evolvefit.design_system.text_styles.LocalEvolveFitTextStyle

object Theme {
    val color: EvolveFitColors
        @Composable @ReadOnlyComposable get() = LocalEvolveFitColor.current

    val textStyle: EvolveFitTextStyle
        @Composable @ReadOnlyComposable get() = LocalEvolveFitTextStyle.current
}