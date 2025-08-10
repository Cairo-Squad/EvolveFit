package com.cairosquad.evolvefit.ui.screen.playWorkout.content

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.CircularTimer
import com.cairosquad.evolvefit.design_system.component.PrimaryButton
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.design_system.util.NetworkImage
import com.cairosquad.evolvefit.viewmodel.playWorkout.PlayWorkoutInteractionListener
import com.cairosquad.evolvefit.viewmodel.playWorkout.PlayWorkoutScreenState
import com.cairosquad.evolvefit.viewmodel.playWorkout.PlayWorkoutViewModel
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.exercises
import evolvefit.composeapp.generated.resources.ic_cross
import evolvefit.composeapp.generated.resources.ic_question_mark
import evolvefit.composeapp.generated.resources.im_default_workout
import evolvefit.composeapp.generated.resources.start
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun PlayWorkoutContentGetReady(
    screenState: PlayWorkoutScreenState,
    listener: PlayWorkoutInteractionListener
) {
    ColumnWithBlurredBackground(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        model = screenState.workout.imageUrl,
        contentDescription = screenState.workout.name,
        contentPadding = WindowInsets.systemBars.asPaddingValues()
    ) {
        Icon(
            modifier = Modifier
                .padding(start = 16.dp)
                .size(48.dp)
                .clip(CircleShape)
                .clickable(
                    onClick = listener::onClickCancelWorkout
                )
                .padding(12.dp),
            painter = painterResource(Res.drawable.ic_cross),
            contentDescription = "Cancel workout",
            tint = Theme.color.surfaces.textColor,
        )
        Spacer(Modifier.weight(0.4f))
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 40.dp),
            text = "Ready To Workout!",
            style = Theme.textStyle.display.largeBold24,
            color = Theme.color.surfaces.onSurfaceContainer,
        )
        CircularTimer(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 32.dp),
            timeSeconds = PlayWorkoutViewModel.GET_READY_COUNTER_SECONDS,
            onFinish = { listener.onGetReadyCounterFinish() },
            reverse = true
        )
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 8.dp),
            text =
                stringResource(Res.string.exercises) +
                screenState.workout.exercises
                .takeIf { it.isNotEmpty() }
                .let { if (it == null) "" else " 1/${it.size}" },
            style = Theme.textStyle.label.smallRegular14,
            color = Theme.color.surfaces.outline,
        )
        ExerciseNameAndInfoIcon(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            exerciseName = screenState.workout.exercises
                .firstOrNull()?.name
                ?: stringResource(Res.string.exercises),
            onClickInfo = listener::onClickExerciseInfo
        )
        Spacer(Modifier.weight(0.6f))
        PrimaryButton(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 16.dp)
                .padding(bottom = 24.dp),
            text = stringResource(Res.string.start),
            onClick = listener::onClickStart
        )
    }
}

@Composable
private fun ExerciseNameAndInfoIcon(
    exerciseName: String,
    onClickInfo: () -> Unit,
    modifier: Modifier = Modifier,
){
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .padding(end = 8.dp),
            text = exerciseName,
            style = Theme.textStyle.display.mediumMedium20,
            color = Theme.color.surfaces.textColor,
        )
        Icon(
            modifier = Modifier
                .size(24.dp)
                .clip(CircleShape)
                .clickable(
                    onClick = onClickInfo
                ),
            painter = painterResource(Res.drawable.ic_question_mark),
            contentDescription = "Exercise Info",
            tint = Theme.color.surfaces.onSurfaceVariant,
        )
    }
}

@Composable
private fun ColumnWithBlurredBackground(
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
fun PlayWorkoutContentGetReadyPreview() {
    AppTheme(
        isDarkTheme = true
    ){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Theme.color.surfaces.surface)
        ) {
            PlayWorkoutContentGetReady(
                screenState = PlayWorkoutScreenState(workout = PlayWorkoutScreenState.WorkoutUiState(imageUrl = "https://phxgymwitham.co.uk/wp-content/uploads/2024/05/Upper-body-gym-workout-1024x681.jpg")),
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

                    override fun onClickExerciseInfo() {
                        TODO("Not yet implemented")
                    }

                },
            )
        }
    }
}