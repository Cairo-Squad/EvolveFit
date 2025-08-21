package com.cairosquad.evolvefit.design_system.component.appbar

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun StepsIndicator(
    currentStep: Int,
    totalSteps: Int,
    modifier: Modifier = Modifier,
    spacing: Dp = 4.dp,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(spacing),
    ) {
        repeat(totalSteps) { index ->
            val stepColor by animateColorAsState(
                targetValue = if (index < currentStep)
                    Theme.color.brand.primary
                else
                    Theme.color.brand.primaryContainer,
                animationSpec = tween(300),
                label = "stepColor"
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(4.dp)
                    .background(stepColor, CircleShape)
            )
        }
    }
}

@Preview
@Composable
private fun StepsIndicatorPreview() {
    AppTheme(
        isDarkTheme = false
    ) {
        Column(
            modifier = Modifier
                .background(Theme.color.surfaces.surface)
                .padding(16.dp)
        ) {
            StepsIndicator(
                currentStep = 2,
                totalSteps = 8,
            )
        }
    }
}

@Preview
@Composable
private fun StepsIndicatorDarkPreview() {
    AppTheme(
        isDarkTheme = true
    ) {
        Column(
            modifier = Modifier
                .background(Theme.color.surfaces.surface)
                .padding(16.dp)
        ) {
            StepsIndicator(
                currentStep = 2,
                totalSteps = 8,
            )
        }
    }
}

