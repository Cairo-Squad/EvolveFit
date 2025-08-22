package com.cairosquad.evolvefit.ui.screen.workout

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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
import com.cairosquad.evolvefit.ui.navigation.NavBarRoute
import com.cairosquad.evolvefit.ui.navigation.navBar.Scaffold
import com.cairosquad.evolvefit.ui.screen.workout.content.WorkoutsErrorScreen
import com.cairosquad.evolvefit.ui.screen.workout.content.WorkoutsLoadingScreen
import com.cairosquad.evolvefit.ui.util.ObserveAsEffect
import com.cairosquad.evolvefit.viewmodel.workout.WorkoutEffect
import com.cairosquad.evolvefit.viewmodel.workout.WorkoutInteractionListener
import com.cairosquad.evolvefit.viewmodel.workout.WorkoutScreenState
import com.cairosquad.evolvefit.viewmodel.workout.WorkoutViewModel
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.community
import evolvefit.composeapp.generated.resources.create_workout_title_
import evolvefit.composeapp.generated.resources.ic_group
import evolvefit.composeapp.generated.resources.ic_plus
import evolvefit.composeapp.generated.resources.min
import evolvefit.composeapp.generated.resources.workouts
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun WorkoutScreen(
    navigateToCreateWorkout: () -> Unit,
    navigateToCommunityWorkout: () -> Unit,
    navigateToWorkoutDetails: (String) -> Unit,
    onSelectNavBarRoute: (navBarRoute: NavBarRoute) -> Unit,
    viewModel: WorkoutViewModel = koinViewModel()
) {
    val state by viewModel.screenState.collectAsState()

    ObserveAsEffect(viewModel.effect) { effect ->
        when (effect) {
            is WorkoutEffect.NavigateToWorkoutDetails -> navigateToWorkoutDetails(effect.workoutId)
            WorkoutEffect.NavigateToCommunityWorkout -> navigateToCommunityWorkout()
            WorkoutEffect.NavigateToCreateWorkout -> navigateToCreateWorkout()
        }
    }

    Scaffold(
        currentRoute = NavBarRoute.Workout,
        onSelectNavBarRoute = onSelectNavBarRoute
    ) {
        WorkoutsScreenContent(
            state = state,
            listener = viewModel
        )
    }
}

@Composable
private fun WorkoutsScreenContent(
    state: WorkoutScreenState,
    listener: WorkoutInteractionListener
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

            AppBar(listener::onCommunityClicked)

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
        if (state.screenStatus == WorkoutScreenState.ScreenStatus.SUCCESS) {
            AddWorkoutBtn(
                modifier = Modifier.padding(24.dp).align(Alignment.BottomEnd),
                listener::onAddWorkoutClicked
            )
        }
    }
}

@Composable
fun AddWorkoutBtn(
    modifier: Modifier = Modifier,
    onClickAddWorkout: () -> Unit
) {
    FloatingActionButton(
        onClick = onClickAddWorkout,
        modifier = modifier,
        containerColor = Theme.color.brand.primary,
        shape = CircleShape,
    ) {
        Icon(
            painter = painterResource(Res.drawable.ic_plus),
            contentDescription = stringResource(Res.string.create_workout_title_),
            modifier = Modifier.padding(12.dp).size(24.dp)
        )
    }
}

@Composable
private fun AppBar(onCommunityClick: () -> Unit) {
    CustomAppBar(
        title = stringResource(Res.string.workouts),
        tail = {
            ActionIconButton(
                icon = painterResource(Res.drawable.ic_group),
                contentDescription = stringResource(Res.string.community),
                tint = Theme.color.surfaces.onSurface,
                onClick = onCommunityClick
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
        modifier = Modifier.padding(vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(workouts) { workout ->
            WorkoutCard(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .clickable { onClickWorkout(workout.id) },
                title = workout.title,
                duration = workout.durationInMinutes + " " + stringResource(Res.string.min),
                focusArea = stringResource(workout.focusArea.nameResId),
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
                title = stringResource(area.nameResId),
                isSelected = selectedFocusArea == area,
                onClick = { onSelectFocusArea(area) }
            )
        }
    }
}

@Preview
@Composable
private fun WorkoutsScreenPreview() {
    AppTheme(isDarkTheme = true) {
        WorkoutScreen(
            navigateToCreateWorkout = {},
            navigateToCommunityWorkout = {},
            navigateToWorkoutDetails = {},
            onSelectNavBarRoute = {}
        )
    }
}