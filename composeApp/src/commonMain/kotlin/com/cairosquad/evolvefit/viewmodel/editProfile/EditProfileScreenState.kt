package com.cairosquad.evolvefit.viewmodel.editProfile

import kotlinx.datetime.LocalDate

data class EditProfileScreenState(
    val profile: ProfileUiState = ProfileUiState(),
    val bottomSheetType: EditProfileBottomSheetType? = null,
    val errorMessage: String? = null,
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
        FULL_NAME,
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
    enum class WeekDayUi {
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY
    }


}

