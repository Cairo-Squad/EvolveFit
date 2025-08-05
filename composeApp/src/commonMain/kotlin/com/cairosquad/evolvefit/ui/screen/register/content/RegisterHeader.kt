package com.cairosquad.evolvefit.ui.screen.register.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun OnboardingHeader(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    titleColor: Color = Theme.color.surfaces.onSurface,
    descriptionColor: Color = Theme.color.surfaces.onSurfaceVariant,
    titleStyle: TextStyle = Theme.textStyle.headline.mediumMedium18,
    descriptionStyle: TextStyle = Theme.textStyle.label.smallRegular14
) {
    Column(
        modifier = modifier.padding(horizontal = 16.dp).fillMaxWidth()
    ) {
        Text(
            text = title,
            color = titleColor,
            style = titleStyle,
        )
        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = description,
            color = descriptionColor,
            style = descriptionStyle,
        )
    }
}

@Preview
@Composable
private fun OnboardingHeaderPreview() {
    AppTheme(isDarkTheme = true) {
        OnboardingHeader(
            title = "Workout Days", description = "How often would you like to workout?"
        )
    }
}