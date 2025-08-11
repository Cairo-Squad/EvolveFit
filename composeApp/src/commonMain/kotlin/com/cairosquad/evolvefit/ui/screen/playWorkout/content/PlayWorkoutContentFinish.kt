package com.cairosquad.evolvefit.ui.screen.playWorkout.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.design_system.util.NetworkImage
import com.cairosquad.evolvefit.viewmodel.playWorkout.PlayWorkoutInteractionListener
import com.cairosquad.evolvefit.viewmodel.playWorkout.PlayWorkoutScreenState
import com.cairosquad.evolvefit.viewmodel.playWorkout.PlayWorkoutScreenState.WorkoutLevelUiState
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.im_default_workout
import evolvefit.composeapp.generated.resources.im_fireworks
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
fun PlayWorkoutContentFinish(
    screenState: PlayWorkoutScreenState,
    listener: PlayWorkoutInteractionListener
) {
    ColumnWithBackgroundAndFireworks(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        model = screenState.workout.imageUrl,
        contentDescription = screenState.workout.name,
        contentPadding = WindowInsets.systemBars.asPaddingValues(),
        backgroundBlurRadius = 32.dp
    ) {
        Spacer(Modifier.weight(0.569f))
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 16.dp),
            text = "Congratulations!",
            style = Theme.textStyle.display.largeBold24,
            color = Theme.color.surfaces.textColor,
        )
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 64.dp),
            text = "You have completed the workout",
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
        Spacer(Modifier.weight(0.431f))
        PrimaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            text = "Next to Another Workout",
            onClick = listener::onClickNextToAnotherWorkout
        )
        SecondaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
                .padding(horizontal = 16.dp),
            text = "Finish",
            onClick = listener::onClickFinish
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
            label = "Exercises",
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 12.dp, vertical = 8.dp)
        )
        StatVerticalLine()
        SingleStat(
            value = totalTimeString,
            label = "Minute",
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 12.dp, vertical = 8.dp)
        )
        StatVerticalLine()
        SingleStat(
            value = stringResource(level.nameResId),
            label = "Level",
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
            color = Theme.color.surfaces.onSurfaceContainer,
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
    Box(modifier = modifier){
        NetworkImage(
            modifier = Modifier
                .matchParentSize()
                .blur(backgroundBlurRadius),
            model = model,
            contentDescription = contentDescription,
            defaultImage = painterResource(Res.drawable.im_default_workout)
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
            contentDescription = "Congratulations",
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


@Preview
@Composable
private fun PlayWorkoutContentFinishPreview() {
    AppTheme(
        isDarkTheme = true
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Theme.color.surfaces.surface)
        ) {
            PlayWorkoutContentFinish(
                screenState = PlayWorkoutScreenState(
                    workout = PlayWorkoutScreenState.WorkoutUiState(
                        name = "Upper Body",
                        imageUrl = "https://phxgymwitham.co.uk/wp-content/uploads/2024/05/Upper-body-gym-workout-1024x681.jpg"
                    ),
                    totalTimeMinutes = 25
                ),
                listener = object : PlayWorkoutInteractionListener {
                    override fun onClickCancelWorkout() {
                        TODO("Not yet implemented")
                    }

                    override fun onGetReadyCounterFinish() {
                        TODO("Not yet implemented")
                    }

                    override fun onClickStart() {
                        TODO("Not yet implemented")
                    }

                    override fun onClickExerciseInfo(id: String) {
                        TODO("Not yet implemented")
                    }

                    override fun onClickRestFinish() {
                        TODO("Not yet implemented")
                    }

                    override fun onFinishExercise() {
                        TODO("Not yet implemented")
                    }

                    override fun onClickForward() {
                        TODO("Not yet implemented")
                    }

                    override fun onClickBack() {
                        TODO("Not yet implemented")
                    }

                    override fun onClickSkipRest() {
                        TODO("Not yet implemented")
                    }

                    override fun onClickNextToAnotherWorkout() {
                        TODO("Not yet implemented")
                    }

                    override fun onClickFinish() {
                        TODO("Not yet implemented")
                    }

                    override fun onClinkStayInWorkout() {
                        TODO("Not yet implemented")
                    }

                    override fun onClinkEnd() {
                        TODO("Not yet implemented")
                    }

                    override fun onDismissExerciseInfo() {
                        TODO("Not yet implemented")
                    }
                },
            )
        }
    }
}

