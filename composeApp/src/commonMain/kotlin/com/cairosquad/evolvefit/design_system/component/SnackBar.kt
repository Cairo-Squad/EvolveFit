package com.cairosquad.evolvefit.design_system.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.adamglin.composeshadow.dropShadow
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ic_green_check_circle
import evolvefit.composeapp.generated.resources.ic_warning
import evolvefit.composeapp.generated.resources.undo
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SnackBar(
    text: String,
    isVisible: Boolean,
    modifier: Modifier = Modifier,
    paddingBottom: Dp = 12.dp,
    isUndo: Boolean = false,
    icon: Painter = painterResource(Res.drawable.ic_green_check_circle),
    iconTint: Color = Color.Unspecified,
    backgroundColor: Color = Theme.color.surfaces.surface,
    textColor: Color = Theme.color.surfaces.onSurface,
    textStyle: TextStyle = Theme.textStyle.label.mediumMedium14,
    onUndoClicked: () -> Unit = {}
) {
    val density = LocalDensity.current
    val shadowAnimatedColor by animateColorAsState(
        targetValue =
            if (isVisible) Theme.color.surfaces.dropShadow
            else Theme.color.surfaces.dropShadow.copy(alpha = 0f),
        animationSpec = tween(500)
    )
    AnimatedVisibility(
        modifier = modifier
            .navigationBarsPadding()
            .padding(horizontal = 16.dp)
            .dropShadow(
                shape = RoundedCornerShape(8.dp),
                color = shadowAnimatedColor,
                offsetX = 0.dp,
                offsetY = 40.dp,
                blur = 80.dp,
                spread = 0.dp
            ),
        visible = isVisible,
        enter = slideInVertically {
            with(density) {
                it + paddingBottom.roundToPx()
            }
        } + fadeIn(),
        exit = slideOutVertically {
            with(density) {
                it + paddingBottom.roundToPx()
            }
        } + fadeOut()
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
                tint = iconTint
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                text = text,
                color = textColor,
                style = textStyle,
            )
            if (isUndo) {
                Surface(
                    color = Color.Transparent,
                    tonalElevation = 0.dp,
                    shape = MaterialTheme.shapes.small,
                    onClick = onUndoClicked,
                    modifier = Modifier.padding(4.dp)
                ) {
                    Text(
                        text = stringResource(Res.string.undo),
                        color = Theme.color.brand.primary,
                        style = Theme.textStyle.label.mediumMedium16,
                        modifier = Modifier
                            .padding(horizontal = 12.dp, vertical = 8.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun LightSnackBarPreview() {
    AppTheme(isDarkTheme = true) {
        SnackBar(text = "Meal added to your history.", isVisible = true, isUndo = false)
    }
}

@Preview
@Composable
private fun UndoLightSnackBarPreview() {
    AppTheme(isDarkTheme = true) {
        SnackBar(
            text = "Remove from favorites?",
            icon = painterResource(Res.drawable.ic_warning),
            isVisible = true, isUndo = true
        )
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
