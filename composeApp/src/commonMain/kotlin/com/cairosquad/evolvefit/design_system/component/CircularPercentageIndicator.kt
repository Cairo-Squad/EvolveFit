package com.cairosquad.evolvefit.design_system.component

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CircularProgressCard(
    modifier: Modifier = Modifier,
    percentage: Float,
    number: Int = 100,
    radius: Dp = 32.dp,
    strokeWidth: Dp = 2.dp,
    animationDuration: Int = 5_000,
    animationDelay: Int = 0,
    progressColor: Color,
    backgroundColor: Color = Theme.color.surfaces.surfaceVariant
) {

    var hasAnimationPlayed by remember { mutableStateOf(false) }

//    val animateCurrentPercentage by animateFloatAsState(
//        targetValue = if (hasAnimationPlayed) percentage else 0f,
//        animationSpec = tween(
//            durationMillis = animationDuration,
//            delayMillis = animationDelay,
//            easing = LinearEasing
//        )
//    )
    val animateCurrentPercentage by animateFloatAsState(
        targetValue = percentage,
        animationSpec = tween(
            durationMillis = animationDuration,
            delayMillis = animationDelay,
            easing = LinearEasing
        ),
        label = "progressAnimation"
    )

    LaunchedEffect(percentage) {
        hasAnimationPlayed = true
    }

    Box(
        modifier = modifier
            .size(radius * 2f + strokeWidth),
        contentAlignment = Alignment.Center,
    ) {
        Canvas(modifier = Modifier.size(radius * 2f)){
            drawArc(
                color = backgroundColor,
                -90f,
                360f,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
            drawArc(
                color = progressColor,
                -90f,
                360 * animateCurrentPercentage,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }
        Text(
            text = (animateCurrentPercentage * number).toInt().toString() + "%",
            style = Theme.textStyle.headline.mediumMedium16,
            color = Theme.color.surfaces.outline,
        )
    }
}

@Preview
@Composable
private fun CircularProgressCardPreview() {
    CircularProgressCard(
        percentage = 0.50f,
        number = 100,
        radius = 40.dp,
        strokeWidth = 8.dp,
        animationDuration = 1000,
        progressColor = Color.Blue
    )
}