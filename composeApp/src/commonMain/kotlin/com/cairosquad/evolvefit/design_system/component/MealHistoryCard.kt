package com.cairosquad.evolvefit.design_system.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ic_coffee
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MealHistoryCard(
    title: String,
    dateTime: String,
    calories: Int,
    mealType: String,
    icon: Painter,
    modifier: Modifier = Modifier,
    calorieUnit: String = "Kcal"
) {
    Row(
        modifier = modifier.padding(vertical = 16.dp).fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            Modifier
                .padding(vertical = 4.dp)
                .size(48.dp)
                .border(
                    width = 1.dp,
                    color = Theme.color.surfaces.outlineVariant,
                    shape = CircleShape
                ).clip(CircleShape)
                .background(color = Theme.color.surfaces.surfaceContainer),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = icon,
                contentDescription = "Meal Type",
                modifier = Modifier.padding(12.dp),
                tint = Theme.color.brand.primary
            )
        }
        Column(
            modifier = Modifier.weight(1f)
                .padding(start = 12.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = title,
                style = Theme.textStyle.title.largeBold14,
                color = Theme.color.surfaces.onSurface
            )
            Text(
                text = dateTime,
                modifier = Modifier.padding(top = 8.dp),
                style = Theme.textStyle.label.smallRegular12,
                color = Theme.color.surfaces.onSurfaceVariant
            )
        }
        Column(
            modifier = Modifier.padding(start = 8.dp),
            verticalArrangement = Arrangement.Center

        ) {
            Text(
                text = "$calories $calorieUnit",
                style = Theme.textStyle.title.largeBold14,
                color = Theme.color.brand.primary
            )
            Text(
                text = mealType,
                modifier = Modifier.padding(top = 4.dp),
                style = Theme.textStyle.body.smallRegular10,
                color = Theme.color.surfaces.onSurfaceVariant
            )
        }
    }
}

@Preview
@Composable
private fun MealHistoryCardPreview() {
    AppTheme(isDarkTheme = false) {
        Box(
            Modifier
                .background(Theme.color.surfaces.surface)
                .padding(16.dp)
        ) {
            MealHistoryCard(
                title = "Oatmeal with Fruits",
                dateTime = "Today, 7:30 AM",
                calories = 320,
                mealType = "Breakfast",
                icon = painterResource(Res.drawable.ic_coffee),
            )
        }
    }
}