package com.cairosquad.evolvefit.ui.screen.workoutDetails

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.cairosquad.evolvefit.ui.screen.workoutDetails.content.WorkoutDetailsContent
import com.cairosquad.evolvefit.ui.util.ObserveAsEffect
import com.cairosquad.evolvefit.viewmodel.workout_details.WorkoutDetailsEffect
import com.cairosquad.evolvefit.viewmodel.workout_details.WorkoutDetailsViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun WorkoutDetailsScreen(
    workoutId: String,
    navigateBack: () -> Unit,
    navigateToShareWithCommunity: (workoutId: String) -> Unit,
    navigateToPlayWorkout: () -> Unit,
    viewModel: WorkoutDetailsViewModel = koinViewModel {
        parametersOf(workoutId)
    }
) {
    val state by viewModel.screenState.collectAsState()
    ObserveAsEffect(viewModel.effect) { effect ->
        when (effect) {
            is WorkoutDetailsEffect.NavigateToPlayWorkout -> navigateToPlayWorkout()
            is WorkoutDetailsEffect.NavigateBack -> navigateBack()
            is WorkoutDetailsEffect.NavigateToShareWithCommunity -> {
                navigateToShareWithCommunity(effect.workoutId)
            }
        }
    }
    WorkoutDetailsContent(state = state, listener = viewModel)
}