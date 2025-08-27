package com.cairosquad.evolvefit.ui.screen.workoutDetails

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.design_system.theme.UpdateStatusBarIconsForTheme
import com.cairosquad.evolvefit.ui.screen.workoutDetails.content.WorkoutDetailsContent
import com.cairosquad.evolvefit.ui.util.ObserveAsEffect
import com.cairosquad.evolvefit.ui.util.PlatformBackHandler
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

    val appInDarkMode = Theme.isDark
    var isStatusBarIconsLight by remember { mutableStateOf(true) }
    val resetStatusBarIcons = { isStatusBarIconsLight = appInDarkMode }
    UpdateStatusBarIconsForTheme(isStatusBarIconsLight)
    PlatformBackHandler {
        resetStatusBarIcons()
        navigateBack()
    }

    ObserveAsEffect(viewModel.effect) { effect ->
        when (effect) {
            is WorkoutDetailsEffect.NavigateToPlayWorkout -> {
                resetStatusBarIcons()
                navigateToPlayWorkout()
            }
            is WorkoutDetailsEffect.NavigateBack -> {
                resetStatusBarIcons()
                navigateBack()
            }
            is WorkoutDetailsEffect.NavigateToShareWithCommunity -> {
                resetStatusBarIcons()
                navigateToShareWithCommunity(effect.workoutId)
            }
        }
    }
    WorkoutDetailsContent(state = state, listener = viewModel)
}