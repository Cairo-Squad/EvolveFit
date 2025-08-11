package com.cairosquad.evolvefit.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ic_fire
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SimpleNutritionCard(
    iconRes: DrawableResource,
    iconTint: Color,
    name: String,
    value: String,
    goal: String,
    remaining: String,
    unit: String,
    isUnitShownOnBar: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Theme.color.surfaces.surfaceContainer)
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
                .padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            NutritionIcon(
                iconRes = iconRes,
                iconTint = iconTint,
                unit = unit,
                modifier = Modifier.padding(end = 8.dp)
            )

            Text(
                text = name,
                style = Theme.textStyle.title.largeBold14,
                color = Theme.color.surfaces.onSurfaceContainer
            )
        }
    }
}

@Composable
private fun NutritionIcon(
    iconRes: DrawableResource,
    iconTint: Color,
    unit: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .size(32.dp)
            .background(Theme.color.surfaces.surfaceVariant),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(iconRes),
            contentDescription = unit,
            tint = iconTint,
            modifier = Modifier
                .size(16.dp)
        )
    }
}

@Preview
@Composable
private fun PreviewSimpleNutritionCard() {
    AppTheme(
        isDarkTheme = true
    ) {
        SimpleNutritionCard(
            iconRes = Res.drawable.ic_fire,
            iconTint = Theme.color.success,
            name = "Calories",
            value = "1,650",
            goal = "2,200",
            remaining = "550",
            unit = "550",
            isUnitShownOnBar = false
        )
    }
}