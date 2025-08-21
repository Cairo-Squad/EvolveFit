package com.cairosquad.evolvefit.ui.screen.mealDetails.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme

@Composable
 fun MealDescription(
    mealName: String,
    mealType: String,
    mealDescription: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 24.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().weight(1f)
            ) {
                Text(
                    mealName,
                    style = Theme.textStyle.headline.largeBold18,
                    color = Theme.color.surfaces.onSurface,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            Text(
                mealType,
                style = Theme.textStyle.title.mediumMedium14,
                color = Theme.color.brand.primary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(start = 8.dp)

            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            mealDescription,
            style = Theme.textStyle.label.smallRegular14,
            color = Theme.color.surfaces.outline
        )
    }
}