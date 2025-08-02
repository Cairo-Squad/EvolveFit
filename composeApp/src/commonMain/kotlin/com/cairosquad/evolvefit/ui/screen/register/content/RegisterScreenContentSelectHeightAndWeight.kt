import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cairosquad.evolvefit.design_system.component.PrimaryButton
import com.cairosquad.evolvefit.design_system.component.appbar.IndicatorBar
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.viewmodel.register.RegisterInteractionListener
import com.cairosquad.evolvefit.viewmodel.register.RegisterScreenState
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.height
import evolvefit.composeapp.generated.resources.ic_ruler
import evolvefit.composeapp.generated.resources.ic_scale
import evolvefit.composeapp.generated.resources.next
import evolvefit.composeapp.generated.resources.select_measurement
import evolvefit.composeapp.generated.resources.weight
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.math.abs

@Composable
fun RegisterScreenContentSelectHeightAndWeight(
    state: RegisterScreenState,
    listener: RegisterInteractionListener,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF1A1A1A))
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Column {

            measureSection(
                selectedMeasure = state.selectedHeight,
                measureType = stringResource(Res.string.height),
                measureIcon = painterResource(Res.drawable.ic_ruler),
                minMeasureValue = 50F,
                maxMeasureVlaue = 200F,
                onMeasureChanged = { height ->
                    listener.onHeightChanged(height)
                }
            )

            Spacer(modifier = Modifier.height(32.dp))

            measureSection(
                selectedMeasure = state.selectedWeight,
                measureType = stringResource(Res.string.weight),
                measureIcon = painterResource(Res.drawable.ic_scale),
                minMeasureValue = 1F,
                maxMeasureVlaue = 200F,
                onMeasureChanged = { weight ->
                    listener.onWeightChanged(weight)
                }
            )

        }

    }
}

@Preview()
@Composable
fun RegisterScreenContentSelectHeightAndWeightPreview() {
    AppTheme {
        RegisterScreenContentSelectHeightAndWeight(
            state = RegisterScreenState(
                selectedHeight = 156.0f,
                selectedWeight = 63.0f
            ),
            listener = object : RegisterInteractionListener {
                override fun onHeightChanged(height: Float) {
                    println("Height changed to: $height")
                }

                override fun onWeightChanged(weight: Float) {
                    println("Weight changed to: $weight")
                }

                override fun onClickNext() {
                    println("Next button clicked")
                }

                override fun onClickBack() {
                    println("Back button clicked")
                }

                override fun onSelectStep(step: Int) {
                    println("Step selected: $step")
                }

                override fun onClickStartNow() {
                    println("Start now clicked")
                }
            }
        )
    }
}


@Composable
private fun measureSection(
    selectedMeasure: Float,
    measureType: String,
    measureIcon: Painter,
    minMeasureValue: Float,
    maxMeasureVlaue: Float,
    onMeasureChanged: (Float) -> Unit
) {
    Column {
        BasicText(
            text = measureType,
            style = Theme.textStyle.headline.mediumMedium18.copy(
                color = Theme.color.surfaces.surface
            ),
            modifier = Modifier.padding(bottom = 8.dp, start = 16.dp)
        )
        BasicText(
            text = stringResource(Res.string.select_measurement) + "$measureType",
            style = Theme.textStyle.label.smallRegular14.copy(
                color = Theme.color.surfaces.onSurfaceVariant
            ),
            modifier = Modifier.padding(bottom = 24.dp, start = 16.dp)
        )

        Ruler(
            selectedValue = selectedMeasure,
            minValue = minMeasureValue,
            maxValue = maxMeasureVlaue,
            onValueChanged = onMeasureChanged
        )

        Spacer(modifier = Modifier.height(24.dp))

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .background(
                    Theme.color.surfaces.onSurfaceContainer,
                    RoundedCornerShape(8.dp)
                )
                .width(116.dp).height(48.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    painter = measureIcon,
                    contentDescription = null,
                    tint = Theme.color.surfaces.onSurfaceVariant,
                    modifier = Modifier
                        .size(20.dp)
                )
                BasicText(
                    text = "${selectedMeasure.toInt()}.0 cm",
                    style = Theme.textStyle.label.smallRegular14.copy(
                        color = Theme.color.surfaces.onSurfaceVariant
                    )
                )

            }
        }
    }
}


@OptIn(ExperimentalTextApi::class)
@Composable
private fun Ruler(
    modifier: Modifier = Modifier,
    selectedValue: Float,
    minValue: Float,
    maxValue: Float,
    onValueChanged: (Float) -> Unit
) {
    var currentValue by remember(selectedValue) { mutableFloatStateOf(selectedValue) }
    val density = LocalDensity.current
    val textMeasurer = rememberTextMeasurer()

    LaunchedEffect(selectedValue) {
        currentValue = selectedValue
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp)
    ) {

        Canvas(
            modifier = Modifier
                .size(16.dp)
                .align(Alignment.TopCenter)
                .offset(y = (-8).dp)
        ) {
            val path = Path().apply {
                moveTo(size.width / 2, size.height)
                lineTo(0f, 0f)
                lineTo(size.width, 0f)
                close()
            }
            drawPath(path, Color.White)
        }


        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .align(Alignment.Center)
                .pointerInput(Unit) {
                    detectDragGestures { _, dragAmount ->
                        val sensitivity = 0.1f
                        val newValue = (currentValue - dragAmount.x * sensitivity)
                            .coerceIn(minValue, maxValue)
                        currentValue = newValue
                        onValueChanged(newValue)
                    }
                }
        ) {
            drawRuler(
                selectedValue = currentValue,
                minValue = minValue,
                maxValue = maxValue,
                textMeasurer = textMeasurer,
                density = density
            )
        }
    }
}


@OptIn(ExperimentalTextApi::class)
private fun DrawScope.drawRuler(
    selectedValue: Float,
    minValue: Float,
    maxValue: Float,
    textMeasurer: TextMeasurer,
    density: androidx.compose.ui.unit.Density
) {
    val canvasWidth = size.width
    val canvasHeight = size.height
    val centerX = canvasWidth / 2

    val range = maxValue - minValue
    val pixelsPerUnit = canvasWidth / (range * 0.4f)

    val startValue = selectedValue - (canvasWidth / pixelsPerUnit) / 2
    val endValue = selectedValue + (canvasWidth / pixelsPerUnit) / 2

    for (i in startValue.toInt()..endValue.toInt()) {
        if (i < minValue || i > maxValue) continue

        val x = centerX + (i - selectedValue) * pixelsPerUnit
        val isMainMark = i % 5 == 0
        val isMajorMark = i % 10 == 0

        val markHeight = with(density) {
            when {
                isMajorMark -> 40.dp.toPx()
                isMainMark -> 25.dp.toPx()
                else -> 15.dp.toPx()
            }
        }

        val strokeWidth = if (isMajorMark) 2.dp.toPx() else 1.dp.toPx()
        val alpha = 1f - (abs(x - centerX) / (canvasWidth / 2)) * 0.7f

        drawLine(
            color = Color.White.copy(alpha = alpha.coerceAtLeast(0.3f)),
            start = Offset(x, canvasHeight - markHeight),
            end = Offset(x, canvasHeight),
            strokeWidth = strokeWidth
        )

        if (isMajorMark && abs(x - centerX) < canvasWidth / 3) {
            val textStyle = TextStyle(
                color = Color.White.copy(alpha = alpha.coerceAtLeast(0.3f)),
                fontSize = 12.sp
            )

            val textLayoutResult = textMeasurer.measure(
                text = i.toString(),
                style = textStyle
            )

            drawText(
                textLayoutResult = textLayoutResult,
                topLeft = Offset(
                    x - textLayoutResult.size.width / 2,
                    canvasHeight - markHeight - with(density) { 20.dp.toPx() }
                )
            )
        }
    }
}