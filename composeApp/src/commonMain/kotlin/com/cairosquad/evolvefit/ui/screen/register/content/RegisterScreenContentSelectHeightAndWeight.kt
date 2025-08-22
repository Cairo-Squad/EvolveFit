package com.cairosquad.evolvefit.ui.screen.register.content

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.viewmodel.onboarding.models.UiImage
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
            .padding(top = 16.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        val measureUnit = state.selectedMeasurementStandard
        var heightMeasureUnit = ""
        var weightMeasureUnit = ""
        var minHeight = 0f
        var maxHeight = 0f
        var dpPerHeightUnit = .1f
        var heightStep = 5f
        var minWeight = 0f
        var maxWeight = 0f
        var dpPerWeightUnit = .1f
        var weightStep = 5f

        if (measureUnit == RegisterScreenState.MeasurementStandard.Metric) {
            heightMeasureUnit = stringResource(Res.string.cm)
            weightMeasureUnit = stringResource(Res.string.kg)

            minHeight = HeightWeightConstants.MIN_HEIGHT_CM
            maxHeight = HeightWeightConstants.MAX_HEIGHT_CM
            minWeight = HeightWeightConstants.MIN_WEIGHT_KG
            maxWeight = HeightWeightConstants.MAX_WEIGHT_KG
            dpPerHeightUnit = HeightWeightConstants.DP_PER_CM
            dpPerWeightUnit = HeightWeightConstants.DP_PER_KG
            heightStep = HeightWeightConstants.HEIGHT_STEP_CM
            weightStep = HeightWeightConstants.WEIGHT_STEP_KG
        } else {
            heightMeasureUnit = stringResource(Res.string.ft)
            weightMeasureUnit = stringResource(Res.string.lb)

            minHeight = HeightWeightConstants.MIN_HEIGHT_FT
            maxHeight = HeightWeightConstants.MAX_HEIGHT_FT
            minWeight = HeightWeightConstants.MIN_WEIGHT_LB
            maxWeight = HeightWeightConstants.MAX_WEIGHT_LB
            dpPerHeightUnit = HeightWeightConstants.DP_PER_FT
            dpPerWeightUnit = HeightWeightConstants.DP_PER_LB
            heightStep = HeightWeightConstants.HEIGHT_STEP_FT
            weightStep = HeightWeightConstants.WEIGHT_STEP_LB
        }
        var height = state.selectedHeight
        var weight =state.selectedWeight
            if (state.selectedHeight == 0f && state.selectedWeight == 0f) {
            if (state.selectedMeasurementStandard == RegisterScreenState.MeasurementStandard.Metric) {
                height = 170f
                weight =70f

            } else {
                height = 5.6f
                weight =150f
            }
                listener.onHeightChanged(height)
                listener.onWeightChanged(weight)
        }
        MeasureSection(
            selectedMeasure = height,
            measureType = stringResource(Res.string.height),
            measureIcon = painterResource(Res.drawable.ic_ruler),
            minMeasureValue = minHeight,
            maxMeasureValue = maxHeight,
            onMeasureChanged = { height ->
                listener.onHeightChanged(height)
            },
            measureUnit = heightMeasureUnit,
            dpPerUnit = dpPerHeightUnit,
            step = heightStep
        )

        MeasureSection(
            selectedMeasure = weight,
            measureType = stringResource(Res.string.weight),
            measureIcon = painterResource(Res.drawable.ic_scale),
            minMeasureValue = minWeight,
            maxMeasureValue = maxWeight,
            onMeasureChanged = { weight ->
                listener.onWeightChanged(weight)
            },
            measureUnit = weightMeasureUnit,
            dpPerUnit = dpPerWeightUnit,
            step = weightStep
        )
    }
}

@Composable
fun MeasureSection(
    selectedMeasure: Float,
    measureType: String,
    measureIcon: Painter,
    minMeasureValue: Float,
    maxMeasureValue: Float,
    onMeasureChanged: (Float) -> Unit,
    measureUnit: String,
    dpPerUnit: Float,
    step: Float,
    modifier: Modifier = Modifier,
    isDescriptionFound: Boolean = true,
    textStyle: TextStyle = Theme.textStyle.headline.mediumMedium18,
    bottomPadding: Dp = 8.dp,
    startPadding: Dp = 16.dp,
) {

    val alignedSelectedMeasure = kotlin.math.round(selectedMeasure / step) * step

    Column(
        modifier = modifier
    ) {
        BasicText(
            text = measureType,
            style = textStyle.copy(
                color = Theme.color.surfaces.onSurface
            ),
            modifier = Modifier
                .padding(bottom = bottomPadding, start = startPadding, end = 16.dp)
        )
        if (isDescriptionFound) {
            BasicText(
                text = stringResource(Res.string.select_measurement) + measureType.replaceFirstChar { it.lowercase() },
                style = Theme.textStyle.label.smallRegular14.copy(
                    color = Theme.color.surfaces.onSurfaceVariant
                ),
                modifier = Modifier.padding(bottom = 24.dp, start = 16.dp, end = 16.dp)
            )
        }

        Ruler(
            selectedValue = alignedSelectedMeasure,
            minValue = minMeasureValue,
            maxValue = maxMeasureValue,
            dpPerUnit = dpPerUnit,
            step = step,
            onValueChanged = { newValue ->

                val alignedNewValue = kotlin.math.round(newValue / step) * step
                onMeasureChanged(alignedNewValue)
            }
        )

        MeasurementCard(
            selectedMeasure = alignedSelectedMeasure,
            measureUnit = measureUnit,
            measureIcon = measureIcon,
            modifier = Modifier
                .padding(top = 24.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun MeasurementCard(
    selectedMeasure: Float,
    measureUnit: String,
    measureIcon: Painter,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .background(
                Theme.color.surfaces.surfaceContainer,
                RoundedCornerShape(8.dp)
            )
            .width(116.dp)
            .height(48.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = measureIcon,
            contentDescription = null,
            tint = Theme.color.surfaces.onSurfaceVariant,
            modifier = Modifier
                .size(20.dp)
        )
        BasicText(
            text = formatToOneDecimal(selectedMeasure) + " " + measureUnit,
            style = Theme.textStyle.label.smallRegular14.copy(
                color = Theme.color.surfaces.onSurfaceVariant
            )
        )
    }
}

@OptIn(ExperimentalTextApi::class)
@Composable
fun Ruler(
    modifier: Modifier = Modifier,
    selectedValue: Float,
    minValue: Float,
    maxValue: Float,
    dpPerUnit: Float,
    step: Float,
    onValueChanged: (Float) -> Unit
) {

    val initialValue = if (selectedValue == 0f) ((minValue + maxValue) / 2f) else selectedValue

    val clampedAndAlignedValue = kotlin.math.round(
        initialValue.coerceIn(minValue, maxValue) / step
    ) * step

    var currentValue by remember(clampedAndAlignedValue) {
        mutableFloatStateOf(
            clampedAndAlignedValue
        )
    }
    val density = LocalDensity.current
    val textMeasurer = rememberTextMeasurer()
    val outlineColor = Theme.color.surfaces.outline
    val textStyle = Theme.textStyle.body.smallRegular10

    val pixelsPerUnit = with(density) { dpPerUnit.dp.toPx() }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(79.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DrawIndicatorTriangle()

        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(51.dp)
                .pointerInput(Unit) {
                    detectDragGestures { _, dragAmount ->
                        val newValue = (currentValue - dragAmount.x / pixelsPerUnit)
                            .coerceIn(minValue, maxValue)
                        val alignedNewValue = kotlin.math.round(newValue / step) * step
                        currentValue = alignedNewValue
                        onValueChanged(alignedNewValue)
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
                textStyle = textStyle,
                pixelsPerUnit = pixelsPerUnit,
                step = step
            )
        }
    }
}

@OptIn(ExperimentalTextApi::class)
fun DrawScope.drawRuler(
    selectedValue: Float,
    minValue: Float,
    maxValue: Float,
    textMeasurer: TextMeasurer,
    density: androidx.compose.ui.unit.Density,
    outlineColor: Color,
    textStyle: TextStyle,
    pixelsPerUnit: Float,
    step: Float
) {

    val canvasWidth = size.width
    val centerX = canvasWidth / 2

    var startValue = selectedValue - (canvasWidth / pixelsPerUnit) / 2
    var endValue = selectedValue + (canvasWidth / pixelsPerUnit) / 2


    startValue = kotlin.math.floor(startValue / step) * step
    endValue = kotlin.math.ceil(endValue / step) * step

    var currentValue = startValue
    var index = kotlin.math.round(currentValue / step).toInt()

    while (currentValue <= endValue) {
        if (currentValue < minValue || currentValue > maxValue) {
            currentValue += step
            index++
            continue
        }

        val x = centerX + (currentValue - selectedValue) * pixelsPerUnit

        val isMediumMark = (index % 5) % 2 == 0
        val isLargeMark = index % 5 == 0

        val markHeight = with(density) {
            when {
                isLargeMark -> 38.dp.toPx()
                isMediumMark -> 25.dp.toPx()
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

        if (isLargeMark) {
            val finalTextStyle = textStyle.copy(
                color = outlineColor
            )

            val valueText = formatToOneDecimal(currentValue)

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
        index++
    }
}

@Composable
fun DrawIndicatorTriangle(
    modifier: Modifier = Modifier,
) {
    val indicatorColor = Theme.color.surfaces.onSurfaceVariant

    Canvas(
        modifier = modifier
            .width(15.dp)
            .height(12.dp)
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
}


fun formatToOneDecimal(value: Float): String {
    return (kotlin.math.round(value * 10) / 10f).toString()
}

object HeightWeightConstants {
    const val MIN_HEIGHT_CM = 50f
    const val MAX_HEIGHT_CM = 250f
    const val HEIGHT_STEP_CM = 1f
    const val DP_PER_CM = 10f

    const val MIN_HEIGHT_FT = 2f
    const val MAX_HEIGHT_FT = 8f
    const val HEIGHT_STEP_FT = 0.04f
    const val DP_PER_FT = 250f

    const val MIN_WEIGHT_KG = 30f
    const val MAX_WEIGHT_KG = 200f
    const val WEIGHT_STEP_KG = 1f
    const val DP_PER_KG = 10f

    const val MIN_WEIGHT_LB = 60f
    const val MAX_WEIGHT_LB = 400f
    const val WEIGHT_STEP_LB = 2f
    const val DP_PER_LB = 5f
}

