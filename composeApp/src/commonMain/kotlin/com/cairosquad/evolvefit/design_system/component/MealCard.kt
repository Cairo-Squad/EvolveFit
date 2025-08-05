package com.cairosquad.evolvefit.design_system.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ic_fire
import evolvefit.composeapp.generated.resources.im_default_image
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MealCard(
    title: String,
    mealType: String,
    calories: Int,
    modifier: Modifier = Modifier,
    calorieUnit: String = stringResource(Res.string.kcal),
    model: String
) {
    Column(
        modifier = modifier,
    ) {
        Box(
            modifier = Modifier.height(124.dp)
        ) {
            NetworkImage(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(8.dp)),
                model = model,
                contentDescription = stringResource(Res.string.meal_image),
            )
            Row(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(end = 4.dp, top = 4.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(Theme.color.brand.onPrimary),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_fire),
                    contentDescription = stringResource(Res.string.calories_icon),
                    tint = Theme.color.brand.primary,
                    modifier = Modifier.padding(start = 8.dp, end = 4.dp, top = 4.dp, bottom = 4.dp)
                )
                Text(
                    text = "$calories $calorieUnit",
                    style = Theme.textStyle.body.smallRegular10,
                    color = Theme.color.brand.primary,
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
        }
        Text(
            text = title,
            style = Theme.textStyle.label.mediumMedium14,
            color = Theme.color.surfaces.onSurface,
            modifier = Modifier.padding(bottom = 4.dp, top = 8.dp)
        )
        Text(
            text = mealType,
            style = Theme.textStyle.label.smallRegular12,
            color = Theme.color.surfaces.onSurfaceVariant,
        )
    }
}

@Preview
@Composable
private fun MealCardPreview() {
    AppTheme {
        Box(
            Modifier
                .background(Theme.color.surfaces.surface)
                .padding(16.dp)
        ) {
            MealCard(
                modifier = Modifier.width(158.dp),
                title = "Avocado Toast",
                mealType = "Breakfast",
                calories = 350,
                model = "",
            )
        }
    }
}