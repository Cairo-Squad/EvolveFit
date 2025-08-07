package com.cairosquad.evolvefit.ui.screen.workouts

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ic_group
import evolvefit.composeapp.generated.resources.ic_plus
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun WorkoutsScreen(
    navigateToCreateWorkout: () -> Unit,
    navigateToCommunityWorkout: () -> Unit,
    navigateToWorkoutDetails: (Long) -> Unit,
) {

    val bodyParts = listOf("All", "Arm", "Chest", "Back", "Shoulder")
    var selectedBodyPart by remember { mutableStateOf("All") }

    val allWorkouts = listOf(
        Workout(1, "Workout 1", "25 min", "Chest"),
        Workout(2, "Workout 2", "30 min", "Arm"),
        Workout(3, "Workout 3", "20 min", "Back"),
        Workout(4, "Workout 4", "35 min", "Shoulder"),
        Workout(5, "Workout 5", "40 min", "Chest"),
        Workout(6, "Workout 6", "35 min", "Shoulder")
    )

    val filteredWorkouts = if (selectedBodyPart == "All") {
        allWorkouts
    } else {
        allWorkouts.filter { it.bodyPart == selectedBodyPart }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Theme.color.surfaces.surface)
                .statusBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            AppBar(navigateToCommunityWorkout)

            BodyPartsFilter(
                bodyParts = bodyParts,
                selectedBodyPart = selectedBodyPart,
                onSelect = { selectedBodyPart = it }
            )

            Workouts(
                workouts = filteredWorkouts,
                navigateToWorkoutDetails = navigateToWorkoutDetails
            )
        }
        FloatingActionButton(
            onClick = navigateToCreateWorkout,
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
fun AppBar(navigateToCommunityWorkout: () -> Unit) {
    CustomAppBar(

        title = "Workouts",
        tail = {
            ActionIconButton(
                icon = painterResource(Res.drawable.ic_group),
                contentDescription = "Community",
                tint = Theme.color.surfaces.onSurface,
                onClick = navigateToCommunityWorkout
            )
        }
    )
}

@Composable
private fun Workouts(
    workouts: List<Workout>,
    navigateToWorkoutDetails: (Long) -> Unit
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
                    .clickable { navigateToWorkoutDetails(workout.id) },
                title = workout.title,
                duration = workout.duration,
                bodyPart = workout.bodyPart,
                model = "",
            )
        }
    }
}

@Composable
private fun BodyPartsFilter(
    bodyParts: List<String>,
    selectedBodyPart: String,
    onSelect: (String) -> Unit
) {
    LazyRow(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(bodyParts.size) { index ->
            val bodyPart = bodyParts[index]
            Chip(
                title = bodyPart,
                isSelected = selectedBodyPart == bodyPart,
                onClick = { onSelect(bodyPart) }
            )
        }
    }
}


@Preview
@Composable
private fun WorkoutsScreenPreview() {
    AppTheme(isDarkTheme = true) {
        WorkoutsScreen(
            navigateToCreateWorkout = {},
            navigateToCommunityWorkout = {},
            navigateToWorkoutDetails = {}
        )
    }
}

data class Workout(
    val id: Long,
    val title: String,
    val duration: String,
    val bodyPart: String
)
