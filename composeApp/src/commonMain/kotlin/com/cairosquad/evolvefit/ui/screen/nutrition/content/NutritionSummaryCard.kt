package com.cairosquad.evolvefit.ui.screen.nutrition.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.composables.CircularPercentageIndicator
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.viewmodel.nutrition.NutritionInteractionListener
import com.cairosquad.evolvefit.viewmodel.nutrition.NutritionScreenState
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.calories
import evolvefit.composeapp.generated.resources.ic_fire
import evolvefit.composeapp.generated.resources.ic_water_drop
import evolvefit.composeapp.generated.resources.kcal_unit
import evolvefit.composeapp.generated.resources.liter_unit
import evolvefit.composeapp.generated.resources.water
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun NutritionSummaryCard(
    state: NutritionScreenState,
    listener: NutritionInteractionListener,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircularPercentageIndicator(
                modifier = Modifier.weight(1f),
                title = stringResource(Res.string.calories),
                currentValue = state.caloriesGoal,
                totalValue = state.caloriesConsumed,
                unit = stringResource(Res.string.kcal_unit),
                icon = painterResource(Res.drawable.ic_fire),
                iconColor = Theme.color.system.success,
                progressColor = Theme.color.system.success,
                buttonClickable = false
            )
            CircularPercentageIndicator(
                modifier = Modifier.weight(1f),
                title = stringResource(Res.string.water),
                onActionButtonClicked = { listener.onAddWaterClicked() },
                currentValue =state.waterConsumedLiters,
                totalValue = state.waterGoalLiters,
                unit = stringResource(Res.string.liter_unit),
                icon = painterResource(Res.drawable.ic_water_drop),
                iconColor = Theme.color.system.info,
                progressColor = Theme.color.system.info,
                buttonClickable = true
            )
        }
    }
}