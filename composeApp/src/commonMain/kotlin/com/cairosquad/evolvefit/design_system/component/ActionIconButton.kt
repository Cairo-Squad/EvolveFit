package com.cairosquad.evolvefit.design_system.component

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.DrawableResource

@Composable
fun ActionIconButton(
    icon: DrawableResource,
    contentDescription: String?,
    tint: Color = Color.Unspecified,
    onClick: () -> Unit
) {
    IconButton(onClick = onClick) {
        Icon(
            painter = painterResource(icon),
            contentDescription = contentDescription,
            tint = tint
        )
    }
}
