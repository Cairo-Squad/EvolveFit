package com.cairosquad.evolvefit.ui.screen.playWorkout.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.PrimaryButton
import com.cairosquad.evolvefit.design_system.component.SecondaryButton
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.design_system.util.NetworkImage
import com.cairosquad.evolvefit.viewmodel.play_workout.PlayWorkoutInteractionListener
import com.cairosquad.evolvefit.viewmodel.play_workout.PlayWorkoutScreenState
import com.cairosquad.evolvefit.viewmodel.play_workout.PlayWorkoutScreenState.WorkoutLevelUiState
import evolvefit.composeapp.generated.resources.Onboarding
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.congratulations_message
import evolvefit.composeapp.generated.resources.exercises
import evolvefit.composeapp.generated.resources.finish
import evolvefit.composeapp.generated.resources.im_default_workout
import evolvefit.composeapp.generated.resources.im_fireworks
import evolvefit.composeapp.generated.resources.level_label
import evolvefit.composeapp.generated.resources.minute_label
import evolvefit.composeapp.generated.resources.next_workout_button
import evolvefit.composeapp.generated.resources.workout_completed_message
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource


@Composable
fun PlayWorkoutContentFinish(
    screenState: PlayWorkoutScreenState,
    listener: PlayWorkoutInteractionListener
) {
    val upperSpaceWeight = 0.569f
    val lowerSpaceWeight = 1 - upperSpaceWeight

    ColumnWithBackgroundAndFireworks(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        model = screenState.workout.imageUrl,
        contentDescription = screenState.workout.name,
        contentPadding = WindowInsets.systemBars.asPaddingValues(),
        backgroundBlurRadius = 32.dp
    ) {
        Spacer(Modifier.weight(upperSpaceWeight))
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 16.dp),
            text = stringResource(Res.string.congratulations_message) + "!",
            style = Theme.textStyle.display.largeBold24,
            color = Theme.color.surfaces.textColor,
        )
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 64.dp),
            text = stringResource(Res.string.workout_completed_message),
            style = Theme.textStyle.body.smallRegular16,
            color = Theme.color.surfaces.outline,
        )
        Text(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp),
            text = screenState.workout.name,
            style = Theme.textStyle.display.largeBold20,
            color = Theme.color.surfaces.textColor,
        )
        WorkoutStatistics(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            totalExercises = screenState.workout.exercises.size,
            totalTimeMinutes = screenState.totalTimeMinutes,
            level = screenState.workout.level,
        )
        Spacer(Modifier.weight(lowerSpaceWeight))
        PrimaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            text = stringResource(Res.string.next_workout_button),
            onClick = listener::onNextToAnotherWorkoutClicked
        )
        SecondaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
                .padding(horizontal = 16.dp),
            text = stringResource(Res.string.finish),
            onClick = listener::onFinishClicked
        )
    }
}

@Composable
private fun WorkoutStatistics(
    totalExercises: Int,
    totalTimeMinutes: Int,
    level: WorkoutLevelUiState,
    modifier: Modifier = Modifier
) {
    val hours = minOf(totalTimeMinutes / 60, 99)
    val hoursText = hours.toString().padStart(2, '0')
    val minutes = totalTimeMinutes % 60
    val minutesText = minutes.toString().padStart(2, '0')
    val totalTimeString = "$hoursText:$minutesText"

    Row(
        modifier = modifier,
    ) {
        SingleStat(
            value = totalExercises.toString(),
            label = stringResource(Res.string.exercises),
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 12.dp, vertical = 8.dp)
        )
        StatVerticalLine()
        SingleStat(
            value = totalTimeString,
            label = stringResource(Res.string.minute_label),
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 12.dp, vertical = 8.dp)
        )
        StatVerticalLine()
        SingleStat(
            value = stringResource(level.nameResId),
            label = stringResource(Res.string.level_label),
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 12.dp, vertical = 8.dp)
        )
    }
}

@Composable
private fun StatVerticalLine(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(1.dp)
            .height(65.dp)
            .border(
                width = 1.dp,
                color = Theme.color.surfaces.outlineVariant
            )
    )
}

@Composable
private fun SingleStat(
    value: String,
    label: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            modifier = Modifier,
            text = value,
            style = Theme.textStyle.display.largeBold20,
            color = Theme.color.surfaces.textColor
        )
        Text(
            modifier = Modifier,
            text = label,
            style = Theme.textStyle.label.smallRegular14,
            color = Theme.color.surfaces.onSurfaceVariant,
        )
    }
}


@Composable
private fun ColumnWithBackgroundAndFireworks(
    model: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    contentPadding: PaddingValues = PaddingValues(),
    backgroundBlurRadius: Dp = 32.dp,
    content: @Composable ColumnScope.() -> Unit
) {
    Box(modifier = modifier) {
        NetworkImage(
            modifier = Modifier
                .matchParentSize()
                .blur(backgroundBlurRadius),
            model = model,
            contentDescription = contentDescription,
            defaultImage = painterResource(Res.drawable.Onboarding),
            loadingPlaceHolder = painterResource(Res.drawable.Onboarding)
        )
        Image(
            modifier = Modifier
                .padding(top = 72.dp)
                .padding(horizontal = 12.dp)
                .align(Alignment.TopCenter)
                .widthIn(max = 360.dp)
                .fillMaxWidth(),
            alignment = Alignment.TopCenter,
            painter = painterResource(Res.drawable.im_fireworks),
            contentDescription = stringResource(Res.string.congratulations_message),
        )
        Column(
            modifier = Modifier
                .matchParentSize()
                .padding(contentPadding),
            verticalArrangement = verticalArrangement,
            horizontalAlignment = horizontalAlignment,
            content = content
        )
    }
}