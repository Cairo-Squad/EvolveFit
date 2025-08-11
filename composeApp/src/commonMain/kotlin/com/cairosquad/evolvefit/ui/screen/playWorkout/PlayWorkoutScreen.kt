package com.cairosquad.evolvefit.ui.screen.playWorkout

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.BottomSheet
import com.cairosquad.evolvefit.design_system.component.PrimaryButton
import com.cairosquad.evolvefit.design_system.component.SecondaryButton
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.screen.playWorkout.content.PlayWorkoutContentFinish
import com.cairosquad.evolvefit.ui.screen.playWorkout.content.PlayWorkoutContentGetReady
import com.cairosquad.evolvefit.ui.screen.playWorkout.content.PlayWorkoutContentPerform
import com.cairosquad.evolvefit.ui.screen.playWorkout.content.PlayWorkoutContentRest
import com.cairosquad.evolvefit.ui.util.ObserveAsEffect
import com.cairosquad.evolvefit.ui.util.PlatformBackHandler
import com.cairosquad.evolvefit.viewmodel.playWorkout.PlayWorkoutEffect
import com.cairosquad.evolvefit.viewmodel.playWorkout.PlayWorkoutInteractionListener
import com.cairosquad.evolvefit.viewmodel.playWorkout.PlayWorkoutScreenState
import com.cairosquad.evolvefit.viewmodel.playWorkout.PlayWorkoutScreenState.Stage.FINISH
import com.cairosquad.evolvefit.viewmodel.playWorkout.PlayWorkoutScreenState.Stage.GET_READY
import com.cairosquad.evolvefit.viewmodel.playWorkout.PlayWorkoutScreenState.Stage.PERFORM
import com.cairosquad.evolvefit.viewmodel.playWorkout.PlayWorkoutScreenState.Stage.REST
import com.cairosquad.evolvefit.viewmodel.playWorkout.PlayWorkoutViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun PlayWorkoutScreen(
    workoutId: String,
    navigateBack: () -> Unit,
    navigateBackToApp: () -> Unit,
    viewModel: PlayWorkoutViewModel = koinViewModel { parametersOf(workoutId) },
) {

    val screenState by viewModel.screenState.collectAsState()

    PlatformBackHandler (
        enabled = !screenState.haseCancelWorkoutClicked,
        onBack = viewModel::onClickCancelWorkout
    )

    ObserveAsEffect(viewModel.effect) { effect ->
        when (effect) {
            PlayWorkoutEffect.NavigateBackToApp -> navigateBackToApp()
            PlayWorkoutEffect.NavigateBack -> navigateBack()
        }
    }

    PlayWorkoutContent(
        screenState = screenState,
        listener = viewModel
    )
}

@Composable
private fun PlayWorkoutContent(
    screenState: PlayWorkoutScreenState,
    listener: PlayWorkoutInteractionListener,
) {

    val animatedBlurRadius by animateDpAsState(
        if (screenState.haseCancelWorkoutClicked) 32.dp else 0.dp
    )
    val animatedBlurColor by animateColorAsState(
        targetValue =
            if (screenState.haseCancelWorkoutClicked) Theme.color.surfaces.onSurfaceAt3
            else Theme.color.surfaces.onSurfaceAt3.copy(alpha = 0f)
    )

    Box(
        Modifier.fillMaxSize()
    ) {
        Crossfade(
            modifier = Modifier
                .fillMaxSize()
                .blur(animatedBlurRadius),
            targetState = screenState.stage
        ) { stage ->
            when (stage) {
                GET_READY -> PlayWorkoutContentGetReady(screenState, listener)
                PERFORM -> PlayWorkoutContentPerform(screenState, listener)
                REST -> PlayWorkoutContentRest(screenState, listener)
                FINISH -> PlayWorkoutContentFinish(screenState, listener)
            }
        }
        AnimatedVisibility(
            modifier = Modifier
                .fillMaxSize()
                .background(animatedBlurColor),
            visible = screenState.haseCancelWorkoutClicked
        ) {
            EndWorkoutConfirmation(
                onClinkStayInWorkout = listener::onClinkStayInWorkout,
                onClinkEnd = listener::onClinkEnd,
            )
        }
        ExerciseInfoBottomSheet(
            isVisible = screenState.showExerciseInfo,
            exercise =
                screenState.workout.exercises.getOrNull(screenState.currentStep - 1)
                ?: PlayWorkoutScreenState.ExerciseUiState(),
            onDismiss = listener::onDismissExerciseInfo
        )
    }
}

@Composable
private fun ExerciseInfoBottomSheet(
    isVisible: Boolean,
    exercise: PlayWorkoutScreenState.ExerciseUiState,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    BottomSheet(
        modifier = modifier,
        isVisible = isVisible,
        onDismiss = onDismiss
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 16.dp),
            text = exercise.name,
            style = Theme.textStyle.display.largeBold24,
            color = Theme.color.surfaces.textColor,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 16.dp),
            text = "TODO: Exercise Info",
            style = Theme.textStyle.display.largeBold24,
            color = Theme.color.surfaces.textColor,
            textAlign = TextAlign.Center
        )
    }
}


@Composable
fun EndWorkoutConfirmation(
    onClinkStayInWorkout: () -> Unit,
    onClinkEnd: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .clickable(
                onClick = { },
                indication = null,
                interactionSource = MutableInteractionSource()
            ),
    ) {
        Spacer(Modifier.weight(322f / (322 + 209)))
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp),
            text = "Do you want to end the workout?",
            style = Theme.textStyle.display.largeBold24,
            color = Theme.color.surfaces.textColor,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 16.dp)
                .padding(bottom = 64.dp),
            text = "Exiting now will discard your current progress.",
            style = Theme.textStyle.body.smallRegular16,
            color = Theme.color.surfaces.outline,
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.weight(209f / (322 + 209)))
        PrimaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            text = "Stay in Workout",
            onClick = onClinkStayInWorkout
        )
        SecondaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
                .padding(horizontal = 16.dp),
            text = "End Workout",
            onClick = onClinkEnd
        )
    }
}


private val fakeListener = object : PlayWorkoutInteractionListener {
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
        // TODO("Not yet implemented")
    }

    override fun onClickFinish() {
        // TODO("Not yet implemented")
    }

    override fun onClinkStayInWorkout() {
        // TODO("Not yet implemented")
    }

    override fun onClinkEnd() {
        // TODO("Not yet implemented")
    }

    override fun onDismissExerciseInfo() {
        // TODO("Not yet implemented")
    }

}
private val fakeScreenState = PlayWorkoutScreenState(
    workout = PlayWorkoutScreenState.WorkoutUiState(
        name = "Upper Body",
        imageUrl = "fakeUrl",
        level = PlayWorkoutScreenState.WorkoutLevelUiState.BEGINNER,
        exercises = listOf(
            PlayWorkoutScreenState.ExerciseUiState(
                name = "Push-up",
                exerciseSpec = PlayWorkoutScreenState.ExerciseSpecUiState.Reps(10),
                imageUrls = listOf("fakeUrl")
            ),
            PlayWorkoutScreenState.ExerciseUiState(
                name = "Running",
                exerciseSpec = PlayWorkoutScreenState.ExerciseSpecUiState.Time(30),
                imageUrls = listOf("fakeUrl")
            ),
            PlayWorkoutScreenState.ExerciseUiState(
                name = "Push-up",
                exerciseSpec = PlayWorkoutScreenState.ExerciseSpecUiState.Reps(10),
                imageUrls = listOf("fakeUrl")
            ),
            PlayWorkoutScreenState.ExerciseUiState(
                name = "Running",
                exerciseSpec = PlayWorkoutScreenState.ExerciseSpecUiState.Time(30),
                imageUrls = listOf("fakeUrl")
            ),
            PlayWorkoutScreenState.ExerciseUiState(
                name = "Push-up",
                exerciseSpec = PlayWorkoutScreenState.ExerciseSpecUiState.Reps(10),
                imageUrls = listOf("fakeUrl")
            ),
            PlayWorkoutScreenState.ExerciseUiState(
                name = "Running",
                exerciseSpec = PlayWorkoutScreenState.ExerciseSpecUiState.Time(30),
                imageUrls = listOf("fakeUrl")
            ),
        )
    ),
    currentStep = 1
)

@Composable
private fun PlayWorkoutContentPreview(screenState: PlayWorkoutScreenState) {
    AppTheme(
        isDarkTheme = true
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Theme.color.surfaces.surface)
        ) {
            PlayWorkoutContent(
                screenState = screenState,
                listener = fakeListener
            )
        }
    }
}

@Preview
@Composable
private fun PlayWorkoutContentPreview1() {
    PlayWorkoutContentPreview(screenState = fakeScreenState.copy(stage = GET_READY))
}

@Preview
@Composable
private fun PlayWorkoutContentPreview2() {
    PlayWorkoutContentPreview(screenState = fakeScreenState.copy(stage = PERFORM))
}

@Preview
@Composable
private fun PlayWorkoutContentPreview7() {
    PlayWorkoutContentPreview(
        screenState = fakeScreenState.copy(
            stage = PERFORM,
            currentStep = 4
        )
    )
}

@Preview
@Composable
private fun PlayWorkoutContentPreview3() {
    PlayWorkoutContentPreview(screenState = fakeScreenState.copy(stage = REST))
}

@Preview
@Composable
private fun PlayWorkoutContentPreview4() {
    PlayWorkoutContentPreview(screenState = fakeScreenState.copy(stage = FINISH))
}

@Preview
@Composable
private fun PlayWorkoutContentPreview5() {
    PlayWorkoutContentPreview(
        screenState = fakeScreenState.copy(
            stage = GET_READY,
            haseCancelWorkoutClicked = true
        )
    )
}


@Preview
@Composable
private fun PlayWorkoutContentPreview6() {
    PlayWorkoutContentPreview(
        screenState = fakeScreenState.copy(
            stage = PERFORM,
            haseCancelWorkoutClicked = true
        )
    )
}

