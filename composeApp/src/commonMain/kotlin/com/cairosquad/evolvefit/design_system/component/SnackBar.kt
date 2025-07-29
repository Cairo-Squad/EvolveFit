package com.cairosquad.evolvefit.design_system.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ic_green_check_circle
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SnackBar(
    text: String,
    isVisible: Boolean,
    modifier: Modifier = Modifier,
    icon: Painter = painterResource(Res.drawable.ic_green_check_circle),
    backgroundColor: Color = Theme.color.surfaces.surface,
    textColor: Color = Theme.color.surfaces.onSurface,
    textStyle: TextStyle = Theme.textStyle.label.mediumMedium14,
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn() + slideInVertically { it },
        exit = fadeOut() + slideOutVertically { it }
    ) {
        Row(
            modifier = modifier.fillMaxWidth().clip(RoundedCornerShape(8.dp))
                .background(backgroundColor).padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.padding(end = 8.dp),
                painter = icon,
                contentDescription = null,
                tint = Color.Unspecified
            )
            Text(
                modifier = Modifier.fillMaxWidth()
                    .weight(1f),
                text = text,
                color = textColor,
                style = textStyle,
            )
        }
    }
}

@Preview
@Composable
private fun LightSnackBarPreview() {
    AppTheme(isDarkTheme = false) {
        SnackBar(text = "Meal added to your history.",isVisible = true)
    }
}

@Preview
@Composable
private fun DarkSnackBarPreview() {
    AppTheme(isDarkTheme = true) {
        SnackBar(text = "Meal added to your history.",isVisible = true)
    }
}
