package com.cairosquad.evolvefit.ui.screen.communityWorkout.content

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.component.RefreshBox
import com.cairosquad.evolvefit.ui.screen.communityWorkout.content.component.AppBar
import com.cairosquad.evolvefit.ui.screen.workout.WorkoutsErrorScreen
import com.cairosquad.evolvefit.ui.screen.workout.WorkoutsLoadingScreen
import com.cairosquad.evolvefit.viewmodel.community_workout.CommunityWorkoutInteractionListener
import com.cairosquad.evolvefit.viewmodel.workout.WorkoutScreenState

@Composable
 fun WorkoutsScreenContent(
    state: WorkoutScreenState,
    listener: CommunityWorkoutInteractionListener,
    navigateBack: () -> Unit
) {
    RefreshBox(
        isRefreshing = state.isRefreshing,
        onRefresh = listener::onRefresh
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Theme.color.surfaces.surface)
                .statusBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            AppBar(navigateBack)

            FocusAreaFilter(
                focusArea = WorkoutScreenState.FocusAreaUiState.entries,
                selectedFocusArea = state.selectedFocusArea,
                onSelectFocusArea = listener::onFocusAreaSelected
            )

            Crossfade(
                targetState = state.screenStatus,
                animationSpec = tween(
                    durationMillis = 400,
                    easing = FastOutSlowInEasing
                )
            ) { status ->
                when (status) {
                    WorkoutScreenState.ScreenStatus.SUCCESS -> {
                        Workouts(
                            workouts = state.allWorkouts,
                            onClickWorkout = listener::onWorkoutClicked
                        )
                    }

                    WorkoutScreenState.ScreenStatus.LOADING -> {
                        WorkoutsLoadingScreen()
                    }

                    WorkoutScreenState.ScreenStatus.FAIL -> {
                        WorkoutsErrorScreen(
                            message = state.errorMessage ?: "Something went wrong",
                            onRetry = listener::onRetryClicked
                        )
                    }
                }
            }
        }
    }
}