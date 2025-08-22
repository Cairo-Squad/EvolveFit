package com.cairosquad.evolvefit.ui.screen.register.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.CheckboxItem
import com.cairosquad.evolvefit.design_system.component.CheckboxStyle
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.viewmodel.register.RegisterInteractionListener
import com.cairosquad.evolvefit.viewmodel.register.RegisterScreenState
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.no_tools_description
import evolvefit.composeapp.generated.resources.no_tools_title
import evolvefit.composeapp.generated.resources.or_select_one_or_more
import evolvefit.composeapp.generated.resources.your_tools_description
import evolvefit.composeapp.generated.resources.your_tools_title
import org.jetbrains.compose.resources.stringResource

@Composable
fun RegisterScreenContentSelectYourTools(
    state: RegisterScreenState,
    listener: RegisterInteractionListener,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(top = 16.dp)
            .padding(horizontal = 16.dp)
            .fillMaxSize()
    ) {
        RegisterHeader(
            title = stringResource(Res.string.your_tools_title),
            description = stringResource(Res.string.your_tools_description)
        )
        CheckboxItem(
            modifier = Modifier.padding(top = 16.dp),
            text = stringResource(Res.string.no_tools_title),
            description = stringResource(Res.string.no_tools_description),
            isChecked = state.isNoEquipmentSelected,
            onCheckedChange = { listener.onNoEquipmentSelected() },
            style = CheckboxStyle.Tick
        )

        Text(
            modifier = Modifier.padding(top = 24.dp, bottom = 12.dp),
            text = stringResource(Res.string.or_select_one_or_more),
            color = Theme.color.surfaces.onSurfaceVariant,
            style = Theme.textStyle.label.smallRegular14,
        )
        EquipmentsSection(
            modifier = modifier.fillMaxSize(),
            listener = listener,
            state = state,
        )

    }
}

@Composable
fun EquipmentsSection(
    state: RegisterScreenState,
    listener: RegisterInteractionListener,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(bottom = 12.dp)
    ) {
        items(state.availableEquipments.toList()) { equipment ->
            val isSelected = state.selectedEquipments.contains(equipment.toolId)
            val isEnabled = !state.isNoEquipmentSelected

            CheckboxItem(
                text = equipment.toolName,
                isChecked = isSelected,
                onCheckedChange = {
                    if (isEnabled) {
                        listener.onEquipmentToggled(equipment.toolId)
                    }
                },
                style = CheckboxStyle.Tick
            )
        }
    }
}
