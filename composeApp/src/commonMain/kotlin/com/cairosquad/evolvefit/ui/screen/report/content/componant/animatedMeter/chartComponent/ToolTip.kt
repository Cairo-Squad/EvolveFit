package com.cairosquad.evolvefit.ui.screen.report.content.componant.animatedMeter.chartComponent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme

@Composable
fun Tooltip(
    canvasPoint: Offset,
    parentWidthPx: Float,
    parentHeightPx: Float,
    density: Density,
    content: @Composable () -> Unit
) {
    val xDp = with(density) { canvasPoint.x.toDp() }
    val yDp = with(density) { canvasPoint.y.toDp() }

    val tooltipMinW = 72.dp
    val tooltipH = 36.dp

    val rawOffsetX = xDp - (tooltipMinW / 2f)
    val rawOffsetY = yDp - tooltipH - 8.dp

    val parentWidthDp = with(density) { parentWidthPx.toDp() }
    val parentHeightDp = with(density) { parentHeightPx.toDp() }

    val clampedX = rawOffsetX.coerceIn(0.dp, parentWidthDp - tooltipMinW)
    val clampedY = rawOffsetY.coerceIn(0.dp, parentHeightDp + tooltipH + 10.dp)

    Box(
        Modifier.height(30.dp)
            .offset(x = clampedX, y = clampedY)
    ) {
        Box(
            modifier = Modifier
                .graphicsLayer {
                    rotationY = 90f
                }
                .align(Alignment.BottomCenter)
                .size(12.dp)
                .clip(RoundedCornerShape(1.dp))
                .background(Theme.color.surfaces.surfaceVariant)
        )
        Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .widthIn(min = tooltipMinW)
                    .background(Theme.color.surfaces.surfaceVariant, RoundedCornerShape(8.dp))
                    .padding(horizontal = 8.dp, vertical = 6.dp),
        contentAlignment = Alignment.Center
        ) {
        content()
    }
    }
}