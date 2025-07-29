package com.cairosquad.evolvefit.design_system.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun NavigationBar() {
    Row(
    ) {}
}

@Composable
private fun NavigationBarItem(
    icon: Painter,
    label: String,
    onClick: () -> Unit,
    isSelected: Boolean
) {
    Column(
        modifier = Modifier.clickable { onClick() }
    ) {
        Icon(
            painter = icon,
            contentDescription = label,
            modifier = Modifier.size(24.dp),
            tint = if (isSelected) Theme.color.brand.primary else Theme.color.surfaces.onSurfaceVariant
        )
        Text(
            text = label,
            style = Theme.textStyle.body.smallRegular10,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Preview
@Composable
fun NavigationBarPreview() {
    NavigationBar()
}