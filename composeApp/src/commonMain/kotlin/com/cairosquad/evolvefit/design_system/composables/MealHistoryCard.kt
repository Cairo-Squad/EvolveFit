package com.cairosquad.evolvefit.design_system.composables

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
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.text_styles.TextStyle
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ic_coffee
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MealHistoryCard(
    title: String,
    dateTime: String,
    calories: Int,
    mealType: String,
    icon: DrawableResource,
    modifier: Modifier = Modifier,
    calorieUnit: String = "Kcal"
) {
    Row(
        modifier = modifier.fillMaxWidth().padding(16.dp),
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
                painter = painterResource(icon),
                contentDescription = "Meal Type",
                modifier = Modifier.padding(12.dp),
                tint = Theme.color.brand.primary
            )
        }
        Column(
            modifier = Modifier.weight(1f)
                .padding(start = 12.dp, top = 4.5.dp, bottom = 4.5.dp),
        ) {
            Text(
                text = title,
                style = TextStyle().title.largeBold14,
                color = Theme.color.surfaces.onSurface
            )
            Text(
                text = dateTime,
                style = TextStyle().label.smallRegular12,
                color = Theme.color.surfaces.onSurfaceVariant,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
        Column(
            modifier = Modifier.padding(start = 8.dp, top = 11.5.dp, bottom = 11.5.dp)
        ) {
            Text(
                text = "$calories $calorieUnit",
                style = Theme.textStyle.title.largeBold14,
                color = Theme.color.brand.primary
            )
            Text(
                text = mealType,
                style = Theme.textStyle.body.smallRegular10,
                color = Theme.color.surfaces.onSurfaceVariant,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Preview
@Composable
private fun MealHistoryCardPreview() {
    AppTheme {
        Box(
            Modifier
                .background(Theme.color.surfaces.surface)
                .padding(16.dp)
        )
        MealHistoryCard(
            title = "Oatmeal with Fruits Squats",
            dateTime = "Today, 7:30 AM",
            calories = 320,
            mealType = "Breakfast",
            icon = Res.drawable.ic_coffee,
        )
    }
}