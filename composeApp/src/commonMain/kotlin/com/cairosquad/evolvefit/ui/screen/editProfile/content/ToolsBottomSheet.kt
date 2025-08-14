package com.cairosquad.evolvefit.ui.screen.editProfile.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.BottomSheet
import com.cairosquad.evolvefit.design_system.component.CheckboxItem
import com.cairosquad.evolvefit.design_system.component.CheckboxStyle
import com.cairosquad.evolvefit.design_system.component.PrimaryButton
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.screen.register.content.RegisterHeader
import com.cairosquad.evolvefit.viewmodel.editProfile.EditProfileScreenState
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.no_tools_description
import evolvefit.composeapp.generated.resources.no_tools_title
import evolvefit.composeapp.generated.resources.or_select_one_or_more
import evolvefit.composeapp.generated.resources.save
import evolvefit.composeapp.generated.resources.your_tools_description
import evolvefit.composeapp.generated.resources.your_tools_title
import org.jetbrains.compose.resources.stringResource

@Composable
fun ToolsBottomSheet(
    userEquipments: Set<EditProfileScreenState.EquipmentUiState>,
    allEquipments: Set<EditProfileScreenState.EquipmentUiState>,
    isEquipmentBottomSheetOpen: Boolean = true,
    onEquipmentBottomSheetDismiss: () -> Unit,
    onEquipmentChange: (Set<EditProfileScreenState.EquipmentUiState>) -> Unit,
    modifier: Modifier = Modifier
) {
    BottomSheet(
        isVisible = isEquipmentBottomSheetOpen,
        onDismiss = onEquipmentBottomSheetDismiss,
        modifier = modifier.fillMaxWidth()
    ) {
        EquipmentBottomSheetContent(
            userEquipments = userEquipments,
            allEquipments = allEquipments,
            onEquipmentChange = onEquipmentChange,
            onEquipmentBottomSheetDismiss = onEquipmentBottomSheetDismiss
        )
    }
}

@Composable
fun EquipmentBottomSheetContent(
    userEquipments: Set<EditProfileScreenState.EquipmentUiState>,
    allEquipments: Set<EditProfileScreenState.EquipmentUiState>,
    onEquipmentChange: (Set<EditProfileScreenState.EquipmentUiState>) -> Unit,
    onEquipmentBottomSheetDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    val selectedEquipments = remember(userEquipments) { userEquipments.toMutableSet() }
    val isNoEquipmentSelected = remember(selectedEquipments) { selectedEquipments.isEmpty() }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 24.dp)
    ) {
        RegisterHeader(
            modifier = Modifier.padding(bottom = 24.dp),
            title = stringResource(Res.string.your_tools_title),
        )

        // No Equipment Option
        CheckboxItem(
            modifier = Modifier.padding(bottom = 8.dp),
            text = stringResource(Res.string.no_tools_title),
            isChecked = isNoEquipmentSelected,
            onCheckedChange = {
                if (it) {
                    selectedEquipments.clear()
                    onEquipmentChange(selectedEquipments.toSet())
                }
            },
            style = CheckboxStyle.Tick
        )

        Text(
            modifier = Modifier.padding(vertical = 16.dp),
            text = stringResource(Res.string.or_select_one_or_more),
            color = Theme.color.surfaces.onSurfaceVariant,
            style = Theme.textStyle.label.smallRegular14,
        )

        // Equipment List - Limited height for partial sheet
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f, fill = false)
                .padding(bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(allEquipments.toList()) { equipment ->
                val isSelected = selectedEquipments.contains(equipment)
                val isEnabled = !isNoEquipmentSelected

                CheckboxItem(
                    text = equipment.name,
                    isChecked = isSelected,
                    onCheckedChange = { checked ->
                        if (isEnabled) {
                            if (checked) {
                                selectedEquipments.add(equipment)
                            } else {
                                selectedEquipments.remove(equipment)
                            }
                            onEquipmentChange(selectedEquipments.toSet())
                        }
                    },
                    style = CheckboxStyle.Tick
                )
            }
        }

        PrimaryButton(
            text = stringResource(Res.string.save),
            onClick = onEquipmentBottomSheetDismiss,
            modifier = Modifier.fillMaxWidth()
        )
    }
}