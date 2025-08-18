package com.cairosquad.evolvefit.ui.screen.report.componant.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.screen.report.componant.animatedMeter.AnimatedLineChart
import com.cairosquad.evolvefit.ui.screen.report.componant.animatedMeter.chartComponent.ChartGrid
import com.cairosquad.evolvefit.ui.util.TimeUtil
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.friday_short
import evolvefit.composeapp.generated.resources.monday_short
import evolvefit.composeapp.generated.resources.saturday_short
import evolvefit.composeapp.generated.resources.sunday_short
import evolvefit.composeapp.generated.resources.thursday_short
import evolvefit.composeapp.generated.resources.tuesday_short
import evolvefit.composeapp.generated.resources.wednesday_short
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun LineChartCard(
    data: List<Float>,
    labels: List<StringResource>,
    totalTime: String,
    isAnimationStarted: Boolean,
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

    ReportCard(
        modifier = modifier,
        title = "Time Spent Training",
        value = totalTime
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .height(108.dp)
                    .padding(end = 8.dp, bottom = 8.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                for (i in steps downTo 0) {
                    val stepValue = maxValue / steps * i
                    Text(
                        text = TimeUtil.formatDurationLabel(stepValue),
                        style = Theme.textStyle.body.smallRegular10,
                        color = Theme.color.surfaces.onSurfaceVariant
                    )
                }
            }
            Column {
                Box {
                    Row {
                        ChartGrid(
                            modifier = Modifier
                                .weight(1f)
                                .height(96.dp)
                                .padding(top = 6.dp),
                            gridLinesColor = gridLinesColor,
                            steps = 4
                        )
                    }
                    AnimatedLineChart(
                        modifier = Modifier
                            .height(112.dp)
                            .offset(y = (-6).dp),
                        data = data,
                        lineColor = lineColor,
                        areaColor = areaColor,
                        markerColor = markerColor,
                        isAnimationStarted = isAnimationStarted,
                        onPointCentersCalculated = { barCenters = it }
                    )
                }
                Box(
                    modifier = Modifier
                        .padding(top = 6.dp)
                        .fillMaxWidth()
                ) {
                    barCenters.forEachIndexed { index, offset ->
                        val isMax = index == maxIndex
                        Text(
                            text = stringResource(labels[index]),
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
    val data by remember { mutableStateOf(listOf(7f, 10f, 10f, 35f, 40f, 5f, 9f)) }
    val labels by remember {
        mutableStateOf(
            listOf(
                Res.string.saturday_short,
                Res.string.sunday_short,
                Res.string.monday_short,
                Res.string.tuesday_short,
                Res.string.wednesday_short,
                Res.string.thursday_short,
                Res.string.friday_short
            )
        )
    }

    LineChartCard(
        data = data,
        labels = labels,
        totalTime = "2h 30min",
        true
    )
}