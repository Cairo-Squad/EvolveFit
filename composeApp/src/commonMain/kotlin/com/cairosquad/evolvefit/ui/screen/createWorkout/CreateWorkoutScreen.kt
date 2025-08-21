package com.cairosquad.evolvefit.ui.screen.createWorkout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.cairosquad.evolvefit.ui.screen.createWorkout.content.AllExercisesContent
import com.cairosquad.evolvefit.ui.screen.createWorkout.content.CreateWorkoutContent
import com.cairosquad.evolvefit.viewmodel.createWorkOut.CreateWorkOutEffect
import com.cairosquad.evolvefit.viewmodel.createWorkOut.CreateWorkOutScreenState.CreateWorkoutStep
import com.cairosquad.evolvefit.viewmodel.createWorkOut.CreateWorkoutViewModel
import kotlinx.coroutines.flow.collectLatest
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
    when (state.currentStep) {
        CreateWorkoutStep.DETAILS -> {
            CreateWorkoutContent(
                state = state,
                listener = viewModel,
            )
        }

        CreateWorkoutStep.EXERCISES -> {
            AllExercisesContent(
                state = state,
                listener = viewModel
            )
        }
    }
}