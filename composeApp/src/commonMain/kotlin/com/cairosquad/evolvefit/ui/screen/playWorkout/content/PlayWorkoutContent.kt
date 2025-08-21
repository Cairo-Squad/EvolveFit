package com.cairosquad.evolvefit.ui.screen.playWorkout.content

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.BottomSheet
import com.cairosquad.evolvefit.design_system.component.PrimaryButton
import com.cairosquad.evolvefit.design_system.component.SecondaryButton
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.util.noRippleClickable
import com.cairosquad.evolvefit.viewmodel.playWorkout.PlayWorkoutInteractionListener
import com.cairosquad.evolvefit.viewmodel.playWorkout.PlayWorkoutScreenState
import com.cairosquad.evolvefit.viewmodel.playWorkout.PlayWorkoutScreenState.Stage.FINISH
import com.cairosquad.evolvefit.viewmodel.playWorkout.PlayWorkoutScreenState.Stage.GET_READY
import com.cairosquad.evolvefit.viewmodel.playWorkout.PlayWorkoutScreenState.Stage.PERFORM
import com.cairosquad.evolvefit.viewmodel.playWorkout.PlayWorkoutScreenState.Stage.REST
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.discard_progress_warning
import evolvefit.composeapp.generated.resources.end_workout_confirm_button
import evolvefit.composeapp.generated.resources.end_workout_prompt
import evolvefit.composeapp.generated.resources.stay_in_workout_button
import org.jetbrains.compose.resources.stringResource


@Composable
fun PlayWorkoutContent(
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
                onClinkStayInWorkout = listener::onStayInWorkoutClinked,
                onClinkEnd = listener::onEndClinked,
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
private fun EndWorkoutConfirmation(
    onClinkStayInWorkout: () -> Unit,
    onClinkEnd: () -> Unit,
    modifier: Modifier = Modifier
) {
    val upperSpaceWeight = 0.606f
    val lowerSpaceWeight = 1 - upperSpaceWeight

    Column(
        modifier = modifier
            .fillMaxSize()
            .noRippleClickable(onClick = {}),
    ) {
        Spacer(Modifier.weight(upperSpaceWeight))
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp),
            text = stringResource(Res.string.end_workout_prompt),
            style = Theme.textStyle.display.largeBold24,
            color = Theme.color.surfaces.textColor,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 16.dp)
                .padding(bottom = 64.dp),
            text = stringResource(Res.string.discard_progress_warning),
            style = Theme.textStyle.body.smallRegular16,
            color = Theme.color.surfaces.outline,
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.weight(lowerSpaceWeight))
        PrimaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            text = stringResource(Res.string.stay_in_workout_button),
            onClick = onClinkStayInWorkout
        )
        SecondaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
                .padding(horizontal = 16.dp),
            text = stringResource(Res.string.end_workout_confirm_button),
            onClick = onClinkEnd
        )
    }
}