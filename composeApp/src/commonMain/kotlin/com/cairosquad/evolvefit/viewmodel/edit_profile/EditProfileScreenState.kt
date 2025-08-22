package com.cairosquad.evolvefit.viewmodel.edit_profile

import com.cairosquad.evolvefit.domain.entity.Profile
import com.cairosquad.evolvefit.domain.entity.Profile.Gender
import com.cairosquad.evolvefit.domain.model.Language
import com.cairosquad.evolvefit.domain.model.MeasurementStandard
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
        val gender:Gender= Gender.FEMALE,
        val height: Float = 0f,
        val weight: Float = 0f,
        val mainGoal: Profile.FitnessGoal = Profile.FitnessGoal.GAIN_WEIGHT,
        val imageUrl: String="",
        val preferredMeasurementStandard: MeasurementStandard=MeasurementStandard.METRIC,
        val preferredLanguage : Language= Language.ENGLISH,
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


}