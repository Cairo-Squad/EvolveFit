package com.cairosquad.evolvefit.viewmodel.editProfile

import com.cairosquad.evolvefit.domain.entity.Equipment
import com.cairosquad.evolvefit.domain.model.WeekDay
import kotlinx.datetime.LocalDate
import org.jetbrains.compose.resources.StringResource

data class EditProfileScreenState(
    val profile: ProfileUiState = ProfileUiState(),
    val bottomSheetType: EditProfileBottomSheetType? = null,
    val errorMessage: StringResource? = null,
    val allEquipments: Set<EquipmentUiState> = emptySet(),
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
        val preferredLanguage : String="",
        val equipments: Set<EquipmentUiState> = emptySet(),
        val workoutDays: Set<WeekDayUiState> = emptySet(),
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
    enum class MeasurementStandard {
        Metric,
        Imperial
    }

    enum class Language {
        ARABIC,
        ENGLISH
    }

}