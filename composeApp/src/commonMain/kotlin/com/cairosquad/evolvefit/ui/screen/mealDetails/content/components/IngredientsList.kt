package com.cairosquad.evolvefit.ui.screen.mealDetails.content.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ingredients
import org.jetbrains.compose.resources.stringResource

@Composable
 fun IngredientsList(ingredients: List<String>) {
    Column {
        Text(
            stringResource(Res.string.ingredients),
            style = Theme.textStyle.title.largeBold16,
            color = Theme.color.surfaces.onSurface
        )
        Spacer(modifier = Modifier.height(12.dp))

        Column (
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            repeat(ingredients.size) { index ->
                Text(
                    "• ${ingredients[index]}",
                    style = Theme.textStyle.label.smallRegular14,
                    color = Theme.color.surfaces.onSurfaceVariant,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}