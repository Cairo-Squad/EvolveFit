package com.cairosquad.evolvefit.ui.screen.workout

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import com.cairosquad.evolvefit.design_system.component.appbar.ActionIconButton
import com.cairosquad.evolvefit.design_system.component.appbar.CustomAppBar
import com.cairosquad.evolvefit.design_system.composables.WorkoutCard
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.entity.BodyPart
import com.cairosquad.evolvefit.ui.util.ObserveAsEffect
import com.cairosquad.evolvefit.viewmodel.workout.WorkoutEffect
import com.cairosquad.evolvefit.viewmodel.workout.WorkoutInteractionListener
import com.cairosquad.evolvefit.viewmodel.workout.WorkoutScreenState
import com.cairosquad.evolvefit.viewmodel.workout.WorkoutViewModel
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.community
import evolvefit.composeapp.generated.resources.ic_group
import evolvefit.composeapp.generated.resources.ic_plus
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun WorkoutScreen(
    navigateToCreateWorkout: () -> Unit,
    navigateToCommunityWorkout: () -> Unit,
    navigateToWorkoutDetails: (Long) -> Unit,
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

    WorkoutsScreenContent(state = state, listener = viewModel)
}

@Composable
private fun WorkoutsScreenContent(
    state: WorkoutScreenState,
    listener: WorkoutInteractionListener
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Theme.color.surfaces.surface)
                .statusBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            AppBar(listener::onClickCommunity)

            BodyPartsFilter(
                bodyParts = state.bodyParts,
                selectedBodyPart = state.selectedBodyPart,
                onSelect = listener::onSelectBodyPart
            )

            Workouts(
                workouts = state.allWorkouts,
                onClickWorkout = listener::onClickWorkout
            )

        }
        FloatingActionButton(
            onClick = listener::onClickAddWorkout,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(24.dp),
            containerColor = Theme.color.brand.primary,
            shape = CircleShape
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_plus),
                contentDescription = "Create Workout",
                modifier = Modifier.padding(12.dp).size(24.dp)
            )
        }
    }
}

@Composable
private fun AppBar(onCommunityClick: () -> Unit) {
    CustomAppBar(
        title = "Workouts",
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
    workouts: List<WorkoutScreenState.WorkoutUiModel>,
    onClickWorkout: (Long) -> Unit
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
                duration = workout.duration,
                bodyPart = workout.bodyPart.name,
                model = workout.imageUrl,
            )
        }
    }
}

@Composable
private fun BodyPartsFilter(
    bodyParts: List<BodyPart>,
    selectedBodyPart: String,
    onSelect: (String) -> Unit
) {
    LazyRow(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(bodyParts.size) { index ->
            val bodyPart = bodyParts[index].name
            Chip(
                title = bodyPart,
                isSelected = selectedBodyPart.equals(bodyPart, true),
                onClick = { onSelect(bodyPart) }
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
            navigateToWorkoutDetails = {}
        )
    }
}