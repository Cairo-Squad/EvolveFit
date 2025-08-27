package com.cairosquad.evolvefit.ui.screen.mealsHistory.content.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.viewmodel.meal_history.MealHistoryScreenState

@Composable
fun GroupedMealHistoryItem(
    groupedMeal: MealHistoryScreenState.DayGroupedMealsUiState,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = groupedMeal.dayHeader,
            style = Theme.textStyle.label.mediumMedium16,
            color = Theme.color.surfaces.onSurface,
            modifier = Modifier
                .padding(bottom = 8.dp).padding(horizontal = 16.dp)
        )

        Column {
            groupedMeal.meals.forEach { meal ->
                MealHistoryItem(
                    meal = meal
                )
            }
        }

    }
}