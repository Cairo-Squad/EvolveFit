package com.cairosquad.evolvefit.design_system.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ic_fire
import evolvefit.composeapp.generated.resources.ic_plus
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CircularPercentageIndicator(
    modifier: Modifier = Modifier,
    title: String,
    currentValue: Float,
    totalValue: Float,
    unit: String = "",
    icon: Painter = painterResource(Res.drawable.ic_fire),
    iconColor: Color = Color.Blue,
    progressColor: Color = Color(0xFF8BC34A),
    backgroundColor: Color = Theme.color.surfaces.surfaceContainer,
    buttonClickable: Boolean = false
) {
    val percentage = (currentValue / totalValue).coerceIn(0f, 1f)

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor)
            .size(width = 160.dp, height = 180.dp)
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(vertical = 12.dp, horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = icon,
                    contentDescription = null,
                    modifier = Modifier
                        .size(20.dp)
                        .padding(end = 4.dp),
                    tint = iconColor
                )

                Text(
                    text = title,
                    style = Theme.textStyle.title.largeBold14,
                    color = Theme.color.surfaces.onSurfaceContainer
                )
            }
            Column(
                modifier = modifier.align(Alignment.CenterHorizontally)
            ) {
                CircularProgressCard(
                    percentage = percentage,
                    number = 100,
                    radius = 40.dp,
                    strokeWidth = 8.dp,
                    animationDuration = 1000,
                    progressColor = progressColor,
                )
            }

            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "$currentValue / $totalValue $unit",
                    color = Theme.color.surfaces.outline,
                    style = Theme.textStyle.body.mediumMedium12
                )
                if (buttonClickable) {
                    Spacer(modifier = modifier.weight(1f))
                    Box(
                        modifier = modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(Theme.color.system.info)
                            .clickable { },
                        contentAlignment = Alignment.Center

                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_plus),
                            contentDescription = null,
                            modifier = modifier.size(20.dp),
                            tint = Theme.color.surfaces.textColor
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CircularPercentageIndicatorPreview() {
    CircularPercentageIndicator(
        title = "Calories",
        currentValue = 1f,
        totalValue = 2f,
        unit = "kcal",
        progressColor = Color.Blue,
        buttonClickable = false
    )
}