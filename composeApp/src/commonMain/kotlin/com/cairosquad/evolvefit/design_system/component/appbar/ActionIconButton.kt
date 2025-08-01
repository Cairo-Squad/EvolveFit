package com.cairosquad.evolvefit.design_system.component.appbar

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter

@Composable
fun ActionIconButton(
    icon: Painter,
    contentDescription: String?,
    onClick: () -> Unit,
    tint: Color = Color.Unspecified
) {
    IconButton(onClick = onClick) {
        Icon(
            painter = icon,
            contentDescription = contentDescription,
            tint = tint
        )
    }
}
