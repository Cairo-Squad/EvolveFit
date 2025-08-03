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
import com.cairosquad.evolvefit.design_system.composables.CheckboxItem
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.screen.onBoarding.OnboardingHeader
import com.cairosquad.evolvefit.viewmodel.register.RegisterInteractionListener
import com.cairosquad.evolvefit.viewmodel.register.RegisterScreenState

@Composable
fun RegisterScreenContentSelectUnitsOfMeasurement(
    state: RegisterScreenState,
    listener: RegisterInteractionListener,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier.fillMaxSize().background(Theme.color.surfaces.surface)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        OnboardingHeader(
            title = "Choose your units of measurement",
            description = "You can change the units of measurement at any time through the app settings."
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            CheckboxItem(
                text = "Metric (cm/kg)",
                isChecked = state.isMetricChecked,
                onCheckedChange = listener::onMetricCheckedChange,
            )
            CheckboxItem(
                text = "Imperial (ft/lb)",
                isChecked = state.isImperialChecked,
                onCheckedChange = listener::onImperialCheckedChange,
            )

        }

    }
}