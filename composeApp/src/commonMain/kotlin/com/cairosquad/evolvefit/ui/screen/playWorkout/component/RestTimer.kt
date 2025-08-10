package com.cairosquad.evolvefit.ui.screen.playWorkout.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cairosquad.evolvefit.design_system.component.PrimaryButton
import com.cairosquad.evolvefit.design_system.component.clockTimer.ClockTimer
import com.cairosquad.evolvefit.design_system.component.clockTimer.rememberClockTimerState
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.im_default_workout
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun RestTimer(
    initialTimeSeconds: Int,
    onFinish: () -> Unit,
    onClickSkipRest: () -> Unit,
    modifier: Modifier = Modifier,
    timeIncrement: Int = 15,
    textColor: Color = Theme.color.surfaces.onSurfaceContainer,
    textStyle: TextStyle = Theme.textStyle.title.largeBold16.copy(fontSize = 48.sp),
) {

    val clockTimerState = rememberClockTimerState(
        totalTime = initialTimeSeconds,
        onFinish = onFinish
    )

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        ClockTimer(
            modifier = Modifier
                .padding(bottom = 40.dp),
            clockTimerState = clockTimerState,
            textColor = textColor,
            textStyle = textStyle,
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            PrimaryButton(
                modifier = Modifier.weight(1f),
                text = "+${timeIncrement} Sec",
                onClick = { clockTimerState.addSeconds(timeIncrement) },
                enabledContainerColor = Theme.color.surfaces.onSurfaceAt3,
                enabledTextColor = Theme.color.surfaces.textColor
            )
            PrimaryButton(
                modifier = Modifier.weight(1f),
                text = "Skip Rest",
                onClick = onClickSkipRest
            )
        }
    }
}

@Preview
@Composable
private fun RestTimerPreview() {
    AppTheme(
        isDarkTheme = true
    ) {
        RestTimer(
            modifier = Modifier
                .padding(16.dp),
            initialTimeSeconds = 30,
            onFinish = {},
            onClickSkipRest = {}
        )
    }
}

@Preview
@Composable
private fun RestTimerWithBackGroundPreview() {
    AppTheme(
        isDarkTheme = true
    ) {
        Box{
            Image(
                modifier = Modifier
                    .matchParentSize()
                    .blur(32.dp),
                painter = painterResource(Res.drawable.im_default_workout),
                contentDescription = "default workout image"
            )
            RestTimer(
                modifier = Modifier
                    .padding(16.dp),
                initialTimeSeconds = 30,
                onFinish = {},
                onClickSkipRest = {}
            )
        }
    }
}