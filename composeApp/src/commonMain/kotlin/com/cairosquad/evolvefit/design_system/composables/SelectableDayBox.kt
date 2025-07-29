package com.cairosquad.evolvefit.design_system.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SelectableDayBox(
    textDay: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    ) {
    val backgroundColor = if (isSelected) Theme.color.brand.primary else Theme.color.surfaces.surfaceContainer
    val textColor = if (isSelected) Theme.color.brand.onPrimary else Theme.color.surfaces.onSurface

    Box(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(8.dp)
            )
            .width(101.33333587646484.dp)
            .height(48.dp)
            .clickable { onClick() },
            contentAlignment = Alignment.Center
    ) {
        Text(
            text = textDay,
            color = textColor,
            style = Theme.textStyle.body.mediumMedium14
        )
    }
}


@Preview()
@Composable
fun SelectableBoxPreview() {
    AppTheme(isDarkTheme = true) {
        SelectableDayBox(
            textDay = "Wednesday",
            isSelected = true,
            onClick = {}
        )
    }
}

@Preview()
@Composable
fun SelectableBoxPreview2() {
    AppTheme(isDarkTheme = true) {
        SelectableDayBox(
            textDay = "Sunday",
            isSelected = true,
            onClick = {}
        )
    }
}
