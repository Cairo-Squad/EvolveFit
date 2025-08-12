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

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(data) {
                detectTapGestures { offset -> onTap(offset) }
            }
    ) {
        val canvasWidth = this.size.width
        val canvasHeight = this.size.height + 3
        val size = data.size
        val spacingX = if (size == 1) 0f else canvasWidth / (size - 1)

        val anchors = List(size) { i ->
            val x = i * spacingX
            val normalized = (data[i] / maxValue).coerceIn(0f, 1f)
            val y = canvasHeight - normalized * canvasHeight
            Offset(x, y)
        }

        onAnchorsComputed(anchors)

        val sampled = PathFunctions.catmullRomSpline(anchors, samplesPerSegment = 20)
        val linePath = PathFunctions.buildPathFromPoints(sampled)
        val areaPath = PathFunctions.buildAreaPath(linePath, anchors, canvasHeight)

        clipRect(right = (this.size.width * animationProgress) + 3.dp.toPx()) {
            drawPath(
                areaPath,
                brush = Brush.verticalGradient(areaColor, startY = -75f)
            )
            drawPath(
                path = linePath,
                color = lineColor,
                style = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round)
            )
        }

        if (markerProgress <= 0f) return@Canvas
        val maxIndex = data.indices.maxByOrNull { data[it] } ?: -1
        if (maxIndex !in anchors.indices) return@Canvas
        val anchorPoint = anchors[maxIndex]

        if (maxIndex in anchors.indices) {

            val lineAlpha = markerProgress
            val circleRadius = markerRadiusPx * markerProgress
            val outerCircleRadius = (markerRadiusPx * 1.5f) * markerProgress

            drawLine(
                color = dashedLineColor.copy(alpha = 1f * lineAlpha),
                start = Offset(anchorPoint.x, 0f),
                end = Offset(anchorPoint.x, canvasHeight),
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
                style = Stroke(width = 2.dp.toPx())
            )
        }

    }
}
