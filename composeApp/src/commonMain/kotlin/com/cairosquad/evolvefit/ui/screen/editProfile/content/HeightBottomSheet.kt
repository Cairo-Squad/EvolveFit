package com.cairosquad.evolvefit.ui.screen.editProfile.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.BottomSheet
import com.cairosquad.evolvefit.design_system.component.PrimaryButton
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.domain.model.MeasurementStandard
import com.cairosquad.evolvefit.ui.screen.register.content.HeightWeightConstants
import com.cairosquad.evolvefit.ui.screen.register.content.MeasureSection
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.cm
import evolvefit.composeapp.generated.resources.confirm
import evolvefit.composeapp.generated.resources.ft
import evolvefit.composeapp.generated.resources.ic_ruler
import evolvefit.composeapp.generated.resources.your_height
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun HeightBottomSheet(
    selectedHeight: Float,
    measurementStandard: MeasurementStandard,
    isHeightBottomSheetOpen: Boolean = true,
    onHeightBottomSheetDismiss: () -> Unit,
    onHeightChange: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    BottomSheet(
        isVisible = isHeightBottomSheetOpen,
        onDismiss = onHeightBottomSheetDismiss,
        modifier = modifier.fillMaxWidth()
    ) {
        HeightBottomSheetContent(
            initialHeight = selectedHeight,
            measurementStandard = measurementStandard,
            onHeightChange = onHeightChange,
            onHeightBottomSheetDismiss = onHeightBottomSheetDismiss,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp)
        )
    }
}

@Composable
fun HeightBottomSheetContent(
    initialHeight: Float,
    measurementStandard: MeasurementStandard,
    onHeightChange: (Float) -> Unit,
    onHeightBottomSheetDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedHeight by remember { mutableStateOf(initialHeight) }

    val (heightMeasureUnit, minHeight, maxHeight, dpPerHeightUnit, heightStep) =
        if (measurementStandard == MeasurementStandard.METRIC) {
            HeightMeasurementData(
                unit = stringResource(Res.string.cm),
                minValue = HeightWeightConstants.MIN_HEIGHT_CM,
                maxValue = HeightWeightConstants.MAX_HEIGHT_CM,
                dpPerUnit = HeightWeightConstants.DP_PER_CM,
                step = HeightWeightConstants.HEIGHT_STEP_CM
            )
        } else {
            HeightMeasurementData(
                unit = stringResource(Res.string.ft),
                minValue = HeightWeightConstants.MIN_HEIGHT_FT,
                maxValue = HeightWeightConstants.MAX_HEIGHT_FT,
                dpPerUnit = HeightWeightConstants.DP_PER_FT,
                step = HeightWeightConstants.HEIGHT_STEP_FT
            )
        }

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        MeasureSection(
            selectedMeasure = selectedHeight,
            measureType = stringResource(Res.string.your_height),
            measureIcon = painterResource(Res.drawable.ic_ruler),
            minMeasureValue = minHeight,
            maxMeasureValue = maxHeight,
            onMeasureChanged = { selectedHeight = it },
            measureUnit = heightMeasureUnit,
            dpPerUnit = dpPerHeightUnit,
            step = heightStep,
            modifier = Modifier.padding(bottom = 40.dp),
            isDescriptionFound = false,
            textStyle = Theme.textStyle.label.mediumMedium16,
            bottomPadding = 16.dp,
            startPadding = 0.dp,
        )

        PrimaryButton(
            text = stringResource(Res.string.confirm),
            onClick = {
                onHeightChange(selectedHeight)
                onHeightBottomSheetDismiss()
            }
        )
    }
}


private data class HeightMeasurementData(
    val unit: String,
    val minValue: Float,
    val maxValue: Float,
    val dpPerUnit: Float,
    val step: Float
)
