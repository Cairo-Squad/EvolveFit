package com.cairosquad.evolvefit.design_system.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import kotlinx.coroutines.delay
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
        modifier = modifier
            .navigationBarsPadding()
            .padding(bottom = 24.dp, start = 16.dp, end = 16.dp),
        visible = isVisible,
        enter = fadeIn() + slideInVertically { it*2 },
        exit = fadeOut() + slideOutVertically { it*2 }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(backgroundColor)
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.padding(end = 8.dp),
                painter = icon,
                contentDescription = null,
                tint = Color.Unspecified
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
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
    AppTheme(isDarkTheme = true) {
        SnackBar(text = "Meal added to your history.", isVisible = true)
    }
}
@Preview
@Composable
private fun DarkSnackBarPreview() {
    var isVisible by remember { mutableStateOf(false) }
    LaunchedEffect(isVisible) {
        if (!isVisible) return@LaunchedEffect
        delay(3000)
        isVisible = false
    }
    AppTheme(isDarkTheme = true) {
        Box(modifier = Modifier.fillMaxSize()) {
            Button(
                modifier = Modifier.align(Alignment.Center),
                onClick = { isVisible = !isVisible }) {
                Text("Show SnackBar")
            }
            SnackBar(
                modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 24.dp),
                text = "Meal added to your history.", isVisible = isVisible
            )
        }

    }
}
