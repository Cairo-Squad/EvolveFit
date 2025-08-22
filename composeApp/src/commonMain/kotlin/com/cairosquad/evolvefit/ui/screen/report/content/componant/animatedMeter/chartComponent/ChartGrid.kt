package com.cairosquad.evolvefit.ui.screen.report.content.componant.animatedMeter.chartComponent

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp

@Composable
fun ChartGrid(
    gridLinesColor: Color,
    steps: Int,
    modifier: Modifier = Modifier
) {
    Canvas(
        modifier = modifier
    ) {
        val maxHeight = size.height

        for (i in 0..steps) {
            val y = maxHeight - (i / steps.toFloat()) * maxHeight
            drawLine(
                color = gridLinesColor,
                start = Offset(0f, y),
                end = Offset(size.width, y),
                strokeWidth = 0.5.dp.toPx(),
                cap = StrokeCap.Square
            )
        }
    }
}