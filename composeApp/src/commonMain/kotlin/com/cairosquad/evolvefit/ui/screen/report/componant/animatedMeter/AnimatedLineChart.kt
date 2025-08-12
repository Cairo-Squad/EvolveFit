package com.cairosquad.evolvefit.ui.screen.report.componant.animatedMeter


import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.screen.report.componant.animatedMeter.chartComponent.ChartCanvas
import com.cairosquad.evolvefit.ui.screen.report.componant.animatedMeter.chartComponent.Tooltip
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun AnimatedLineChart(
    data: List<Float>,
    lineColor: Color,
    areaColor: List<Color>,
    markerColor: Color,
    isAnimationStarted: Boolean,
    modifier: Modifier = Modifier,
    markerRadiusDp: Dp = 8.dp,
    onPointCentersCalculated: (List<Offset>) -> Unit = {}
) {

    val maxValue = (data.maxOrNull() ?: 1f).coerceAtLeast(1f)

    val pointsPx = remember { mutableStateListOf<Offset>() }
    var selectedIndex by remember { mutableStateOf(-1) }

    val density = LocalDensity.current
    val markerRadiusPx = with(density) { markerRadiusDp.toPx() }

    LaunchedEffect(pointsPx.toList()) {
        onPointCentersCalculated(pointsPx.toList())
    }

    BoxWithConstraints(modifier = modifier) {
        val parentWidthPx = constraints.maxWidth.toFloat()
        val parentHeightPx = constraints.maxHeight.toFloat()

        ChartCanvas(
            data = data,
            lineColor = lineColor,
            areaColor = areaColor,
            markerColor = markerColor,
            markerRadiusPx = markerRadiusPx,
            dashedLineColor = Color.White,
            maxValue = maxValue,
            isAnimationStarted = isAnimationStarted,
            onAnchorsComputed = { anchors ->
                pointsPx.clear()
                pointsPx.addAll(anchors)
            },
            onTap = { tap ->
                val thresholdPx = with(density) { 50.dp.toPx() }
                val nearest = pointsPx.withIndex().minByOrNull { (_, p) -> (p - tap).getDistance() }
                val idx = nearest?.index ?: -1
                selectedIndex =
                    if (idx != -1 && (pointsPx[idx] - tap).getDistance() <= thresholdPx) idx else -1
            }
        )

        if (selectedIndex in pointsPx.indices) {
            Tooltip(
                canvasPoint = pointsPx[selectedIndex],
                parentWidthPx = parentWidthPx,
                parentHeightPx = parentHeightPx,
                density = density
            ) {
                Text(
                    text = data[selectedIndex].toString(),
                    color = Color.White,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Preview
@Composable
private fun AnimatedLineChartPreview() {
    AnimatedLineChart(
        data = listOf(10f, 20f, 30f, 40f, 50f),
        lineColor = Theme.color.brand.primary,
        areaColor = Theme.color.gradiant.barGradiant,
        markerColor = Theme.color.brand.primary,
        true
    )
}