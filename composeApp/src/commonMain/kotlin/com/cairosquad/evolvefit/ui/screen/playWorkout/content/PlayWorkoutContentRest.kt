package com.cairosquad.evolvefit.ui.screen.playWorkout.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.design_system.util.NetworkImage
import com.cairosquad.evolvefit.ui.screen.playWorkout.component.ColumnWithBackground
import com.cairosquad.evolvefit.ui.screen.playWorkout.component.ExerciseNameAndInfoIcon
import com.cairosquad.evolvefit.ui.screen.playWorkout.component.RestTimer
import com.cairosquad.evolvefit.viewmodel.playWorkout.PlayWorkoutInteractionListener
import com.cairosquad.evolvefit.viewmodel.playWorkout.PlayWorkoutScreenState
import com.cairosquad.evolvefit.viewmodel.playWorkout.PlayWorkoutViewModel
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.im_default_workout
import evolvefit.composeapp.generated.resources.next_movement
import evolvefit.composeapp.generated.resources.take_a_rest
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun PlayWorkoutContentRest(
    screenState: PlayWorkoutScreenState,
    listener: PlayWorkoutInteractionListener
) {

    LaunchedEffect(Unit){
        listener.onClickForward()
    }

    val currentExercise = screenState.workout.exercises
        .getOrNull(screenState.currentStep - 1)
        ?: PlayWorkoutScreenState.ExerciseUiState()

    ColumnWithBackground(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        model = screenState.workout.imageUrl,
        contentDescription = screenState.workout.name,
        contentPadding = WindowInsets.systemBars.asPaddingValues(),
        backgroundBlurRadius = 32.dp
    ) {
        Spacer(Modifier.weight(0.546f))
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 16.dp),
            text = stringResource(Res.string.take_a_rest),
            style = Theme.textStyle.display.largeBold20,
            color = Theme.color.surfaces.textColor,
        )
        RestTimer(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 32.dp),
            timeSeconds = PlayWorkoutViewModel.REST_TIMER_SECONDS,
            onClickSkipRest = listener::onClickSkipRest,
            onFinish = listener::onClickRestFinish,
            timeIncrement = PlayWorkoutViewModel.REST_TIMER_TIME_INCREMENT
        )
        Spacer(Modifier.weight(0.454f))
        Text(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 8.dp),
            text = stringResource(Res.string.next_movement) + " (${screenState.currentStep + 1}/${screenState.workout.exercises.size})",
            style = Theme.textStyle.label.smallRegular14,
            color = Theme.color.surfaces.textColor,
        )
        ExerciseNameAndInfoIcon(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp),
            exerciseName = currentExercise.name,
            onClickInfo = { listener.onClickExerciseInfo(currentExercise.id) },
            textStyle = Theme.textStyle.headline.mediumMedium18,
            textColor = Theme.color.surfaces.textColor
        )
        NetworkImage(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 16.dp)
                .padding(bottom = 24.dp)
                .aspectRatio(328f/360f)
                .fillMaxHeight()
                .clip(RoundedCornerShape(8.dp)),
            model = currentExercise.imageUrls.firstOrNull() ?: "",
            contentDescription = currentExercise.name,
            defaultImage = painterResource(Res.drawable.im_default_workout)
        )
    }
}


@Preview
@Composable
private fun PlayWorkoutContentRestPreview() {
    AppTheme(
        isDarkTheme = true
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Theme.color.surfaces.surface)
        ) {
            PlayWorkoutContentRest(
                screenState = PlayWorkoutScreenState(
                    workout = PlayWorkoutScreenState.WorkoutUiState(
                        imageUrl = "https://phxgymwitham.co.uk/wp-content/uploads/2024/05/Upper-body-gym-workout-1024x681.jpg",
                        exercises = listOf(
                            PlayWorkoutScreenState.ExerciseUiState(
                                name = "Running",
                                exerciseSpec = PlayWorkoutScreenState.ExerciseSpecUiState.Time(30),
                                imageUrls = listOf("https://phxgymwitham.co.uk/wp-content/uploads/2024/05/Upper-body-gym-workout-1024x681.jpg")
                            )
                        )
                    ),
                    currentStep = 1
                ),
                listener = object : PlayWorkoutInteractionListener {
                    override fun onClickCancelWorkout() {
                        // TODO("Not yet implemented")
                    }

                    override fun onGetReadyCounterFinish() {
                        // TODO("Not yet implemented")
                    }

                    override fun onClickStart() {
                        // TODO("Not yet implemented")
                    }

                    override fun onClickExerciseInfo(id: String) {
                        // TODO("Not yet implemented")
                    }

                    override fun onClickRestFinish() {
                        // TODO("Not yet implemented")
                    }

                    override fun onFinishExercise() {
                        // TODO("Not yet implemented")
                    }

                    override fun onClickForward() {
                        // TODO("Not yet implemented")
                    }

                    override fun onClickBack() {
                        // TODO("Not yet implemented")
                    }

                    override fun onClickSkipRest() {
                        // TODO("Not yet implemented")
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