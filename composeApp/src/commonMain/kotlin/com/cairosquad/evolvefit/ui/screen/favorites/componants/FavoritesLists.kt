package com.cairosquad.evolvefit.ui.screen.favorites.componants

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.viewmodel.favorites.MealsUiModel
import com.cairosquad.evolvefit.viewmodel.favorites.WorkoutsUiModel

@Composable
fun WorkoutList(workouts: List<WorkoutsUiModel>) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 0.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(workouts) { workout ->
            FavoritesCard(
                title = workout.name,
                subtitle = workout.estimatedTimeInSeconds.toString(),
                info = workout.focusArea,
                model = workout.imageUrl
            )
        }
    }
}

@Composable
fun MealsList(meals: List<MealsUiModel>) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 0.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(meals) { meal ->
            FavoritesCard(
                title = meal.name,
                subtitle = meal.type.toString(),
                info = meal.calories.toString(),
                model = meal.imageUrl
            )
        }
    }
}