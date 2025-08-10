package com.cairosquad.evolvefit.ui.screen.report.componant.cards

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.screen.report.componant.animatedMeter.AnimatedBarChart

@Composable
fun DynamicWorkoutsChartCard(
    data: List<Float>,
    labels: List<String>,
    modifier: Modifier = Modifier,
    focusedBarColor: Color = Theme.color.brand.primary,
    unfocusedBarColor: Color = Theme.color.surfaces.surfaceVariant,
    focusedLabelColor: Color = Theme.color.surfaces.onSurface,
    unFocusedLabelColor: Color = Theme.color.surfaces.surfaceVariant,
    gridLinesColor: Color = Theme.color.surfaces.outlineVariant,
    steps: Int = 4
) {
    var barCenters by remember { mutableStateOf<List<Float>>(emptyList()) }

    val maxValue = (data.maxOrNull() ?: 0f).coerceAtLeast(1f)
    val maxIndex = data.indexOf(maxValue)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Theme.color.surfaces.surfaceContainer)
            .padding(horizontal = 12.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(bottom = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Workouts Per Week",
                style = Theme.textStyle.headline.largeBold16,
                color = Theme.color.surfaces.onSurface
            )
            Text(
                text = "Weekly",
                style = Theme.textStyle.label.smallRegular14,
                color = Theme.color.brand.primary
            )
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .height(150.dp)
                    .padding(end = 8.dp, bottom = 8.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                for (i in steps downTo 0) {
                    Text(
                        text = "${(maxValue / steps * i).toInt()}",
                        style = Theme.textStyle.body.smallRegular10,
                        color = Theme.color.surfaces.onSurfaceVariant
                    )
                }
            }
            Column {
                Box {
                    Row {
                        Canvas(
                            modifier = Modifier
                                .weight(1f)
                                .height(144.dp)
                                .padding(top = 6.dp)
                        ) {
                            val maxHeight = size.height

                            for (i in 0..steps) {
                                val y = maxHeight - (i / steps.toFloat()) * maxHeight
                                drawLine(
                                    color = gridLinesColor,
                                    start = Offset(0f, y),
                                    end = Offset(size.width, y),
                                    strokeWidth = 0.5.dp.toPx(),
                                    cap = StrokeCap.Square
                                )
                            }
                        }

                    }
                    AnimatedBarChart(
                        data = data,
                        focusedBarColor = focusedBarColor,
                        unfocusedBarColor = unfocusedBarColor,
                        onBarCentersCalculated = {
                            barCenters = it
                        }
                    )
                }
                Box(
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .fillMaxWidth()
                ) {
                    barCenters.forEachIndexed { index, centerX ->
                        val isMax = index == maxIndex
                        Text(
                            text = labels.getOrNull(index) ?: "",
                            color = if (isMax) focusedLabelColor else unFocusedLabelColor,
                            style = Theme.textStyle.label.smallRegular12,
                            modifier = Modifier
                                .absoluteOffset(x = with(LocalDensity.current) { centerX.toDp() } - 10.dp)
                        )
                    }
                }
            }
        }

    }
}