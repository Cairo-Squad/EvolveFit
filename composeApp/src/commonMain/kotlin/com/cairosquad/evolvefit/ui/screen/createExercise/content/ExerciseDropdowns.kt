package com.cairosquad.evolvefit.ui.screen.createExercise.content

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.viewmodel.exercise.CreateExerciseInteractionListener
import com.cairosquad.evolvefit.viewmodel.exercise.CreateExerciseState

@Composable
fun FocusAreaDropdownRow(
    state: CreateExerciseState,
    selectedFocusArea: String,
    selectedFocusAreaTextColor: Color,
    listener: CreateExerciseInteractionListener
) {
    RowWithIcon(
        modifier = Modifier.padding(bottom = 12.dp),
        text = selectedFocusArea,
        textColor = selectedFocusAreaTextColor,
        isIconClicked = state.isFocusAreaExpanded,
        onIconClicked = listener::onFocusAreaIconClicked
    )
    AnimatedVisibility(visible = state.isFocusAreaExpanded) {
        Column {
            CustomDropdownMenu(
                items = state.focusAreaNames,
                onItemSelected = listener::onFocusAreaNameSelected,
                isChecked = state::isFocusAreaSelected
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun EquipmentDropdownRow(
    state: CreateExerciseState,
    selectedEquipmentNames: String,
    selectedEquipmentTextColor: Color,
    listener: CreateExerciseInteractionListener
) {
    RowWithIcon(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
        text = selectedEquipmentNames,
        textColor = selectedEquipmentTextColor,
        isIconClicked = state.isEquipmentExpanded,
        onIconClicked = listener::onAvailableEquipmentsIconClicked
    )
    AnimatedVisibility(visible = state.isEquipmentExpanded) {
        Column {
            CustomDropdownMenu(
                items = state.equipmentNames,
                onItemSelected = listener::onEquipmentNameSelected,
                isChecked = state::isEquipmentSelected
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
