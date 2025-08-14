package com.cairosquad.evolvefit.ui.screen.editProfile.content
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.BottomSheet
import com.cairosquad.evolvefit.design_system.component.PrimaryButton
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.screen.register.content.HeightWeightConstants
import com.cairosquad.evolvefit.ui.screen.register.content.MeasureSection
import com.cairosquad.evolvefit.viewmodel.register.RegisterScreenState
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.confirm
import evolvefit.composeapp.generated.resources.ic_scale
import evolvefit.composeapp.generated.resources.kg
import evolvefit.composeapp.generated.resources.lb
import evolvefit.composeapp.generated.resources.save
import evolvefit.composeapp.generated.resources.unit_metric
import evolvefit.composeapp.generated.resources.weight
import evolvefit.composeapp.generated.resources.your_weight
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
@Composable
fun WeightBottomSheet(
    selectedWeight: Float,
    measurementStandard:String,
    isWeightBottomSheetOpen: Boolean=true,
    onWeightBottomSheetDismiss: () -> Unit,
    onWeightChange: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    BottomSheet(
        isVisible = isWeightBottomSheetOpen,
        onDismiss = onWeightBottomSheetDismiss,
        modifier = modifier.fillMaxWidth()
    ) {
        WeightBottomSheetContent(
            selectedWeight = selectedWeight,
            measurementStandard = measurementStandard,
            onWeightChange = onWeightChange,
            onWeightBottomSheetDismiss = onWeightBottomSheetDismiss,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom= 16.dp)
        )
    }
}

@Composable
fun WeightBottomSheetContent(
    selectedWeight: Float,
    measurementStandard: String,
    onWeightChange: (Float) -> Unit,
    onWeightBottomSheetDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    val (weightMeasureUnit, minWeight, maxWeight, dpPerWeightUnit, weightStep) =
        if (measurementStandard == stringResource(Res.string.unit_metric)) {
            WeightMeasurementData(
                unit = stringResource(Res.string.kg),
                minValue = HeightWeightConstants.MIN_WEIGHT_KG,
                maxValue = HeightWeightConstants.MAX_WEIGHT_KG,
                dpPerUnit = HeightWeightConstants.DP_PER_KG,
                step = HeightWeightConstants.WEIGHT_STEP_KG
            )
        } else {
            WeightMeasurementData(
                unit = stringResource(Res.string.lb),
                minValue = HeightWeightConstants.MIN_WEIGHT_LB,
                maxValue = HeightWeightConstants.MAX_WEIGHT_LB,
                dpPerUnit = HeightWeightConstants.DP_PER_LB,
                step = HeightWeightConstants.WEIGHT_STEP_LB
            )
        }

    Column(
        modifier = modifier.fillMaxWidth()
    ) {

        MeasureSection(
            selectedMeasure = selectedWeight,
            measureType = stringResource(Res.string.your_weight),
            measureIcon = painterResource(Res.drawable.ic_scale),
            minMeasureValue = minWeight,
            maxMeasureValue = maxWeight,
            onMeasureChanged = onWeightChange,
            measureUnit = weightMeasureUnit,
            dpPerUnit = dpPerWeightUnit,
            step = weightStep,
            modifier = Modifier.padding(bottom = 40.dp),
            isDescriptionFound = false,
            textStyle = Theme.textStyle.label.mediumMedium16,
            bottomPadding = 16.dp,
            startPadding =0.dp,
        )

        PrimaryButton(
            text = stringResource(Res.string.confirm),
            onClick = onWeightBottomSheetDismiss
        )
    }
}



private data class WeightMeasurementData(
    val unit: String,
    val minValue: Float,
    val maxValue: Float,
    val dpPerUnit: Float,
    val step: Float
)