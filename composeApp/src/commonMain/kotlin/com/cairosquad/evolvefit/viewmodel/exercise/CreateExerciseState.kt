package com.cairosquad.evolvefit.viewmodel.exercise

import com.cairosquad.evolvefit.viewmodel.onboarding.models.UiImage

data class CreateExerciseState(
    val name: String = "",
    val frontImage: UiImage? = null,
    val backImage: UiImage? = null,
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
    val isFrontImagePickerOpen: Boolean = false,
    val isBackImagePickerOpen: Boolean = false,
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

    val focusAreaNames: List<FocusArea>
        get() = FocusArea.entries

    val selectedFocusAreasText: String
        get() = selectedFocusAreas.joinToString { it.key }

    fun isFocusAreaSelected(focusArea: FocusArea): Boolean {
        return selectedFocusAreas.contains(focusArea)
    }

    data class EquipmentUiState(
        val name: String = "",
        val id: Int = 0,
        val isSelected: Boolean = false
    )

    enum class FocusArea(val key: String) {
        BACK("back"),
        LEGS("legs"),
        SHOULDERS("shoulders"),
        ARMS("arms"),
        CORE("core"),
        CHEST("chest")
    }


    enum class MeasurementType {
        DURATION, REPS
    }

}