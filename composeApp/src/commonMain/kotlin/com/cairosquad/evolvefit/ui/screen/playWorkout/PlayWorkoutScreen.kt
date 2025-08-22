package com.cairosquad.evolvefit.ui.screen.playWorkout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.cairosquad.evolvefit.ui.screen.playWorkout.content.PlayWorkoutContent
import com.cairosquad.evolvefit.ui.util.ObserveAsEffect
import com.cairosquad.evolvefit.ui.util.PlatformBackHandler
import com.cairosquad.evolvefit.viewmodel.play_workout.PlayWorkoutEffect
import com.cairosquad.evolvefit.viewmodel.play_workout.PlayWorkoutViewModel
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
        onBack = viewModel::onCancelWorkoutClicked
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