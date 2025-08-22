package com.cairosquad.evolvefit.ui.screen.communityWorkout

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.Chip
import com.cairosquad.evolvefit.design_system.component.WorkoutCard
import com.cairosquad.evolvefit.design_system.component.appbar.ActionIconButton
import com.cairosquad.evolvefit.design_system.component.appbar.CustomAppBar
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.component.RefreshBox
import com.cairosquad.evolvefit.ui.screen.workout.WorkoutsErrorScreen
import com.cairosquad.evolvefit.ui.screen.workout.WorkoutsLoadingScreen
import com.cairosquad.evolvefit.ui.util.ObserveAsEffect
import com.cairosquad.evolvefit.viewmodel.community_workout.CommunityWorkoutEffect
import com.cairosquad.evolvefit.viewmodel.community_workout.CommunityWorkoutInteractionListener
import com.cairosquad.evolvefit.viewmodel.community_workout.CommunityWorkoutViewModel
import com.cairosquad.evolvefit.viewmodel.workout.WorkoutScreenState
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.back
import evolvefit.composeapp.generated.resources.ic_back
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
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

@Composable
private fun WorkoutsScreenContent(
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
                onSelectFocusArea = listener::onSelectFocusArea
            )

            androidx.compose.animation.Crossfade(
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
                            onClickWorkout = listener::onClickWorkout
                        )
                    }

                    WorkoutScreenState.ScreenStatus.LOADING -> {
                        WorkoutsLoadingScreen()
                    }

                    WorkoutScreenState.ScreenStatus.FAIL -> {
                        WorkoutsErrorScreen(
                            message = state.errorMessage ?: "Something went wrong",
                            onRetry = listener::onClickRetry
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun AppBar(navigateBack: () -> Unit) {
    CustomAppBar(
        title = "Community",
        header = {
            ActionIconButton(
                icon = painterResource(Res.drawable.ic_back),
                contentDescription = stringResource(Res.string.back),
                tint = Theme.color.surfaces.onSurface,
                onClick = navigateBack
            )
        }
    )
}

@Composable
private fun Workouts(
    workouts: List<WorkoutScreenState.WorkoutSuggestedUiState>,
    onClickWorkout: (String) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(workouts) { workout ->
            WorkoutCard(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .clickable { onClickWorkout(workout.id) },
                title = workout.title,
                duration = workout.duration,
                focusArea = workout.focusArea.name,
                model = workout.imageUrl,
            )
        }
    }
}

@Composable
private fun FocusAreaFilter(
    focusArea: List<WorkoutScreenState.FocusAreaUiState>,
    selectedFocusArea: WorkoutScreenState.FocusAreaUiState,
    onSelectFocusArea: (WorkoutScreenState.FocusAreaUiState) -> Unit
) {
    LazyRow(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(focusArea.size) { index ->
            val area = focusArea[index]
            Chip(
                title = area.name,
                isSelected = selectedFocusArea == area,
                onClick = { onSelectFocusArea(area) }
            )
        }
    }
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