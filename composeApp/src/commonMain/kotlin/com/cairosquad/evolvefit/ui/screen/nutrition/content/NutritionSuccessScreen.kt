package com.cairosquad.evolvefit.ui.screen.nutrition.content

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.screen.nutrition.content.component.NutritionSummaryCard
import com.cairosquad.evolvefit.viewmodel.nutrition.NutritionInteractionListener
import com.cairosquad.evolvefit.viewmodel.nutrition.NutritionScreenState
import com.cairosquad.evolvefit.viewmodel.nutrition.toMealIcon
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.kcal_unit
import evolvefit.composeapp.generated.resources.meal_history
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource


@Composable
fun NutritionSuccessScreen(
    state: NutritionScreenState,
    listener: NutritionInteractionListener
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.color.surfaces.surface)
    ) {
        item { NutritionSummaryCard(listener = listener, state = state) }
        item { TodayMealsSummary(state = state, listener = listener) }
        item { SuggestedMeals(state = state, listener = listener) }
        mealHistorySection(state, listener)
    }
}

private fun LazyListScope.mealHistorySection(
    state: NutritionScreenState,
    listener: NutritionInteractionListener
) {
    item {
        SeeAll(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 32.dp, bottom = 12.dp),
            onViewAllClick = listener::onViewAllMealHistoryClicked,
            sectionTitle = stringResource(Res.string.meal_history)
        )
    }
    if (state.todayConsumedMeals.isNotEmpty()) {
        items(state.todayConsumedMeals) { mealHistory ->
            MealHistoryItem(mealHistory)
        }
    } else {
        item {
            EmptyMealHistory()
        }
    }
}

@Composable
fun MealHistoryItem(
    meal: NutritionScreenState.ConsumedMealUiState,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Theme.color.surfaces.surfaceContainer)
                .border(
                    width = 1.dp,
                    color = Theme.color.surfaces.outlineVariant,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter =painterResource( meal.type.toMealIcon()),
                contentDescription = null,
                tint = Theme.color.brand.primary,
            )
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 12.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = meal.name,
                style = Theme.textStyle.title.largeBold14,
                color = Theme.color.surfaces.onSurface
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = meal.date,
                style = Theme.textStyle.label.smallRegular12,
                color = Theme.color.surfaces.onSurfaceVariant
            )
        }
        Column(
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = "${meal.calories} " + stringResource(Res.string.kcal_unit),
                style = Theme.textStyle.title.largeBold14,
                color = Theme.color.brand.primary
            )
            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = stringResource(meal.type.displayName),
                style = Theme.textStyle.body.smallRegular10,
                color = Theme.color.surfaces.onSurfaceVariant
            )
        }
    }
}
