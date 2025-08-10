package com.cairosquad.evolvefit.design_system.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ic_fire
import evolvefit.composeapp.generated.resources.ic_plus
import evolvefit.composeapp.generated.resources.ic_water_drop
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CircularProgressCard(
    title: String,
    currentValue: Float,
    totalValue: Float,
    iconColor: Color,
    progressColor: Color,
    unit: String = "",
    modifier: Modifier = Modifier,
    onActionButtonClicked: () -> Unit = {},
    icon: Painter = painterResource(Res.drawable.ic_fire),
    backgroundColor: Color = Theme.color.surfaces.surfaceContainer,
    buttonClickable: Boolean = false
) {
    val percentage = (currentValue / totalValue)
        .takeIf { it.isFinite() }
        ?.coerceIn(0f, 1f)
        ?: 0f

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor)
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 12.dp, horizontal = 8.dp),
        ) {
            Row(
                modifier = Modifier.padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .size(20.dp)
                        .padding(end = 4.dp),
                    painter = icon,
                    contentDescription = "Icon description",
                    tint = iconColor
                )

                Text(
                    text = title,
                    style = Theme.textStyle.title.largeBold14,
                    color = Theme.color.surfaces.onSurfaceContainer
                )
            }
            Column(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 12.dp)
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
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 9.dp),
                    text = "${currentValue.formatForDisplay()} / ${totalValue.formatForDisplay()} $unit",
                    color = Theme.color.surfaces.outline,
                    style = Theme.textStyle.body.mediumMedium12
                )
                if (buttonClickable) {
                    Spacer(modifier = Modifier.weight(1f))
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(Theme.color.system.info)
                            .clickable(onClick = { onActionButtonClicked() }, enabled = buttonClickable),
                        contentAlignment = Alignment.Center

                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_plus),
                            contentDescription = "Icon description",
                            modifier = Modifier.size(20.dp),
                            tint = Theme.color.surfaces.textColor
                        )
                    }
                }
            }
        }
    }
}

fun Float.formatForDisplay(): String {
    val rounded = (this * 10).toInt() / 10f
    val isWholeNumber = rounded % 1 == 0f

    val numberStr = if (isWholeNumber) {
        rounded.toInt().toString()
    } else {
        rounded.toString()
    }
    return addThousandSeparators(numberStr)
}

private fun addThousandSeparators(number: String): String {
    val parts = number.split(".")
    val intPart = parts[0]
    val decimalPart = if (parts.size > 1) parts[1] else null

    val withCommas = intPart.reversed()
        .chunked(3)
        .joinToString(",")
        .reversed()
    return if (decimalPart != null) "$withCommas.$decimalPart" else withCommas
}

@Preview
@Composable
fun Preview_Dark() {
    AppTheme(isDarkTheme = true) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircularProgressCard(
                modifier = Modifier.weight(1f),
                title = "Calories",
                currentValue = 200f,
                totalValue = 1000f,
                unit = "L",
                icon = painterResource(Res.drawable.ic_fire),
                iconColor = Color.Green,
                progressColor = Color(0xFF2196F3),
                buttonClickable = false
            )

            CircularProgressCard(
                modifier = Modifier.weight(1f),
                title = "Water",
                currentValue = 1.5f,
                totalValue = 3.0f,
                unit = "L",
                icon = painterResource(Res.drawable.ic_water_drop),
                iconColor = Color(0xFF2196F3),
                progressColor = Color(0xFF2196F3),
                buttonClickable = true
            )
        }
    }
}

@Preview
@Composable
fun Preview_Light() {
    AppTheme(isDarkTheme = false) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircularProgressCard(
                modifier = Modifier.weight(1f),
                title = "Calories",
                currentValue = 2000f,
                totalValue = 100000f,
                unit = "L",
                icon = painterResource(Res.drawable.ic_fire),
                iconColor = Color.Green,
                progressColor = Color(0xFF2196F3),
                buttonClickable = false
            )

            CircularProgressCard(
                modifier = Modifier.weight(1f),
                title = "Water",
                currentValue = 1.5856f,
                totalValue = 3.10f,
                unit = "L",
                icon = painterResource(Res.drawable.ic_water_drop),
                iconColor = Color(0xFF2196F3),
                progressColor = Color(0xFF2196F3),
                buttonClickable = true
            )
        }
    }
}