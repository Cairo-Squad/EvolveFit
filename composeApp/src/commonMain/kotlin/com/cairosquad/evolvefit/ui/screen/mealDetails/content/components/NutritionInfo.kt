package com.cairosquad.evolvefit.ui.screen.mealDetails.content.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.calories_format
import evolvefit.composeapp.generated.resources.carbs_format
import evolvefit.composeapp.generated.resources.fat_format
import evolvefit.composeapp.generated.resources.ic_donuts
import evolvefit.composeapp.generated.resources.ic_fire
import evolvefit.composeapp.generated.resources.ic_plant
import evolvefit.composeapp.generated.resources.ic_water_drop
import evolvefit.composeapp.generated.resources.protein_format
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun NutritionalInfo(
    calories: Int,
    carbs: Int,
    protein: Int,
    fat: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            NutritionalItem(
                icon = painterResource(Res.drawable.ic_fire),
                text = stringResource(Res.string.calories_format, calories),
                modifier = Modifier.width(165.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            NutritionalItem(
                icon = painterResource(Res.drawable.ic_plant),
                text = stringResource(Res.string.carbs_format, carbs),
                modifier = Modifier.width(151.dp)
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            NutritionalItem(
                icon = painterResource(Res.drawable.ic_water_drop),
                text = stringResource(Res.string.protein_format, protein),
                modifier = Modifier.width(165.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            NutritionalItem(
                icon = painterResource(Res.drawable.ic_donuts),
                text = stringResource(Res.string.fat_format, fat),
                modifier = Modifier.width(151.dp)
            )
        }
    }
}

@Composable
fun NutritionalItem(
    icon: Painter,
    text: String,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .size(32.dp)
                .background(color = Theme.color.surfaces.surfaceContainer, shape = RoundedCornerShape(8.dp))
                .border(1.dp, color = Theme.color.surfaces.outlineVariant, shape = RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = icon,
                contentDescription = null,
                tint = Theme.color.brand.primary,
                modifier = Modifier.size(16.dp)
            )
        }
        Text(
            text = text,
            modifier = Modifier.padding(start = 8.dp),
            color = Theme.color.surfaces.onSurface,
            style = Theme.textStyle.body.mediumMedium12
        )
    }
}
