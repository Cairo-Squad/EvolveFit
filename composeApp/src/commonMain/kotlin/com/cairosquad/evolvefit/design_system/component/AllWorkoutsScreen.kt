package com.cairosquad.evolvefit.design_system.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.composables.WorkoutCard
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun AllWorkoutsScreen(workouts: List<Workout>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(workouts) { workout ->
                WorkoutCard(
                    title = workout.title,
                    duration = workout.duration,
                    bodyPart = workout.category,
                    model = workout.model,
                )
            }
        }
    }
}

val workouts = listOf(
    Workout(
        title = "Jumping Jacks",
        duration = "25",
        category = "Full Body",
        model = "",
    ),
    Workout(
        title = "Push-ups",
        duration = "45",
        category = "Chest, Arms",
        model = "",
    ),
    Workout(
        title = "Mountain Climbers",
        duration = "18",
        category = "Abs, Cardio",
        model = "",
    ),
    Workout(
        title = "Lunges (Each Leg)",
        duration = "45",
        category = "Legs",
        model = "",
    ),
    Workout(
        title = "Lunges (Each Leg)",
        duration = "45",
        category = "Legs",
        model = "",
    ),
    Workout(
        title = "Lunges (Each Leg)",
        duration = "45",
        category = "Legs",
        model = "",
    ),
    Workout(
        title = "Lunges (Each Leg)",
        duration = "45",
        category = "Legs",
        model = "",
    ),
) // TODO : replace with data from api

data class Workout(
    val title: String,
    val duration: String,
    val category: String,
    val model: String,
) //TODO : remove this is just now until data classes created

@Preview
@Composable
private fun AllWorkoutsScreenPreview() {
    AppTheme(isDarkTheme = true) {
        Box(modifier = Modifier.background(Theme.color.surfaces.surface)) {
            AllWorkoutsScreen(workouts = workouts)
        }
    }
}