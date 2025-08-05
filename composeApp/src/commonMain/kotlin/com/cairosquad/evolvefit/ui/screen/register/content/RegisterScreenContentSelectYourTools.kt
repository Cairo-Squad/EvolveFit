package com.cairosquad.evolvefit.ui.screen.register.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.CheckboxItem
import com.cairosquad.evolvefit.design_system.component.CheckboxStyle
import com.cairosquad.evolvefit.design_system.component.SelectableDayBox
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.viewmodel.register.RegisterInteractionListener
import com.cairosquad.evolvefit.viewmodel.register.RegisterScreenState
import org.jetbrains.compose.resources.stringResource

@Composable
fun RegisterScreenContentSelectYourTools(
    state: RegisterScreenState,
    listener: RegisterInteractionListener,
    modifier: Modifier = Modifier
){
    val equipments = remember { RegisterScreenState.Equipment.entries.toList() }
    Column(
        modifier = modifier
            .padding(top = 16.dp)
            .fillMaxSize()
    ) {
        OnboardingHeader(
            title = "Your Tools",
            description = "Select with tools you have, and we’ll tailor your custom plan to reflect this."
        )
        CheckboxItem(
            modifier = Modifier.padding(horizontal = 16.dp).padding(top = 16.dp),
            text = "No tools",
            description = "Show me bodyweight - only workouts.",
            isChecked = state.isBodyWeightReminderEnabled,
            onCheckedChange = { listener.onBodyWeightReminderToggled(enabled = !state.isBodyWeightReminderEnabled) },
            style = CheckboxStyle.Switch
        )

        Text(
            modifier = Modifier.padding(horizontal = 16.dp).padding(top = 24.dp),
            text = "Or select one or more of the  following:",
            color =Theme.color.surfaces.onSurfaceVariant,
            style = Theme.textStyle.label.smallRegular14,
        )
        LazyColumn(
            modifier = modifier
                .padding(horizontal = 16.dp)
                .padding(top = 24.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(vertical = 12.dp)
        ) {
            items(equipments) { equipment ->
                val isSelected = state.selectedEquipments.contains(equipment)
                CheckboxItem(
                    modifier = Modifier.padding(top = 8.dp),
                    text = stringResource(equipment.resId),
                    isChecked = state.isBodyWeightReminderEnabled,
                    onCheckedChange = { listener.onBodyWeightReminderToggled(enabled = !state.isBodyWeightReminderEnabled) },
                    style = CheckboxStyle.Switch
                )
            }
        }
    }
}