package com.cairosquad.evolvefit.ui.screen.report.componant

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ic_water_drop
import evolvefit.composeapp.generated.resources.liters
import evolvefit.composeapp.generated.resources.water
import evolvefit.composeapp.generated.resources.water_drops
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.math.PI
import kotlin.math.sin

@Composable
fun WaterCard(
    waterConsumed: String,
    modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Theme.color.surfaces.surfaceContainer)
            .padding(horizontal = 12.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CardHeaderSection(
            painter = painterResource(Res.drawable.ic_water_drop),
            title = stringResource(Res.string.water),
            tint = Theme.color.system.info
        )
        Box(
            modifier = Modifier
                .padding(top = 16.dp)
                .width(134.dp)
                .weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
                    .border(
                        width = 5.dp,
                        shape = CircleShape,
                        color = Theme.color.system.info
                    )
            ) {
                Image(
                    modifier = Modifier.padding(8.dp),
                    painter = painterResource(Res.drawable.water_drops),
                    contentDescription = stringResource(Res.string.water_drops)
                )
                RisingWaveAnimation(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .scale(scaleX = -1f, scaleY = 1f),
                    fillPercent = 0.5f
                )
            }
            Row(
                modifier = Modifier.align(Alignment.Center)
                    .offset(y = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = waterConsumed,
                    color = Theme.color.surfaces.textColor,
                    style = Theme.textStyle.display.largeBold24
                )
                Text(
                    text = "/${stringResource(Res.string.liters)}",
                    color = Theme.color.surfaces.textColor,
                    style = Theme.textStyle.label.smallRegular14
                )
            }
        }
    }
}


@Composable
private fun RisingWaveAnimation(
    modifier: Modifier = Modifier,
    fillPercent: Float,
    durationMillis: Int = 2000,
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

@Preview
@Composable
private fun WaterCardPreview() {
    WaterCard(
        waterConsumed = "06",
        modifier = Modifier
            .width(160.dp).height(214.dp)
    )
}

@Preview
@Composable
private fun WaterCardLongPreview() {
    WaterCard(
        waterConsumed = "08",
        modifier = Modifier.width(160.dp).height(260.dp)
    )
}