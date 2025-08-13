package com.cairosquad.evolvefit.ui.screen.report.componant.animatedMeter.chartComponent

import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp


@Composable
fun ChartCanvas(
    data: List<Float>,
    lineColor: Color,
    areaColor: List<Color>,
    markerColor: Color,
    dashedLineColor: Color,
    markerRadiusPx: Float,
    maxValue: Float,
    isAnimationStarted: Boolean,
    animationDuration: Int = 1200,
    animationEasing: Easing = LinearOutSlowInEasing,
    onAnchorsComputed: (List<Offset>) -> Unit,
    onTap: (Offset) -> Unit
) {
    val animationProgress by animateFloatAsState(
        targetValue = if (isAnimationStarted) 1f else 0f,
        animationSpec = tween(
            durationMillis = animationDuration,
            easing = animationEasing
        ),
        label = "chartAnimation"
    )
    val markerProgress by animateFloatAsState(
        targetValue = if (animationProgress >= 1f) 1f else 0f,
        animationSpec = tween(
            durationMillis = 400,
            easing = LinearOutSlowInEasing
        ),
        label = "markerAnimation"
    )

    if (data.isEmpty()) {
        return
    }

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(data) {
                detectTapGestures { offset -> onTap(offset) }
            }
    ) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        val count = data.size

        val strokeWidthPx = 2.dp.toPx()
        val maxOuterRadius = markerRadiusPx
        val inset = maxOuterRadius + strokeWidthPx

        val availableWidth = (canvasWidth - 2f * inset).coerceAtLeast(0f)
        val availableHeight = (canvasHeight - 1f * inset).coerceAtLeast(0f)

        val spacingX = if (count == 1) 0f else availableWidth / (count - 1)

        val anchors = List(count) { i ->
            val x = if (count == 1) {
                inset + availableWidth / 2f
            } else {
                inset + i * spacingX
            }

            val normalized = (data[i] / maxValue).coerceIn(0f, 1f)
            val y = inset + (1.05f - normalized) * availableHeight

            Offset(x, y)
        }

        onAnchorsComputed(anchors)

        val sampled = PathFunctions.catmullRomSpline(anchors, samplesPerSegment = 12)
        val minY = inset
        val maxY = canvasHeight - inset
        val clampedSampled = sampled.map { p ->
            Offset(p.x, p.y.coerceIn(minY, maxY))
        }

        val linePath = PathFunctions.buildPathFromPoints(clampedSampled)
        val clampedAnchors = anchors.map { a -> Offset(a.x, a.y.coerceIn(minY, maxY)) }

        val areaPath = PathFunctions.buildAreaPath(linePath, clampedAnchors, canvasHeight - inset)

        clipRect(right = (this.size.width * animationProgress)) {
            drawPath(
                areaPath,
                brush = Brush.verticalGradient(areaColor, startY = inset - 75f)
            )
            drawPath(
                path = linePath,
                color = lineColor,
                style = Stroke(width = strokeWidthPx, cap = StrokeCap.Round)
            )
        }

        if (markerProgress <= 0f) return@Canvas
        val maxIndex = data.indices.maxByOrNull { data[it] } ?: -1
        if (maxIndex !in anchors.indices) return@Canvas
        val anchorPoint = anchors[maxIndex]

        val lineAlpha = markerProgress
        val circleRadius = markerRadiusPx * markerProgress
        val outerCircleRadius = (markerRadiusPx * 1.5f) * markerProgress

        drawLine(
            color = dashedLineColor.copy(alpha = lineAlpha),
            start = Offset(anchorPoint.x, inset),
            end = Offset(anchorPoint.x, canvasHeight - inset),
            strokeWidth = 1.dp.toPx(),
            cap = StrokeCap.Round,
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 8f), 0f)
        )

        drawCircle(
            color = markerColor.copy(alpha = lineAlpha),
            center = anchorPoint,
            radius = circleRadius
        )

        drawCircle(
            color = markerColor.copy(alpha = lineAlpha),
            center = anchorPoint,
            radius = outerCircleRadius,
            style = Stroke(width = strokeWidthPx)
        )
    }
}