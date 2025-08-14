package com.cairosquad.evolvefit.ui.screen.editProfile.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.BottomSheet
import com.cairosquad.evolvefit.design_system.component.CheckboxItem
import com.cairosquad.evolvefit.design_system.component.PrimaryButton
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.screen.register.content.RegisterHeader
import com.cairosquad.evolvefit.viewmodel.register.RegisterScreenState
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.select_unit_description
import evolvefit.composeapp.generated.resources.select_unit_title
import evolvefit.composeapp.generated.resources.unit_imperial
import evolvefit.composeapp.generated.resources.unit_metric
import evolvefit.composeapp.generated.resources.save
import org.jetbrains.compose.resources.stringResource

@Composable
fun MeasurementBottomSheet(
    selectedMeasurementStandard: String,
    isBottomSheetOpen: Boolean = true,
    onDismiss: () -> Unit,
    onMeasurementChange: (String) -> Unit,
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
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun MeasurementBottomSheetContent(
    selectedMeasurementStandard: String,
    onMeasurementChange: (String) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        RegisterHeader(
            modifier = Modifier.padding(bottom = 16.dp),
            title = stringResource(Res.string.select_unit_title),
            description = stringResource(Res.string.select_unit_description),
            titleStyle = Theme.textStyle.label.mediumMedium16
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 40.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            RegisterScreenState.MeasurementStandard.entries.forEach { unit ->
                CheckboxItem(
                    text = when (unit) {
                        RegisterScreenState.MeasurementStandard.Metric -> stringResource(Res.string.unit_metric)
                        RegisterScreenState.MeasurementStandard.Imperial -> stringResource(Res.string.unit_imperial)
                    },
                    isChecked = selectedMeasurementStandard == unit.name,
                    onCheckedChange = { onMeasurementChange(unit.name) }
                )
            }
        }

        PrimaryButton(
            text = stringResource(Res.string.save),
            onClick = onDismiss
        )
    }
}
