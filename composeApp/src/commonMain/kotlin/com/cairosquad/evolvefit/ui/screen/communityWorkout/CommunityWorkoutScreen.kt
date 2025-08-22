package com.cairosquad.evolvefit.ui.screen.communityWorkout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.ui.screen.communityWorkout.content.WorkoutsScreenContent
import com.cairosquad.evolvefit.ui.util.ObserveAsEffect
import com.cairosquad.evolvefit.viewmodel.community_workout.CommunityWorkoutEffect
import com.cairosquad.evolvefit.viewmodel.community_workout.CommunityWorkoutViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CommunityWorkoutScreen(
    navigateBack: () -> Unit,
    viewModel: CommunityWorkoutViewModel = koinViewModel(),
    navigateToWorkoutDetails: (String) -> Unit,
) {
    val state by viewModel.screenState.collectAsState()

    ObserveAsEffect(viewModel.effect) { effect ->
        when (effect) {
            is CommunityWorkoutEffect.NavigateToWorkoutDetails -> navigateToWorkoutDetails(effect.workoutId)
            CommunityWorkoutEffect.NavigateBack -> navigateBack()
        }
    }
    WorkoutsScreenContent(state = state, listener = viewModel, navigateBack = navigateBack)
}

@Preview
@Composable
private fun CommunityWorkoutScreenPreview() {
    AppTheme(isDarkTheme = true) {
        CommunityWorkoutScreen(
            navigateBack = {},
            navigateToWorkoutDetails = {}
        )
    }
}