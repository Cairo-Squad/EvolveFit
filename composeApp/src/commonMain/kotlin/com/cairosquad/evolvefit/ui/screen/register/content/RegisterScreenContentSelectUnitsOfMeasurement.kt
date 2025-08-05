package com.cairosquad.evolvefit.ui.screen.register.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.CheckboxItem
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.viewmodel.register.RegisterInteractionListener
import com.cairosquad.evolvefit.viewmodel.register.RegisterScreenState
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.select_unit_description
import evolvefit.composeapp.generated.resources.select_unit_title
import evolvefit.composeapp.generated.resources.unit_imperial
import evolvefit.composeapp.generated.resources.unit_metric
import org.jetbrains.compose.resources.stringResource

@Composable
fun RegisterScreenContentSelectUnitsOfMeasurement(
    state: RegisterScreenState,
    listener: RegisterInteractionListener,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().background(Theme.color.surfaces.surface)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        OnboardingHeader(
            title = stringResource(Res.string.select_unit_title),
            description = stringResource(Res.string.select_unit_description)
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            RegisterScreenState.MeasurementUnit.entries.forEach { unit ->
                CheckboxItem(
                    text = when (unit) {
                        RegisterScreenState.MeasurementUnit.Metric -> stringResource(Res.string.unit_metric)
                        RegisterScreenState.MeasurementUnit.Imperial -> stringResource(Res.string.unit_imperial)
                    },
                    isChecked = state.selectedMeasurementUnit == unit,
                    onCheckedChange = { listener.onMeasurementUnitClicked(unit) },
                )
            }

        }
    }
}
