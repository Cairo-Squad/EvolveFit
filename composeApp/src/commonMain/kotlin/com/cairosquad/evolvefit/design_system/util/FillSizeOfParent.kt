package com.cairosquad.evolvefit.design_system.util

import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.Dp

@Stable
fun Modifier.fillWidthOfParent(parentPadding: Dp) = layout { measurable, constraints ->
    val newMaxWidth = constraints.maxWidth + 2 * parentPadding.roundToPx()
    val newConstraints = constraints.copy(maxWidth = newMaxWidth)
    val placeable = measurable.measure(newConstraints)
    layout(constraints.maxWidth, placeable.height) {
        placeable.place(-parentPadding.roundToPx(), 0)
    }
}