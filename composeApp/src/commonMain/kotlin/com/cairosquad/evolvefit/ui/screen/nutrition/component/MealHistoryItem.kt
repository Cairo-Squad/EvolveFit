package com.cairosquad.evolvefit.ui.screen.nutrition.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.viewmodel.nutrition.NutritionScreenState
import com.cairosquad.evolvefit.viewmodel.nutrition.toMealIcon
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.kcal_unit
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun MealHistoryItem(
    meal: NutritionScreenState.MealHistory,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
            Icon(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Theme.color.surfaces.outlineVariant)
                    .padding(10.dp),
                painter = painterResource(meal.type.toMealIcon()),
                contentDescription = null,
                tint = Theme.color.brand.primary,
            )
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
                text = meal.date,
                style = Theme.textStyle.label.smallRegular12,
                color = Theme.color.surfaces.onSurfaceVariant
            )
        }
        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "${meal.calories} " + stringResource(Res.string.kcal_unit),
                style = Theme.textStyle.title.largeBold14,
                color = Theme.color.brand.primary
            )
            Text(
                text = stringResource(meal.type.displayName),
                style = Theme.textStyle.body.smallRegular10,
                color = Theme.color.surfaces.onSurfaceVariant
            )
        }
    }
}