package com.cairosquad.evolvefit.ui.screen.nutrition.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.cairosquad.evolvefit.design_system.theme.Theme
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ic_end_arrow
import evolvefit.composeapp.generated.resources.ic_scan
import org.jetbrains.compose.resources.painterResource

@Composable
private fun ScanMeal(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .padding(top = 12.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Theme.color.surfaces.surfaceContainer)
            .padding(vertical = 12.dp, horizontal = 8.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically

    ) {

        Icon(
            modifier = Modifier
                .clip(CircleShape)
                .background(Theme.color.surfaces.surfaceVariant)
                .padding(10.dp),
            painter = painterResource(Res.drawable.ic_scan),
            contentDescription = null,
            tint = Color.Unspecified
        )
        ScanMealTextBlock(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth()
                .weight(1f),
        )
        Icon(
            painter = painterResource(Res.drawable.ic_end_arrow),
            contentDescription = null,
            tint = Color.Unspecified
        )
    }
}

@Composable
fun ScanMealTextBlock(modifier: Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Scan Meal",
            style = Theme.textStyle.title.largeBold14,
            color = Theme.color.surfaces.onSurfaceContainer
        )
        Text(
            text = "Take a picture of your meal to count calories.",
            style = Theme.textStyle.body.mediumMedium12,
            color = Theme.color.surfaces.outline
        )
    }
}