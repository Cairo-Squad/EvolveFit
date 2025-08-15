package com.cairosquad.evolvefit.ui.screen.nutrition.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.viewmodel.nutrition.NutritionInteractionListener
import com.cairosquad.evolvefit.viewmodel.nutrition.NutritionScreenState
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.add_meal
import evolvefit.composeapp.generated.resources.ic_plus
import evolvefit.composeapp.generated.resources.kcal_unit
import evolvefit.composeapp.generated.resources.remaining
import evolvefit.composeapp.generated.resources.todays_meals
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun TodayMealsSummary(
    state: NutritionScreenState,
    listener: NutritionInteractionListener,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Row(
            modifier = modifier
                .padding(horizontal = 16.dp)
                .padding(top = 32.dp, bottom = 12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = stringResource(Res.string.todays_meals),
                style = Theme.textStyle.title.largeBold16,
                color = Theme.color.surfaces.onSurface
            )
        }
        Column(
            modifier = modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(Theme.color.surfaces.surfaceContainer)
                .padding(vertical = 16.dp, horizontal = 12.dp)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(76.dp),
                modifier = Modifier
                    .fillMaxWidth()
                  .heightIn(max = 150.dp),
                userScrollEnabled = false
            ) {
                items(state.dailyMealSummaryUiStates) { meal ->
                    TodayMealItem(meal = meal)
                }
            }
            Row(
                modifier = Modifier
                    .padding(top = 20.dp, bottom = 8.dp)
                    .fillMaxWidth()
                    .height(1.dp)
                    .clip(CircleShape)
                    .background(Theme.color.surfaces.outlineVariant)
            ) { }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = stringResource(Res.string.remaining),
                        style = Theme.textStyle.title.mediumMedium14,
                        color = Theme.color.surfaces.onSurfaceContainer
                    )
                    Text(
                        text = "${state.remainingDailyCalories} " + stringResource(Res.string.kcal_unit),
                        style = Theme.textStyle.body.mediumMedium12,
                        color = Theme.color.surfaces.outline
                    )
                }
               AddMealButton(listener = listener)
            }
        }
    }
}
@Composable
private fun AddMealButton(listener: NutritionInteractionListener){
    TextButton(
        onClick = {
            listener.onAddMealSheetClicked() }
    ) {
        Icon(
            modifier = Modifier
                .padding(horizontal = 8.dp),
            painter = painterResource(Res.drawable.ic_plus),
            contentDescription = null,
            tint = Theme.color.brand.primary
        )
        Text(
            text = stringResource(Res.string.add_meal),
            style = Theme.textStyle.body.mediumMedium14,
            color = Theme.color.brand.primary
        )
    }
}
@Composable
fun TodayMealItem(
    meal: NutritionScreenState.DailyMealSummaryUiState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Theme.color.surfaces.surfaceVariant),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(meal.icon),
                contentDescription = null,
                tint = Color.Unspecified
            )
        }
        Text(
            modifier = Modifier.padding(vertical = 8.dp),
            text = stringResource(meal.type.displayName),
            style = Theme.textStyle.title.mediumMedium14,
            color = Theme.color.surfaces.onSurfaceContainer
        )
        Text(
            text = "${meal.calories} " + stringResource(Res.string.kcal_unit),
            style = Theme.textStyle.label.smallRegular12,
            color = Theme.color.surfaces.onSurfaceVariant
        )
    }
}