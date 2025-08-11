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
) {
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