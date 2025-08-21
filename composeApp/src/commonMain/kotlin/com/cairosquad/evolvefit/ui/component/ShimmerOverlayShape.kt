package com.cairosquad.evolvefit.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import com.cairosquad.evolvefit.design_system.theme.Theme

@Composable
fun ShimmerOverlayShape(
    shimmerBrush: Brush,
    modifier: Modifier = Modifier,
    shape: Shape =CircleShape,
    background: Color = Theme.color.surfaces.surfaceVariant
) {
    Box(modifier = modifier.clip(shape)) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(background)
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(shimmerBrush)
        )
    }
}