package com.cairosquad.evolvefit.ui.screen.editProfile.content

import androidx.compose.foundation.layout.Arrangement
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
import com.cairosquad.evolvefit.design_system.component.CheckboxItem
import com.cairosquad.evolvefit.design_system.component.PrimaryButton
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.domain.model.MeasurementStandard
import com.cairosquad.evolvefit.ui.screen.register.content.RegisterHeader
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.confirm
import evolvefit.composeapp.generated.resources.select_unit_title
import evolvefit.composeapp.generated.resources.unit_imperial
import evolvefit.composeapp.generated.resources.unit_metric
import org.jetbrains.compose.resources.stringResource

@Composable
fun MeasurementBottomSheet(
    selectedMeasurementStandard: MeasurementStandard,
    isBottomSheetOpen: Boolean = true,
    onDismiss: () -> Unit,
    onMeasurementChange: (MeasurementStandard) -> Unit,
    modifier: Modifier = Modifier
) {
    BottomSheet(
        isVisible = isBottomSheetOpen,
        onDismiss = onDismiss,
        modifier = modifier.fillMaxWidth()
    ) {
        MeasurementBottomSheetContent(
            selectedMeasurementStandard = selectedMeasurementStandard,
            onMeasurementChange = onMeasurementChange,
            onDismiss = onDismiss,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom= 16.dp)
        )
    }
}

@Composable
fun MeasurementBottomSheetContent(
    selectedMeasurementStandard: MeasurementStandard,
    onMeasurementChange: (MeasurementStandard) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {

    var selectedUnit by remember { mutableStateOf(selectedMeasurementStandard) }

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        RegisterHeader(
            modifier = Modifier.padding(bottom = 16.dp),
            title = stringResource(Res.string.select_unit_title),
            titleStyle = Theme.textStyle.label.mediumMedium16
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 40.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            MeasurementStandard.entries.forEach { unit ->
                val unitText = when (unit) {
                  MeasurementStandard.METRIC -> stringResource(Res.string.unit_metric)
                    MeasurementStandard.IMPERIAL -> stringResource(Res.string.unit_imperial)
                }

                CheckboxItem(
                    text = unitText,
                    isChecked = selectedUnit == unit,
                    onCheckedChange = { selectedUnit = unit }
                )
            }
        }

        PrimaryButton(
            text = stringResource(Res.string.confirm),
            onClick = {
                onMeasurementChange(selectedUnit)
                onDismiss()
            }
        )
    }
}
