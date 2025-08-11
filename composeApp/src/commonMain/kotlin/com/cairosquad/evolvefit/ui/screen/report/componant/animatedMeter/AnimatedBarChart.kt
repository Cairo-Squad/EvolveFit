package com.cairosquad.evolvefit.ui.screen.report.componant.animatedMeter

import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun AnimatedBarChart(
    data: List<Float>,
    focusedBarColor: Color,
    unfocusedBarColor: Color,
    isAnimationStarted: Boolean,
    animationDuration: Int = 1200,
    animationEasing: Easing = LinearEasing,
    onBarCentersCalculated: (List<Float>) -> Unit = {}
) {
    val maxValue = (data.maxOrNull() ?: 0f).coerceAtLeast(1f)
    val maxIndex = data.indexOf(maxValue)

    val animatedHeights = data.map { value ->
        animateFloatAsState(
            targetValue = if (isAnimationStarted) value / maxValue else 0f,
            animationSpec = tween(
                durationMillis = animationDuration,
                easing = animationEasing
            ),
            label = "barHeightAnim"
        ).value
    }

    Canvas(modifier = Modifier.height(144.dp).fillMaxWidth()) {
        val maxHeight = size.height
        val barCount = data.size
        val totalWidth = size.width

        val barWidthPx = totalWidth / (barCount * 2 - 1)
        val gapPx = barWidthPx

        val centers = mutableListOf<Float>()

        data.forEachIndexed { index, _ ->
            val barHeight = animatedHeights[index] * maxHeight
            val barX = index * (barWidthPx + gapPx)
            val isMax = index == maxIndex
            val centerX = barX + barWidthPx / 2
            centers.add(centerX)

            if (barHeight != 0f) {
                val path = Path().apply {
                    val left = barX
                    val top = maxHeight - barHeight
                    val right = barX + barWidthPx
                    val bottom = maxHeight
                    val radius = 16f

                    moveTo(left, bottom)
                    lineTo(left, top + radius)
                    quadraticTo(left, top, left + radius, top)
                    lineTo(right - radius, top)
                    quadraticTo(right, top, right, top + radius)
                    lineTo(right, bottom)
                    close()
                }
                drawPath(
                    path = path,
                    color = if (isMax) focusedBarColor else unfocusedBarColor
                )
            }
        }

        onBarCentersCalculated(centers)
    }
}

@Preview
@Composable
private fun AnimatedBarChartPreview() {
    AnimatedBarChart(
        data = listOf(10f, 20f, 30f, 40f, 50f),
        focusedBarColor = Theme.color.brand.primary,
        unfocusedBarColor = Theme.color.surfaces.surfaceVariant,
        true
    )
}