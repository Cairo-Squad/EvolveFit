package com.cairosquad.evolvefit.ui.screen.workouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.composables.WorkoutCard
import com.cairosquad.evolvefit.design_system.theme.Theme

@Composable
fun WorkoutsScreen(
    navigateToCreateWorkout: () -> Unit,
    navigateToCommunityWorkout: () -> Unit,
    navigateToWorkoutDetails: (Long) -> Unit,
    navigateToPlayWorkout: (Long) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Theme.color.surfaces.surface)
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text(
            modifier = Modifier.padding(12.dp),
            text = "this is the workouts screen", color = Theme.color.surfaces.onSurface
        )

        Button(
            onClick = navigateToCommunityWorkout
        ) {
            Text("go to community workouts")
        }

        LazyRow (
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            item { Text("filter by body part", color = Theme.color.surfaces.onSurface) }
            item { Text("All", color = Theme.color.surfaces.onSurface) }
            item { Text("Arm", color = Theme.color.surfaces.onSurface) }
            item { Text("Chest", color = Theme.color.surfaces.onSurface) }
            item { Text("Back", color = Theme.color.surfaces.onSurface) }
            item { Text("Shoulder", color = Theme.color.surfaces.onSurface) }
        }

        WorkoutCard(
            modifier = Modifier.padding(horizontal = 16.dp),
            title = "Workout 1",
            duration = "25 min",
            bodyPart = "Chest",
            model = "",
            onCardClick = { navigateToWorkoutDetails(1) },
            onClickPlayButton = { navigateToPlayWorkout(1) }
        )

        WorkoutCard(
            modifier = Modifier.padding(horizontal = 16.dp),
            title = "Workout 2",
            duration = "25 min",
            bodyPart = "Chest",
            model = "",
            onCardClick = { navigateToWorkoutDetails(2) },
            onClickPlayButton = { navigateToPlayWorkout(2) }
        )

        WorkoutCard(
            modifier = Modifier.padding(horizontal = 16.dp),
            title = "Workout 3",
            duration = "25 min",
            bodyPart = "Chest",
            model = "",
            onCardClick = { navigateToWorkoutDetails(3) },
            onClickPlayButton = { navigateToPlayWorkout(3) }
        )

        Button(
            onClick = navigateToCreateWorkout
        ) {
            Text("create a new workout")
        }
    }
}