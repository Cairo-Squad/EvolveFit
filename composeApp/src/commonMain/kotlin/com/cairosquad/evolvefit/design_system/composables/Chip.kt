package com.cairosquad.evolvefit.design_system.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
    isSelected: Boolean = false,
    textStyle: TextStyle = Theme.textStyle.body.mediumMedium14,
    isEnable: Boolean = true,
    onClick: () -> Unit = {}
) {
    val backgroundColor =
        if (isSelected) Theme.color.brand.primary else Theme.color.surfaces.surfaceContainer
    val textColor =
        if (isSelected) Theme.color.brand.onPrimary else Theme.color.surfaces.onSurfaceContainer

    Row(
        modifier = modifier
            .height(40.dp)
            .clip(CircleShape)
            .clickable(onClick = onClick, enabled = isEnable)
            .background(color = backgroundColor, shape = CircleShape),
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
        Chip(title = "الكل", isSelected = true)
    }
}

@Preview
@Composable
private fun UnSelectedArabicChipPreview() {
    AppTheme(isDarkTheme = true) {
        Chip(title = "الكل", isSelected = false)
    }
}

@Preview
@Composable
private fun SelectedEnglishChipPreview() {
    AppTheme(isDarkTheme = true) {
        Chip(title = "All", isSelected = true)
    }
}

@Preview
@Composable
private fun UnSelectedEnglishChipPreview() {
    AppTheme(isDarkTheme = true) {
        Chip(title = "All", isSelected = false)
    }
}

