package com.cairosquad.evolvefit.design_system.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun Chip(
    title: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit={},
    isSelected: Boolean = false,
    isEnabled: Boolean = true,
    textStyle: TextStyle = Theme.textStyle.body.mediumMedium14,
    selectedContainerColor: Color = Theme.color.brand.primary,
    unselectedContainerColor: Color = Theme.color.surfaces.surfaceContainer,
    selectedTextColor: Color = Theme.color.brand.onPrimary,
    unselectedTextColor: Color = Theme.color.surfaces.onSurfaceContainer
) {
    val containerColor by animateColorAsState(
        targetValue = if (isSelected) selectedContainerColor else unselectedContainerColor,
        label = "ContainerColorAnimation"
    )

    val textColor by animateColorAsState(
        targetValue = if (isSelected) selectedTextColor else unselectedTextColor,
        label = "TextColorAnimation"
    )
    Row(
        modifier = modifier
            .height(40.dp)
            .clip(CircleShape)
            .clickable(onClick = onClick, enabled = isEnabled)
            .background(color = containerColor, shape = CircleShape),
        horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = title,
            color = textColor,
            style = textStyle,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview
@Composable
private fun SelectedArabicChipPreview() {
    AppTheme(isDarkTheme = true) {
        Chip(title = "الكل", isSelected = true, onClick = {})
    }
}

@Preview
@Composable
private fun UnSelectedArabicChipPreview() {
    AppTheme(isDarkTheme = true) {
        Chip(title = "الكل", isSelected = false, onClick = {})
    }
}

@Preview
@Composable
private fun SelectedEnglishChipPreview() {
    AppTheme(isDarkTheme = true) {
        Chip(title = "All", isSelected = true, onClick = {})
    }
}

@Preview
@Composable
private fun UnSelectedEnglishChipPreview() {
    AppTheme(isDarkTheme = true) {
        Chip(title = "All", isSelected = false, onClick = {})
    }
}

