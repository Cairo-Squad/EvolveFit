package com.cairosquad.evolvefit.ui.screen.nutrition.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.BottomSheet
import com.cairosquad.evolvefit.design_system.component.PrimaryButton
import com.cairosquad.evolvefit.design_system.composables.InputField
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.viewmodel.nutrition.NutritionInteractionListener
import com.cairosquad.evolvefit.viewmodel.nutrition.NutritionScreenState
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.add_button
import evolvefit.composeapp.generated.resources.enter_water_intake
import evolvefit.composeapp.generated.resources.ic_water_drop
import evolvefit.composeapp.generated.resources.track_water_intake
import org.jetbrains.compose.resources.stringResource


@Composable
fun AddWaterIntakeBottomSheet(
    state: NutritionScreenState,
    listener: NutritionInteractionListener,
    modifier: Modifier = Modifier
) {
    BottomSheet(
        isVisible = state.isAddWaterSheetVisible,
        onDismiss = { listener.onDismissWaterClicked() }) {
        Column(
            modifier = modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(Res.string.enter_water_intake),
                style = Theme.textStyle.title.largeBold14,
                color = Theme.color.surfaces.onSurfaceContainer
            )
            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = stringResource(Res.string.track_water_intake),
                style = Theme.textStyle.body.mediumMedium12,
                color = Theme.color.surfaces.outline
            )
            InputField(
                modifier = Modifier.padding(top = 16.dp),
                value = state.consumedWaterInput,
                onValueChange = listener::onWaterAmountChange,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                placeholder = "e.g., 1.5 L",
                leadingIcon = Res.drawable.ic_water_drop,
                isErrorMessageShown = true,
                error = state.waterInputError.let { it?.let { resource -> stringResource(resource) } }
                    ?: ""
            )
            PrimaryButton(
                modifier = Modifier.padding(top = 40.dp, bottom = 16.dp),
                text = stringResource(Res.string.add_button),
                isEnabled = state.isAddButtonEnabled,
                onClick = {
                    listener.onConfirmAddWaterClicked(state.consumedWaterInput)
                })
        }
    }

}