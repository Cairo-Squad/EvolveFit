package com.cairosquad.evolvefit.viewmodel.exercise

import com.cairosquad.evolvefit.viewmodel.onboarding.models.UiImage

data class CreateExerciseState(
    val name: String = "",
    val image: UiImage? = null,
    val availableEquipments: Set<EquipmentUiState> = emptySet(),
    val selectedEquipment: EquipmentUiState = EquipmentUiState(),
    val measurementType: MeasurementType = MeasurementType.DURATION,
    val measurementInputValue: Int? = null,
    val selectedFocusAreas: Set<FocusArea> = emptySet(),
    val description: String = "",
    val isExerciseSaved: Boolean = false,
    val showExitBottomSheet: Boolean = false,
    val errorMessage: String? = null,
    val isImagePickerOpen: Boolean = false,
) {
    data class EquipmentUiState(
        val name: String = "",
        val id: Int = 0,
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