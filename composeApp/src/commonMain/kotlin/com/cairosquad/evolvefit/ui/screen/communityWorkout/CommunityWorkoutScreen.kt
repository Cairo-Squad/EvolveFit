package com.cairosquad.evolvefit.ui.screen.communityWorkout

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.WorkoutCard
import com.cairosquad.evolvefit.design_system.theme.Theme

@Composable
fun CommunityWorkoutScreen(
    navigateBack: () -> Unit,
    navigateToWorkoutDetails: (Long) -> Unit,
    viewModel: CommunityWorkoutViewModel = koinViewModel()
    navigateToWorkoutDetails: (String) -> Unit,
    navigateToPlayWorkout: (String) -> Unit
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
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Theme.color.surfaces.surface)
                .statusBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            AppBar(navigateBack)

            BodyPartsFilter(
                bodyParts = state.bodyParts,
                selectedBodyPart = state.selectedBodyPart,
                onSelect = listener::onSelectBodyPart
            )

            Workouts(
                state.allWorkouts,
                listener::onClickWorkout
            )
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
    workouts: List<WorkoutScreenState.WorkoutUiState>,
    onClickWorkout: (Long) -> Unit
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
    onSelect: (BodyPart) -> Unit
) {
    LazyRow(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(bodyParts.size) { index ->
            val bodyPart = bodyParts[index]
            Chip(
                title = bodyPart.name,
                isSelected = selectedBodyPart == bodyPart.name,
                onClick = { onSelect(bodyPart) }
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
            navigateToWorkoutDetails = {},
        )
    }
}