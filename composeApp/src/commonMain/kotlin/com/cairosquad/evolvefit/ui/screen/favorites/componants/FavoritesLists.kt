package com.cairosquad.evolvefit.ui.screen.favorites.componants

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.viewmodel.favorites.MealsUiModel
import com.cairosquad.evolvefit.viewmodel.favorites.WorkoutsUiModel

@Composable
fun WorkoutList(workouts: List<WorkoutsUiModel>) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 330.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
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
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 330.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
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