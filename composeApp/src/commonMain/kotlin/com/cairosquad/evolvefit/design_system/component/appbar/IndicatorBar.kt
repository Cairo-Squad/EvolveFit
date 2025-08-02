package com.cairosquad.evolvefit.design_system.component.appbar

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    onBackClick: () -> Unit,
    contentColor: Color
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onBackClick) {
            Icon(
                painter = painterResource(Res.drawable.ic_back),
                contentDescription = "Back",
                tint = contentColor
            )
        }

        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.weight(1f)
            ) {
                repeat(totalSteps) { index ->
                    val stepColor by animateColorAsState(
                        targetValue = if (index < currentStep)
                            Theme.color.brand.primary
                        else
                            Theme.color.surfaces.surfaceVariant,
                        animationSpec = tween(300),
                        label = "stepColor"
                    )
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(4.dp)
                            .background(stepColor, RoundedCornerShape(2.dp))
                    )
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = "$currentStep/$totalSteps",
                color = Theme.color.surfaces.onSurfaceVariant,
                style = Theme.textStyle.label.smallRegular12,
                modifier = Modifier.size(40.dp)
            )
        }
    }
}

@Preview
@Composable
private fun IndicatorBarPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {

        AppTheme(isDarkTheme = false) {
            IndicatorBar(
                currentStep = 2,
                totalSteps = 8,
                onBackClick = { },
                contentColor = Theme.color.surfaces.onSurface
            )
        }
    }
}

@Preview
@Composable
private fun IndicatorBarDarkPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        AppTheme(isDarkTheme = true) {
            IndicatorBar(
                currentStep = 2,
                totalSteps = 8,
                onBackClick = { },
                contentColor = Theme.color.surfaces.onSurface
            )
        }
    }
}

