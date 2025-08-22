package com.cairosquad.evolvefit.ui.screen.favorites.content.componants

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.viewmodel.favorites.MealsUiModel
import com.cairosquad.evolvefit.viewmodel.favorites.WorkoutsUiModel

@Composable
fun WorkoutList(
    workouts: List<WorkoutsUiModel>,
    onSaveIconClick: (String) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 330.dp),
        contentPadding = PaddingValues( 16.dp,),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(workouts, key = { it.id }) { workout ->
            var visible by remember { mutableStateOf(true) }

            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                FavoritesCard(
                    title = workout.name,
                    subtitle = workout.estimatedTimeInSeconds.toString(),
                    info = workout.focusArea,
                    model = workout.imageUrl,
                    onSaveIconClick = {
                        visible = false
                        onSaveIconClick(workout.id)
                    }
                )
            }
        }
    }
}

@Composable
fun MealsList(
    meals: List<MealsUiModel>,
    onSaveIconClick: (String) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 330.dp),
        contentPadding = PaddingValues(16.dp,),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(meals, key = { it.id }) { meal ->
            var visible by remember { mutableStateOf(true) }

            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                FavoritesCard(
                    title = meal.name,
                    subtitle = meal.type.toString(),
                    info = meal.calories.toString(),
                    model = meal.imageUrl,
                    onSaveIconClick = {
                        visible = false
                        onSaveIconClick(meal.id)
                    }
                )
            }
        }
    }
}