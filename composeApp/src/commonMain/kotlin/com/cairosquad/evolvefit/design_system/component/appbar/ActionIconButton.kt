package com.cairosquad.evolvefit.design_system.component.appbar

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun ActionIconButton(
    icon: Painter,
    contentDescription: String?,
    onClick: () -> Unit,
    tint: Color = Color.Unspecified,
    modifier: Modifier= Modifier
) {
    IconButton(onClick = onClick) {
        Icon(
            painter = icon,
            contentDescription = contentDescription,
            tint = tint,
        )
    }
}
