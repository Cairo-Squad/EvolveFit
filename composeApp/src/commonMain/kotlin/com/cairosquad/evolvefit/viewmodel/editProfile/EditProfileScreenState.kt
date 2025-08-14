package com.cairosquad.evolvefit.viewmodel.editProfile

import kotlinx.datetime.LocalDate

data class EditProfileScreenState(
    val profile: ProfileUiState = ProfileUiState(),
    val bottomSheetType: EditProfileBottomSheetType? = null,
    val errorMessage: String? = null,
    val userEquipments: Set<EquipmentUiState> = emptySet(),
    val allEquipments: Set<EquipmentUiState> = emptySet(),
    val userWorkoutsDays: Set<WeekDayUiState> = emptySet(),
    val isImagePickerOpened : Boolean = false

) {
    data class ProfileUiState(
        val fullName: String = "",
        val email: String = "",
        val dateOfBirth: LocalDate? = null,
        val gender: String = "",
        val height: Float = 0f,
        val weight: Float = 0f,
        val mainGoal: String = "",
        val imageUrl: String="",
        val preferredMeasurementStandard: String ="",
    )
    enum class EditProfileBottomSheetType {
        WORKOUTS_DAYS,
        EQUIPMENT,
        BIRTHDAY,
        GENDER,
        HEIGHT,
        WEIGHT,
        MAIN_GOAL,
        MEASUREMENT_STANDARD
    }
    data class EquipmentUiState(
        val id: Int,
        val name: String,
    )
    enum class WeekDayUiState {
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY
    }


}