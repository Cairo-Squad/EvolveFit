package com.cairosquad.evolvefit.ui.screen.report.componant.animatedMeter

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ic_meter
import evolvefit.composeapp.generated.resources.water_drops
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CaloriesMeter(
    expectedCalories: Int,
    takenCalories: Int,
    modifier: Modifier = Modifier,
) {
    val backgroundColor = Theme.color.surfaces.outlineVariant
    val progressColor = Theme.color.system.success
    var isAnimationStarted by remember { mutableStateOf(false) }


    val animateCurrentPercentage by animateFloatAsState(
        targetValue = if (isAnimationStarted) (takenCalories.toFloat() / expectedCalories) else 0f,
        animationSpec = tween(
            durationMillis = 1200,
            easing = LinearOutSlowInEasing
        )
    )
    val rotationState by animateFloatAsState(
        targetValue = if (isAnimationStarted) 85f else 85f,
        animationSpec = tween(
            durationMillis = 1200,
            easing = LinearOutSlowInEasing
        )
    )

    val xOffset by animateFloatAsState(
        targetValue = if (isAnimationStarted) 0f else -0.5f,
        animationSpec = tween(
            durationMillis = 1200,
            easing = LinearOutSlowInEasing
        )
    )

    val sweepAngle = 180f * animateCurrentPercentage
    val strokeWidth = 13.dp

    LaunchedEffect(true) {
        isAnimationStarted = true
    }

    Box(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .width(123.dp + strokeWidth)
                .align(Alignment.TopCenter)
        ) {
            Canvas(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .width(123.dp).height((123).dp)
            ) {
                drawArc(
                    color = backgroundColor,
                    180f,
                    180f,
                    useCenter = false,
                    style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Square)
                )
                drawArc(
                    color = progressColor,
                    180f,
                    sweepAngle,
                    useCenter = false,
                    style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Square)
                )
            }
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(80.dp)
                    .offset(y = 11.dp),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .background(backgroundColor, CircleShape)
                )
                Image(
                    painter = painterResource(Res.drawable.ic_meter),
                    contentDescription = stringResource(Res.string.water_drops),
                    modifier = Modifier
                        .size(40.dp)
                        .align(Alignment.TopCenter).graphicsLayer {
                            rotationZ = sweepAngle - rotationState
                            transformOrigin =
                                TransformOrigin(pivotFractionX = 0.5f, pivotFractionY = 1f)
                        }
                        .offset(y = 3.25.dp, x = xOffset.dp),
                    colorFilter = ColorFilter.tint(Color.White)
                )
                Box(
                    modifier = Modifier
                        .size(4.dp)
                        .background(backgroundColor, CircleShape)
                )
            }
        }
        Column(
            modifier = Modifier.fillMaxWidth()
                .align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = takenCalories.toString(),
                style = Theme.textStyle.headline.largeBold18,
                color = Theme.color.surfaces.onSurfaceContainer
            )
            Text(
                text = "From $expectedCalories kcal",
                style = Theme.textStyle.label.smallRegular12,
                color = Theme.color.surfaces.onSurfaceVariant
            )
        }
    }

}


@Preview
@Composable
private fun WaterMeterPreview() {
    CaloriesMeter(
        expectedCalories = 2500,
        takenCalories = 1000
    )
}