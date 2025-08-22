package com.cairosquad.evolvefit.viewmodel.exercise

import com.cairosquad.evolvefit.viewmodel.onboarding.models.UiImage

data class CreateExerciseState(
    val name: String = "",
    val image1: UiImage? = null,
    val image2: UiImage? = null,
    val availableEquipments: Set<EquipmentUiState> = emptySet(),
    val selectedEquipment: EquipmentUiState = EquipmentUiState(),
    val measurementType: MeasurementType = MeasurementType.DURATION,
    val measurementInputValue: String = "",
    val selectedFocusAreas: Set<FocusArea> = emptySet(),
    val description: String = "",
    val isLoading: Boolean = false,
    val isExerciseSaved: Boolean = false,
    val showExitBottomSheet: Boolean = false,
    val errorMessage: String? = null,
    val isImage1PickerOpen: Boolean = false,
    val isImage2PickerOpen: Boolean = false,
    val isDurationChecked: Boolean = false,
    val isRepsChecked: Boolean = false,
    val isFocusAreaExpanded: Boolean = false,
    val isEquipmentExpanded: Boolean = false,
    val exerciseId : String? = null,
) {

    val equipmentNames: List<String>
        get() = availableEquipments.map { it.name }

    val selectedEquipmentNames: String
        get() = selectedEquipment.name


    fun isEquipmentSelected(toolName: String): Boolean {
        return selectedEquipment.name == toolName
    }

    val focusAreaNames: List<String>
        get() = FocusArea.entries.map { it.name }

    val selectedFocusAreasText: String
        get() = selectedFocusAreas.joinToString { it.name }


    fun isFocusAreaSelected(name: String): Boolean {
        return selectedFocusAreas.any { it.name == name }
    }

    data class EquipmentUiState(
        val name: String = "",
        val id: Int = 0,
        val isSelected: Boolean = false
    )

    enum class FocusArea {
        BACK,
        LEGS,
        SHOULDERS,
        ARMS,
        CORE,
        CHEST
    }

    enum class MeasurementType {
        DURATION, REPS
    }

}