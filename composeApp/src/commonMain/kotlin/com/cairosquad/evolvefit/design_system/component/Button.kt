package com.cairosquad.evolvefit.design_system.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun Button(
    text: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Theme.color.brand.primary,
    textColor: Color = Theme.color.brand.onPrimary,
    textStyle: TextStyle = Theme.textStyle.body.mediumMedium14,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(CircleShape)
            .background(backgroundColor),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 12.dp),
            text = text,
            color = textColor,
            style = textStyle,
        )
    }
}

@Preview
@Composable
private fun EnabledButtonPreview() {
    AppTheme(isDarkTheme = true) {
        Button(
            text = "Get Started",
            backgroundColor = Theme.color.brand.primary,
            textColor = Theme.color.brand.onPrimary
        )
    }
}

@Preview
@Composable
private fun DisabledButtonPreview() {
    AppTheme(isDarkTheme = true) {
        Button(
            text = "Get Started",
            backgroundColor = Theme.color.surfaces.outlineVariant,
            textColor = Theme.color.surfaces.outline
        )
    }
}