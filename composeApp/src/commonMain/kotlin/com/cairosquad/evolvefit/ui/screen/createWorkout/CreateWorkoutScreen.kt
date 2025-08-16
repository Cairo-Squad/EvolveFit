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
    navigateToCreateExercise: () -> Unit,
    navigateToWorkOuts: () -> Unit,
    navigateToAllExercises : () -> Unit
) {
    val state by viewModel.screenState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                CreateWorkOutEffect.NavigateBack -> navigateBack()
                CreateWorkOutEffect.NavigateToCreateExercise -> navigateToCreateExercise()
                CreateWorkOutEffect.NavigateToAllExercises -> navigateToAllExercises()
                CreateWorkOutEffect.NavigateToWorkouts -> navigateToWorkOuts()
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