package com.cairosquad.evolvefit.design_system.component

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CircularTimer(
    timeSeconds: Int,
    onFinish: (Float) -> Unit,
    modifier: Modifier = Modifier,
    reverse: Boolean = false,
    radius: Dp = 75.dp,
    strokeWidth: Dp = 12.dp,
    animationDurationMilli: Int = timeSeconds * 1000,
    animationDelay: Int = 0,
    progressColor: Color = Theme.color.brand.primary,
    textStyle: TextStyle = Theme.textStyle.title.largeBold16.copy(fontSize = 40.sp),
    textColor: Color = Theme.color.surfaces.textColor,
    backgroundColor: Color = Theme.color.surfaces.onSurfaceAt4
) {

    var hasAnimationPlayed by remember { mutableStateOf(false) }

    val startValue = remember { if (reverse) timeSeconds.toFloat() else 0f }
    val endValue = remember { if (reverse) 0f else timeSeconds.toFloat() }

    val animateValue by animateFloatAsState(
        targetValue = if (hasAnimationPlayed) endValue else startValue,
        animationSpec = tween(
            durationMillis = animationDurationMilli,
            delayMillis = animationDelay,
            easing = LinearEasing
        ),
        finishedListener = onFinish
    )

    val progressPercent = animateValue / timeSeconds

    LaunchedEffect(timeSeconds) {
        hasAnimationPlayed = true
    }

    Box(
        modifier = modifier
            .size(radius * 2f),
        contentAlignment = Alignment.Center,
    ) {
        Canvas(modifier = Modifier.size(radius * 2f - strokeWidth)){
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
                360 * progressPercent,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }
        Text(
            text = animateValue.toInt().toString(),
            style = textStyle,
            color = textColor,
        )
    }
}

@Preview
@Composable
fun CircularCounterPreview(){
    AppTheme(
        isDarkTheme = true
    ) {
        Box(
            modifier = Modifier
                .background(Theme.color.surfaces.surface)
                .padding(16.dp)
        ) {
            CircularTimer(
                timeSeconds = 15,
                onFinish = { },
                reverse = true
            )
        }
    }
}
