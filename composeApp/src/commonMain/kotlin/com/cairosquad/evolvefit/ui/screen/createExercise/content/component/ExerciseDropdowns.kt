package com.cairosquad.evolvefit.ui.screen.createExercise.content.component

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
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.arms
import evolvefit.composeapp.generated.resources.back_muscle
import evolvefit.composeapp.generated.resources.chest
import evolvefit.composeapp.generated.resources.focus_core
import evolvefit.composeapp.generated.resources.focus_shoulders
import evolvefit.composeapp.generated.resources.legs
import org.jetbrains.compose.resources.stringResource

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
                isChecked = state::isFocusAreaSelected,
                labelProvider = { it.toDisplayName() }
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
                isChecked = state::isEquipmentSelected,
                labelProvider = { it }
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun CreateExerciseState.FocusArea.toDisplayName(): String {
    return when (this) {
        CreateExerciseState.FocusArea.BACK -> stringResource(Res.string.back_muscle)
        CreateExerciseState.FocusArea.LEGS -> stringResource(Res.string.legs)
        CreateExerciseState.FocusArea.SHOULDERS -> stringResource(Res.string.focus_shoulders)
        CreateExerciseState.FocusArea.ARMS -> stringResource(Res.string.arms)
        CreateExerciseState.FocusArea.CORE -> stringResource(Res.string.focus_core)
        CreateExerciseState.FocusArea.CHEST -> stringResource(Res.string.chest)
    }
}
