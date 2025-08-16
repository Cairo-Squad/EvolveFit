package com.cairosquad.evolvefit.design_system.component

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    isLoading: Boolean = false,
    enabledContainerColor: Color = Theme.color.brand.primary,
    disabledContainerColor: Color = Theme.color.surfaces.outlineVariant,
    enabledTextColor: Color = Theme.color.brand.onPrimary,
    disabledTextColor: Color = Theme.color.surfaces.outline,
    textStyle: TextStyle = Theme.textStyle.body.mediumMedium14,
) {
    val containerColor by animateColorAsState(
        targetValue = if (isEnabled) enabledContainerColor else disabledContainerColor,
        label = "ContainerColorAnimation"
    )

    val textColor by animateColorAsState(
        targetValue = if (isEnabled) enabledTextColor else disabledTextColor,
        label = "TextColorAnimation"
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(CircleShape)
            .background(containerColor)
            .clickable(enabled = isEnabled && !isLoading, onClick = onClick),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Text(
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 12.dp),
            text = text,
            color = textColor,
            style = textStyle,
        )
        Crossfade(targetState = isLoading) { loading ->
        if (loading) {
            Spacer(modifier = Modifier.padding(start = 8.dp))
            AnimatedLoadingIndicator(
                size = 20.dp
            )
        }
        }

    }
}

@Preview
@Composable
private fun EnabledButtonPreview() {
    AppTheme(isDarkTheme = true) {
        PrimaryButton(
            text = "Get Started", onClick = {}
        )
    }
}

@Preview
@Composable
private fun DisabledButtonPreview() {
    AppTheme(isDarkTheme = true) {
        PrimaryButton(
            text = "Get Started",
            isEnabled = false, onClick = {}
        )
    }
}