package com.cairosquad.evolvefit.design_system.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.text_styles.TextStyle
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
    subtitle: String,
    calories: Int,
    image: DrawableResource = Res.drawable.im_default_image,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .width(158.dp)
    ) {
        Box(
            modifier = Modifier.height(124.dp)
        ) {
            Image(
                painter = painterResource(image),
                contentDescription = "Meal Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
            )
            Row(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Theme.color.brand.onPrimary),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_fire),
                    contentDescription = "Fire Icon",
                    tint = Theme.color.brand.primary,
                    modifier = Modifier.padding(start = 8.dp, end = 4.dp, top = 4.dp, bottom = 4.dp)
                )
                Text(
                    text = "$calories kcal",
                    style = TextStyle().body.smallRegular10,
                    color = Theme.color.brand.primary,
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
        }
        Text(
            text = title,
            style = TextStyle().label.mediumMedium14,
            color = Theme.color.surfaces.onSurface,
            modifier = Modifier.padding(bottom = 4.dp, top = 8.dp)
        )
        Text(
            text = subtitle,
            style = TextStyle().label.smallRegular12,
            color = Theme.color.surfaces.onSurfaceVariant,
        )
    }
}

@Preview
@Composable
private fun MealCardPreview() {
    MealCard(
        title = "Avocado Toast",
        subtitle = "Breakfast",
        calories = 350
    )
}