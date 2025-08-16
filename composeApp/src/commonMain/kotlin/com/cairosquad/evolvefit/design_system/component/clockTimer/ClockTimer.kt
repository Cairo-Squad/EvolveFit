package com.cairosquad.evolvefit.design_system.component.clockTimer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cairosquad.evolvefit.design_system.component.PrimaryButton
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
fun ClockTimer(
    totalTime: Int,
    modifier: Modifier = Modifier,
    onFinish: (() -> Unit)? = null,
    textColor: Color = Theme.color.surfaces.onSurfaceContainer,
    textStyle: TextStyle = Theme.textStyle.title.largeBold16.copy(fontSize = 48.sp),
) {
    ClockTimer(
        clockTimerState = rememberClockTimerState(
            totalTime = totalTime,
            onFinish = onFinish
        ),
        modifier = modifier,
        textColor = textColor,
        textStyle = textStyle
    )
}

@Composable
fun ClockTimer(
    clockTimerState: ClockTimerState,
    modifier: Modifier = Modifier,
    textColor: Color = Theme.color.surfaces.onSurfaceContainer,
    textStyle: TextStyle = Theme.textStyle.title.largeBold16.copy(fontSize = 48.sp),
) {

    val time by clockTimerState.currentTimeInSeconds.collectAsState(initial = clockTimerState.totalTimeInSeconds)

    val minutes = minOf(time?.div(60) ?: 0, 99)
    val seconds = time?.rem(60) ?: 0

    val minutesText = minutes.toString().padStart(2, '0')
    val secondsText = seconds.toString().padStart(2, '0')

    Text(
        modifier = modifier,
        text = "$minutesText:$secondsText",
        color = textColor,
        style = textStyle
    )
}


@Preview
@Composable
private fun ClockTimerPreview() {

    val clockTimerState = rememberClockTimerState(
        totalTime = 30,
        onFinish = {}
    )

    AppTheme(
        isDarkTheme = true
    ) {
        Box(
            modifier = Modifier
                .background(Theme.color.surfaces.surface)
                .padding(16.dp),
        ) {
            ClockTimer(
                clockTimerState = clockTimerState
            )
        }
    }
}

@Preview
@Composable
private fun ClockTimerWithButtonsPreview() {

    var isTimer1Completed by remember { mutableStateOf(false) }
    var isTimer2Completed by remember { mutableStateOf(false) }

    val clockTimerState1 = rememberClockTimerState(
        totalTime = 30,
        onFinish = { isTimer1Completed = true }
    )

    val clockTimerState2 = rememberClockTimerState(
        totalTime = 90,
        onFinish = { isTimer2Completed = true }
    )

    AppTheme(
        isDarkTheme = true
    ) {
        Column(
            modifier = Modifier
                .background(Theme.color.surfaces.surface)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text ="functionality test",
                style = Theme.textStyle.title.largeBold16,
                color = Theme.color.surfaces.onSurface
            )
            Text(
                text = "timer 1 " + (if (isTimer1Completed) "have" else "haven't") + " completed",
                style = Theme.textStyle.title.largeBold16,
                color = Theme.color.surfaces.onSurface
            )
            ClockTimer(
                clockTimerState = clockTimerState1
            )
            Text(
                "timer 2 " + (if (isTimer2Completed) "have" else "haven't") + " completed",
                style = Theme.textStyle.title.largeBold16,
                color = Theme.color.surfaces.onSurface
            )
            ClockTimer(
                clockTimerState = clockTimerState2
            )
            PrimaryButton(
                "Pause/Play",
                onClick = {
                    clockTimerState1.triggerPause()
                    clockTimerState2.triggerPause()
                }
            )
            PrimaryButton(
                "+15",
                onClick = {
                    clockTimerState1.addSeconds(15)
                    clockTimerState2.addSeconds(15)
                }
            )
            PrimaryButton(
                "-15",
                onClick = {
                    clockTimerState1.addSeconds(-15)
                    clockTimerState2.addSeconds(-15)
                }
            )
        }
    }
}