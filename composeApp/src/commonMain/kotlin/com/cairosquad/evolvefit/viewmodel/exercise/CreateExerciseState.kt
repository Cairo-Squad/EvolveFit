package com.cairosquad.evolvefit.viewmodel.exercise

import com.cairosquad.evolvefit.viewmodel.onboarding.models.UiImage

data class CreateExerciseState(
    val name: String = "",
    val image: UiImage? = null,
    val availableEquipments: List<Equipment> = emptyList(),
    val selectedEquipments: List<Long> = emptyList(),
    val measurementType: MeasurementType = MeasurementType.DURATION,
    val measurementInputValue: Int? = null,
    val selectedFocusAreas: List<FocusArea> = emptyList(),
    val description: String = "",
    val isExerciseSaved: Boolean = false,
    val showExitBottomSheet: Boolean = false,
    val errorMessage: String? = null,
    val isImagePickerOpen: Boolean = false,
    val isDurationChecked: Boolean = false,
    val isRepsChecked: Boolean = false,
    val isFocusAreaExpanded: Boolean = false,
    val isEquipmentExpanded: Boolean = false,
) {

    val equipmentNames: List<String>
        get() = availableEquipments.map { it.toolName }

    val selectedEquipmentNames: String
        get() = availableEquipments
            .filter { it.toolId in selectedEquipments }
            .joinToString { it.toolName }


    fun isEquipmentSelected(toolName: String): Boolean {
        val toolId = availableEquipments.firstOrNull { it.toolName == toolName }?.toolId
        return toolId != null && toolId in selectedEquipments
    }

    val focusAreaNames: List<String>
        get() = FocusArea.entries.map { it.name }

    val selectedFocusAreasText: String
        get() = selectedFocusAreas.joinToString { it.name }


    fun isFocusAreaSelected(name: String): Boolean {
        return selectedFocusAreas.any { it.name == name }
    }

    data class Equipment(
        val toolName: String = "",
        val toolId: Long,
        val isSelected: Boolean = false
    )

    enum class FocusArea {
        Quadriceps,
        Abs,
        Calves,
        LowerBack,
        Core,
        Shoulders
    }

    enum class MeasurementType {
        DURATION, REPS
    }
}