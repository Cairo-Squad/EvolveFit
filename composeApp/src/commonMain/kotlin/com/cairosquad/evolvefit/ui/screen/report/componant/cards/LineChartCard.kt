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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.screen.report.componant.animatedMeter.AnimatedLineChart
import com.cairosquad.evolvefit.ui.screen.report.componant.animatedMeter.chartComponent.ChartGrid
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun LineChartCard(
    data: List<Float>,
    labels: List<String>,
    totalTime: String,
    modifier: Modifier = Modifier,
    lineColor: Color = Theme.color.brand.primary,
    areaColor: List<Color> = Theme.color.gradiant.barGradiant,
    markerColor: Color = Theme.color.brand.primary,
    gridLinesColor: Color = Theme.color.surfaces.outlineVariant,
    focusedLabelColor: Color = Theme.color.surfaces.onSurface,
    unFocusedLabelColor: Color = Theme.color.surfaces.onSurfaceVariant,
    steps: Int = 4
) {

    val maxValue = (data.maxOrNull() ?: 0f).coerceAtLeast(1f)
    val maxIndex = data.indexOf(maxValue)
    var barCenters: List<Offset> by remember { mutableStateOf(emptyList()) }

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
                text = "Time Spent Training",
                style = Theme.textStyle.headline.largeBold16,
                color = Theme.color.surfaces.onSurface
            )
            Text(
                text = totalTime,
                style = Theme.textStyle.label.smallRegular14,
                color = Theme.color.brand.primary
            )
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .height(108.dp)
                    .padding(end = 8.dp, bottom = 8.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                for (i in steps downTo 0) {
                    Text(
                        text = "${(maxValue / steps * i).toInt()} min",
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
                                .height(96.dp)
                                .padding(top = 6.dp),
                            gridLinesColor = gridLinesColor,
                            steps = 4
                        )
                    }
                    AnimatedLineChart(
                        modifier = Modifier.height(106.dp),
                        data = data,
                        lineColor = lineColor,
                        areaColor = areaColor,
                        markerColor = markerColor,
                        onPointCentersCalculated = { barCenters = it }
                    )
                }
                Box(
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .fillMaxWidth()
                ) {
                    barCenters.forEachIndexed { index, offset ->
                        val isMax = index == maxIndex
                        Text(
                            text = labels.getOrNull(index) ?: "",
                            color = if (isMax) focusedLabelColor else unFocusedLabelColor,
                            style = Theme.textStyle.label.smallRegular12,
                            modifier = Modifier
                                .absoluteOffset(x = with(LocalDensity.current) { offset.x.toDp() } - 10.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun LineChartCardPreview() {
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

    LineChartCard(
        data = data,
        labels = labels,
        totalTime = "2h 30min"
    )
}