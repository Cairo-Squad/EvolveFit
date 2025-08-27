package com.cairosquad.evolvefit.ui.screen.createWorkout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.cairosquad.evolvefit.ui.screen.createExercise.content.component.ExitCreationBottomSheet
import com.cairosquad.evolvefit.ui.screen.createWorkout.content.AllExercisesContent
import com.cairosquad.evolvefit.ui.screen.createWorkout.content.CreateWorkoutDetailsContent
import com.cairosquad.evolvefit.ui.util.PlatformBackHandler
import com.cairosquad.evolvefit.viewmodel.create_workout.CreateWorkOutEffect
import com.cairosquad.evolvefit.viewmodel.create_workout.CreateWorkOutInteractionListener
import com.cairosquad.evolvefit.viewmodel.create_workout.CreateWorkOutScreenState
import com.cairosquad.evolvefit.viewmodel.create_workout.CreateWorkOutScreenState.CreateWorkoutStep
import com.cairosquad.evolvefit.viewmodel.create_workout.CreateWorkoutViewModel
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.exit_create_workout_confirmation_message
import kotlinx.coroutines.flow.collectLatest
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun CreateWorkoutScreen(
    viewModel: CreateWorkoutViewModel = koinViewModel(),
    navigateBack: () -> Unit,
    navigateToCreateExercise: (onExerciseCreationSuccess: (() -> Unit)?) -> Unit,
    navigateToWorkOuts: () -> Unit,
    navigateToAllExercises : () -> Unit
) {
    val state by viewModel.screenState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                CreateWorkOutEffect.NavigateToCreateExercise -> navigateToCreateExercise(viewModel::loadExercises)
                CreateWorkOutEffect.NavigateToAllExercises -> navigateToAllExercises()
                CreateWorkOutEffect.NavigateToWorkouts -> navigateToWorkOuts()
                CreateWorkOutEffect.NavigateBack -> navigateBack()
            }
        }
    }
    CreateWorkoutContent(
        state = state,
        listener = viewModel
    )
}

@Composable
private fun CreateWorkoutContent(
    state: CreateWorkOutScreenState,
    listener: CreateWorkOutInteractionListener
) {

    PlatformBackHandler(onBack = listener::onExitClicked)

    when (state.currentStep) {
        CreateWorkoutStep.DETAILS -> {
            CreateWorkoutDetailsContent(
                state = state,
                listener = listener,
            )
        }

        CreateWorkoutStep.EXERCISES -> {
            AllExercisesContent(
                state = state,
                listener = listener
            )
        }
    }

    ExitCreationBottomSheet(
        isVisible = state.showExitBottomSheet,
        onDismiss = listener::onCancelExitClicked,
        onCancelClicked = listener::onCancelExitClicked,
        onExitWithoutSavingClicked = listener::onExitConfirmClicked,
        message = stringResource(Res.string.exit_create_workout_confirmation_message)
    )
}