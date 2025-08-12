package com.cairosquad.evolvefit.design_system.component.appbar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ic_back
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun IndicatorBar(
    currentStep: Int,
    totalSteps: Int,
    onClickLeadingIcon: () -> Unit,
    modifier: Modifier = Modifier,
    leadingIcon: Painter = painterResource(Res.drawable.ic_back),
    isStepsCountVisible: Boolean = true,
    iconTint: Color = Theme.color.surfaces.onSurface,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .clickable(onClick = onClickLeadingIcon)
                .padding(8.dp),
            painter = leadingIcon,
            contentDescription = "Back",
            tint = iconTint
        )

        StepsIndicator(
            modifier = Modifier
                .padding(start = 12.dp)
                .weight(1f),
            currentStep = currentStep,
            totalSteps = totalSteps,
        )

        if (isStepsCountVisible) {
            Text(
                modifier = Modifier
                    .padding(start = 12.dp)
                    .width(40.dp),
                text = "$currentStep/$totalSteps",
                color = Theme.color.surfaces.onSurfaceVariant,
                style = Theme.textStyle.label.smallRegular12,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
private fun IndicatorBarPreview() {
    AppTheme(
        isDarkTheme = false
    ) {
        Column(
            modifier = Modifier
                .background(Theme.color.surfaces.surface)
        ) {
            IndicatorBar(
                currentStep = 2,
                totalSteps = 8,
                onClickLeadingIcon = { },
                iconTint = Theme.color.surfaces.onSurface,
            )
        }
    }
}

@Preview
@Composable
private fun IndicatorBarDarkPreview() {
    AppTheme(
        isDarkTheme = true
    ) {
        Column(
            modifier = Modifier
                .background(Theme.color.surfaces.surface)
        ) {
            IndicatorBar(
                currentStep = 2,
                totalSteps = 8,
                onClickLeadingIcon = { },
                iconTint = Theme.color.surfaces.onSurface,
            )
        }
    }
}

