package com.cairosquad.evolvefit.design_system.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.text_styles.TextStyle
import com.cairosquad.evolvefit.design_system.theme.Theme
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.im_default_image
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MealHistoryCard(
    title: String,
    dateTime: String,
    calories: Int,
    mealType: String,
    icon: DrawableResource = Res.drawable.im_default_image,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth().height(68.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = "Image",
            Modifier
                .padding(vertical = 4.dp)
                .size(48.dp)
                .clip(CircleShape)
        )
        Column(
            modifier = Modifier.weight(1f)
                .padding(start = 12.dp, top = 8.5.dp, bottom = 8.5.dp),
            verticalArrangement = Arrangement.SpaceEvenly
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
                text = "$calories Kcal",
                style = TextStyle().title.largeBold14,
                color = Theme.color.brand.primary
            )
            Text(
                text = mealType,
                style = TextStyle().label.smallRegular12,
                color = Theme.color.surfaces.onSurfaceVariant,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Preview
@Composable
private fun MealHistoryCardPreview() {
    MealHistoryCard(
        title = "Oatmeal with Fruits Squats",
        dateTime = "Today, 7:30 AM",
        calories = 320,
        mealType = "Breakfast"
    )
}