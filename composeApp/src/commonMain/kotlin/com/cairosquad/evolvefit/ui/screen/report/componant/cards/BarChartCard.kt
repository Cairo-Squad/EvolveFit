package com.cairosquad.evolvefit.ui.screen.report.componant.cards

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.screen.report.componant.animatedMeter.AnimatedBarChart
import com.cairosquad.evolvefit.ui.screen.report.componant.animatedMeter.chartComponent.ChartGrid
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun BarChartCard(
    data: List<Float>,
    labels: List<String>,
    isAnimationStarted: Boolean,
    modifier: Modifier = Modifier,
    focusedBarColor: Color = Theme.color.brand.primary,
    unfocusedBarColor: Color = Theme.color.surfaces.surfaceVariant,
    focusedLabelColor: Color = Theme.color.surfaces.onSurface,
    unFocusedLabelColor: Color = Theme.color.surfaces.onSurfaceVariant,
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
                        ChartGrid(
                            modifier = Modifier.weight(1f)
                                .height(144.dp)
                                .padding(top = 6.dp),
                            gridLinesColor = gridLinesColor,
                            steps = 4
                        )
                    }
                    AnimatedBarChart(
                        data = data,
                        focusedBarColor = focusedBarColor,
                        unfocusedBarColor = unfocusedBarColor,
                        isAnimationStarted = isAnimationStarted,
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

@Preview
@Composable
private fun BarChartCardPreview() {
    val data by remember { mutableStateOf(listOf(0f, 3f, 2f, 4f, 5f, 3f, 1f)) }
    val labels by remember {
        mutableStateOf(
            listOf(
                "Sat",
                "Sun",
                "Mon",
                "Tue",
                "Wed",
                "Thu",
                "Fri"
            )
        )
    }
    BarChartCard(
        data,
        labels,
        true
    )
}