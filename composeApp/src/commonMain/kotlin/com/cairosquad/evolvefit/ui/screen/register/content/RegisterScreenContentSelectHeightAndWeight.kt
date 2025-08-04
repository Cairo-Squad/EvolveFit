import HeightWeightConstants.MAX_HEIGHT_CM
import HeightWeightConstants.MAX_HEIGHT_FT
import HeightWeightConstants.MAX_WEIGHT_KG
import HeightWeightConstants.MAX_WEIGHT_LB
import HeightWeightConstants.MIN_HEIGHT_CM
import HeightWeightConstants.MIN_HEIGHT_FT
import HeightWeightConstants.MIN_WEIGHT_KG
import HeightWeightConstants.MIN_WEIGHT_LB
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
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.viewmodel.register.RegisterInteractionListener
import com.cairosquad.evolvefit.viewmodel.register.RegisterScreenState
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.cm
import evolvefit.composeapp.generated.resources.ft
import evolvefit.composeapp.generated.resources.height
import evolvefit.composeapp.generated.resources.ic_ruler
import evolvefit.composeapp.generated.resources.ic_scale
import evolvefit.composeapp.generated.resources.kg
import evolvefit.composeapp.generated.resources.lb
import evolvefit.composeapp.generated.resources.select_measurement
import evolvefit.composeapp.generated.resources.weight
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
fun RegisterScreenContentSelectHeightAndWeight(
    state: RegisterScreenState,
    listener: RegisterInteractionListener,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
                           .padding(top=16.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        val measureUnit = state.selectedMeasurementUnit
        var heightMeasureUnit =""
        var weightMeasureUnit =""
        var minHeight = 0f
        var maxHeight = 0f
        var minWeight = 0f
        var maxWeight = 0f

        if (measureUnit == RegisterScreenState.MeasurementUnit.Metric) {
            heightMeasureUnit = stringResource(Res.string.cm)
            weightMeasureUnit = stringResource(Res.string.kg)

            minHeight = MIN_HEIGHT_CM
            maxHeight = MAX_HEIGHT_CM
            minWeight = MIN_WEIGHT_KG
            maxWeight = MAX_WEIGHT_KG
        } else {
            heightMeasureUnit = stringResource(Res.string.ft)
            weightMeasureUnit = stringResource(Res.string.lb)

            minHeight = MIN_HEIGHT_FT
            maxHeight = MAX_HEIGHT_FT
            minWeight = MIN_WEIGHT_LB
            maxWeight = MAX_WEIGHT_LB
        }
        MeasureSection(
            selectedMeasure = state.selectedHeight,
            measureType = stringResource(Res.string.height),
            measureIcon = painterResource(Res.drawable.ic_ruler),
            minMeasureValue = minHeight,
            maxMeasureValue = maxHeight,
            onMeasureChanged = { height ->
                listener.onHeightChanged(height)
            },
            measureUnit = heightMeasureUnit
        )

        MeasureSection(
            selectedMeasure = state.selectedWeight,
            measureType = stringResource(Res.string.weight),
            measureIcon = painterResource(Res.drawable.ic_scale),
            minMeasureValue = minWeight,
            maxMeasureValue =maxWeight,
            onMeasureChanged = { weight ->
                listener.onWeightChanged(weight)
            },
            measureUnit =  weightMeasureUnit
        )
    }
}

@Preview()
@Composable
private fun RegisterScreenContentSelectHeightAndWeightPreview() {
    AppTheme(
        isDarkTheme = true
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Theme.color.surfaces.surface)
        ) {
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


                    override fun onClickStartNow() {
                        println("Start now clicked")
                    }

                    override fun onGenderClicked(gender: RegisterScreenState.Gender) {
                        TODO("Not yet implemented")
                    }

                    override fun onMeasurementUnitClicked(unit: RegisterScreenState.MeasurementUnit) {
                        TODO("Not yet implemented")
                    }

                    override fun onGoalClicked(goal: RegisterScreenState.Goal) {
                        TODO("Not yet implemented")
                    }
                }
            )
        }
    }
}


@Composable
private fun MeasureSection(
    selectedMeasure: Float,
    measureType: String,
    measureIcon: Painter,
    minMeasureValue: Float,
    maxMeasureValue: Float,
    onMeasureChanged: (Float) -> Unit,
    measureUnit:String,
    modifier: Modifier=Modifier
) {
    Column {
        BasicText(
            text = measureType,
            style = Theme.textStyle.headline.mediumMedium18.copy(
                color = Theme.color.surfaces.onSurface
            ),
            modifier = Modifier
                .padding(bottom = 8.dp, start = 16.dp, end = 16.dp)
        )
        BasicText(
            text = stringResource(Res.string.select_measurement) +measureType.replaceFirstChar { it.lowercase() },
            style = Theme.textStyle.label.smallRegular14.copy(
                color = Theme.color.surfaces.onSurfaceVariant
            ),
            modifier = Modifier.padding(bottom = 24.dp, start = 16.dp, end = 16.dp)
        )

        Ruler(
            selectedValue = selectedMeasure,
            minValue = minMeasureValue,
            maxValue = maxMeasureValue,
            onValueChanged = onMeasureChanged
        )

        Spacer(modifier = Modifier.height(24.dp))

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .background(
                    Theme.color.surfaces.surfaceContainer,
                    RoundedCornerShape(8.dp)
                )
                .width(116.dp)
                .height(48.dp)
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

                    text = formatToOneDecimal(selectedMeasure)+" "+measureUnit,
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
    val initialValue = if (selectedValue in minValue..maxValue) {
        selectedValue
    } else {
        (minValue + maxValue) / 2f
    }
    var currentValue by remember(selectedValue) { mutableFloatStateOf(selectedValue) }
    val density = LocalDensity.current
    val textMeasurer = rememberTextMeasurer()
    val indicatorColor = Theme.color.surfaces.onSurfaceVariant
    val outlineColor = Theme.color.surfaces.outline
    val textStyle = Theme.textStyle.body.smallRegular10
    LaunchedEffect(Unit) {
        if (selectedValue != initialValue) {
            onValueChanged(initialValue)
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(79.dp)
    ) {

        Canvas(
            modifier = Modifier
                .width(15.dp)
                .height(12.dp)
                .align(Alignment.TopCenter)
                .offset(y = (-8).dp)
        ) {
            val path = Path().apply {
                moveTo(size.width / 2, size.height)
                lineTo(0f, 0f)
                lineTo(size.width, 0f)
                close()
            }
            drawPath(path, indicatorColor)
        }

        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(51.dp)
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
                density = density,
                outlineColor = outlineColor,
                textStyle = textStyle
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
    density: androidx.compose.ui.unit.Density,
    outlineColor: Color,
    textStyle: TextStyle
) {
    val effectiveSelectedValue = if (selectedValue in minValue..maxValue) {
        selectedValue
    } else {
        (minValue + maxValue) / 2f
    }
    val canvasWidth = size.width
    val centerX = canvasWidth / 2

    val range = maxValue - minValue
    val pixelsPerUnit = canvasWidth / (range * 0.2f)

    var startValue = effectiveSelectedValue  - (canvasWidth / pixelsPerUnit) / 2
    var endValue = effectiveSelectedValue  + (canvasWidth / pixelsPerUnit) / 2


    val step = 0.5f
    startValue = (startValue / step).toInt() * step
    endValue = (endValue / step).toInt() * step + step

    var currentValue = startValue
    while (currentValue <= endValue) {
        val i = currentValue.toInt()
        if (currentValue < minValue || currentValue > maxValue) {
            currentValue += step
            continue
        }

        val x = centerX + (i - effectiveSelectedValue ) * pixelsPerUnit
        val isMainMark = (i % 5) % 2 == 0
        val isMajorMark = i % 5 == 0

        val markHeight = with(density) {
            when {
                isMajorMark -> 38.dp.toPx()
                isMainMark -> 25.dp.toPx()
                else -> 18.dp.toPx()
            }
        }

        val strokeWidth = 2.dp.toPx()



        drawLine(
            color = outlineColor,
            start = Offset(x, 0f),
            end = Offset(x, markHeight),
            strokeWidth = strokeWidth
        )

        if (isMajorMark) {
            val finalTextStyle = textStyle.copy(
                color = outlineColor
            )

            val valueText = formatToOneDecimal(i.toFloat())

            val textLayoutResult = textMeasurer.measure(
                text = valueText,
                style = finalTextStyle
            )

            val textX = x - textLayoutResult.size.width / 2
            val clampedTextX = textX.coerceIn(
                0f,
                canvasWidth - textLayoutResult.size.width
            )

            drawText(
                textLayoutResult = textLayoutResult,
                topLeft = Offset(
                    clampedTextX,
                    markHeight + with(density) { 8.dp.toPx() }
                )
            )
        }

        currentValue += step
    }
}
fun formatToOneDecimal(value: Float): String {
    return (kotlin.math.round(value * 10) / 10f).toString()
}
private object HeightWeightConstants {
    const val MIN_HEIGHT_CM =50f
    const val MAX_HEIGHT_CM = 200f

    const val MIN_HEIGHT_FT =50f
    const val MAX_HEIGHT_FT = 200f

    const val MIN_WEIGHT_KG = 5f
    const val MAX_WEIGHT_KG = 200f

    const val MIN_WEIGHT_LB = 5f
    const val MAX_WEIGHT_LB = 250f
}
