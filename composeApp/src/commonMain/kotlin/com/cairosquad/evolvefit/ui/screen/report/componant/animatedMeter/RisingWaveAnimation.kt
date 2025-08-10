package com.cairosquad.evolvefit.ui.screen.report.componant.animatedMeter

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Path
import com.cairosquad.evolvefit.design_system.theme.Theme
import kotlinx.coroutines.launch
import kotlin.math.PI
import kotlin.math.sin

@Composable
fun RisingWaveAnimation(
    modifier: Modifier = Modifier,
    fillPercent: Float,
    durationMillis: Int = 1200,
    easing: Easing = LinearEasing
) {
    val amplitude1 = 45f
    val amplitude2 = 30f
    val wavelength = 600f
    val phaseOffset = 0.25f
    val verticalOffset = 20f

    val finalPhase = 25f

    val animatedFill = remember { Animatable(0f) }
    val animatedPhase = remember { Animatable(0f) }

    LaunchedEffect(fillPercent, durationMillis, easing) {
        launch {
            animatedFill.animateTo(
                targetValue = fillPercent,
                animationSpec = tween(durationMillis = durationMillis, easing = easing)
            )
        }
        launch {
            animatedPhase.animateTo(
                targetValue = finalPhase,
                animationSpec = tween(durationMillis = durationMillis, easing = easing)
            )
        }
    }

    val waterColor = Theme.color.system.info

    Box(modifier = modifier) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val width = size.width
            val height = size.height
            val baseY = height * (1f - animatedFill.value)

            fun drawWave(amplitude: Float, phaseShift: Float, alpha: Float, yOffset: Float = 0f) {
                val path = Path()
                path.moveTo(0f, baseY + yOffset)

                for (x in 0..width.toInt()) {
                    val y = baseY + yOffset + amplitude *
                            sin((x / wavelength + (animatedPhase.value + phaseShift) / 100) * (2 * PI)).toFloat()
                    path.lineTo(x.toFloat(), y)
                }

                path.lineTo(width, height)
                path.lineTo(0f, height)
                path.close()

                drawPath(path, color = waterColor.copy(alpha = alpha))
            }

            drawWave(amplitude1, 0f, alpha = 0.6f)
            drawWave(amplitude2, phaseOffset * wavelength, alpha = 0.7f, yOffset = verticalOffset)
        }
    }
}