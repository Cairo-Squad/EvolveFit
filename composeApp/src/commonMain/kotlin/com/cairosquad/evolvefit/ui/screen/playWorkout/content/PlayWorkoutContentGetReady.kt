package com.cairosquad.evolvefit.ui.screen.playWorkout.content

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.CircularTimer
import com.cairosquad.evolvefit.design_system.component.PrimaryButton
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.screen.playWorkout.component.ColumnWithBackgroundImage
import com.cairosquad.evolvefit.ui.screen.playWorkout.component.ExerciseNameAndInfoIcon
import com.cairosquad.evolvefit.viewmodel.playWorkout.PlayWorkoutInteractionListener
import com.cairosquad.evolvefit.viewmodel.playWorkout.PlayWorkoutScreenState
import com.cairosquad.evolvefit.viewmodel.playWorkout.PlayWorkoutViewModel
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.cancel_workout
import evolvefit.composeapp.generated.resources.exercise
import evolvefit.composeapp.generated.resources.ic_cross
import evolvefit.composeapp.generated.resources.ready_to_workout
import evolvefit.composeapp.generated.resources.start
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun PlayWorkoutContentGetReady(
    screenState: PlayWorkoutScreenState,
    listener: PlayWorkoutInteractionListener
) {
    val firstExercise = screenState.workout.exercises
        .getOrNull(0)
        ?: PlayWorkoutScreenState.ExerciseUiState()

    val upperSpaceWeight = 0.4f
    val lowerSpaceWeight = 1 - upperSpaceWeight

    ColumnWithBackgroundImage(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        model = screenState.workout.imageUrl,
        contentDescription = screenState.workout.name,
        contentPadding = WindowInsets.systemBars.asPaddingValues(),
        backgroundBlurRadius = 32.dp
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
            contentDescription = stringResource(Res.string.cancel_workout),
            tint = Theme.color.surfaces.textColor,
        )
        Spacer(Modifier.weight(upperSpaceWeight))
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 40.dp),
            text = stringResource(Res.string.ready_to_workout),
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
                stringResource(Res.string.exercise) +
                screenState.workout.exercises
                .takeIf { it.isNotEmpty() }
                .let { if (it == null) "" else " 1/${it.size}" },
            style = Theme.textStyle.label.smallRegular14,
            color = Theme.color.surfaces.outline,
        )
        ExerciseNameAndInfoIcon(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            exerciseName = firstExercise.name,
            onClickInfo = { listener.onClickExerciseInfo(firstExercise.id) }
        )
        Spacer(Modifier.weight(lowerSpaceWeight))
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


@Preview
@Composable
private fun PlayWorkoutContentGetReadyPreview() {
    AppTheme(
        isDarkTheme = true
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Theme.color.surfaces.surface)
        ) {
            PlayWorkoutContentGetReady(
                screenState = PlayWorkoutScreenState(
                    workout = PlayWorkoutScreenState.WorkoutUiState(
                        imageUrl = "https://phxgymwitham.co.uk/wp-content/uploads/2024/05/Upper-body-gym-workout-1024x681.jpg"
                    )
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