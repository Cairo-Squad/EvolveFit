package com.cairosquad.evolvefit.ui.screen.report.content.componant.animatedMeter

import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.cairosquad.evolvefit.design_system.theme.Theme
import kotlin.math.PI
import kotlin.math.sin

@Composable
fun RisingWaveAnimation(
    modifier: Modifier = Modifier,
    fillPercent: Float,
    isAnimationStarted: Boolean,
    durationMillis: Int = 1200,
    easing: Easing = LinearEasing
) {
    val amplitude1 = 45f
    val amplitude2 = 30f
    val wavelength = 600f
    val phaseOffset = 0.25f
    val verticalOffset = 20f

    val finalPhase = 25f


    val animatedFillValue by animateFloatAsState(
        targetValue = if (isAnimationStarted) fillPercent else 0f,
        animationSpec = tween(durationMillis = durationMillis, easing = easing),
        label = "fillAnimation"
    )
    val animatedPhaseValue by animateFloatAsState(
        targetValue = if (isAnimationStarted) finalPhase else 0f,
        animationSpec = tween(durationMillis = durationMillis, easing = easing),
        label = "phaseAnimation"
    )

    val waterColor = Theme.color.system.info

    Box(modifier = modifier) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val width = size.width
            val height = size.height
            val baseY = height * (1f - animatedFillValue)

            drawWave(
                width = width,
                height = height,
                amplitude = amplitude1,
                phaseShift = 0f,
                alpha = 0.6f,
                baseY = baseY,
                animatedPhaseValue = animatedPhaseValue,
                waterColor = waterColor,
                wavelength = wavelength,
            )
            drawWave(
                width = width,
                height = height,
                amplitude = amplitude2,
                phaseShift = phaseOffset * wavelength,
                wavelength = wavelength,
                alpha = 0.7f,
                yOffset = verticalOffset,
                baseY = baseY,
                animatedPhaseValue = animatedPhaseValue,
                waterColor = waterColor
            )
        }
    }
}

fun DrawScope.drawWave(
    width: Float,
    height: Float,
    amplitude: Float,
    phaseShift: Float,
    alpha: Float,
    baseY: Float,
    animatedPhaseValue: Float,
    yOffset: Float = 0f,
    waterColor: Color,
    wavelength: Float
) {
    val path = Path()
    path.moveTo(0f, baseY + yOffset)

    for (x in 0..width.toInt()) {
        val y = baseY + yOffset + amplitude *
                sin((x / wavelength + (animatedPhaseValue + phaseShift) / 100) * (2 * PI)).toFloat()
        path.lineTo(x.toFloat(), y)
    }

    path.lineTo(width, height)
    path.lineTo(0f, height)
    path.close()

    drawPath(path, color = waterColor.copy(alpha = alpha))
}