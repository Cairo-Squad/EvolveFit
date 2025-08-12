package com.cairosquad.evolvefit.viewmodel.register

import com.cairosquad.evolvefit.viewmodel.onboarding.models.UiImage
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.friday
import evolvefit.composeapp.generated.resources.monday
import evolvefit.composeapp.generated.resources.saturday
import evolvefit.composeapp.generated.resources.sunday
import evolvefit.composeapp.generated.resources.thursday
import evolvefit.composeapp.generated.resources.tuesday
import evolvefit.composeapp.generated.resources.wednesday
import org.jetbrains.compose.resources.StringResource

data class RegisterScreenState(
    val currentStep: Int = 1,
    val selectedHeight: Float = 0F,
    val selectedWeight: Float = 0F,
    val userNameInput: String = "",
    val userEmailInput: String = "",
    val userPasswordInput: String = "",
    val dateOfBirthInput: String = "",
    val isNextButtonEnabled: Boolean = false,
    val selectedGender: Gender? = null,
    val selectedMeasurementStandard: MeasurementStandard? = null,
    val selectedGoal: Goal? = null,
    val notificationSettings: NotificationSettings = NotificationSettings(),
    val selectedWeekDayUiState: Set<WeekDayUiState> = emptySet(),
    val isNoEquipmentSelected: Boolean = false,
    val availableEquipments: List<EquipmentUiState> = emptyList(),
    val selectedEquipments: Set<Int> = emptySet(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val image: UiImage = UiImage.ImageUrl(""),
    val preferredLanguage: Language? = null,
    val isImagePickerOpen: Boolean = false,
    val isPasswordVisible: Boolean = false,
) {

    enum class Gender {
        Female, Male
    }

    enum class MeasurementStandard {
        Metric,
        Imperial
    }

    enum class Goal {
        LoseWeight, GainWeight, StayInShape
    }

    enum class Language {
        ARABIC,
        ENGLISH
    }

    enum class WeekDayUiState(val resId: StringResource) {
        SUNDAY(Res.string.sunday),
        MONDAY(Res.string.monday),
        TUESDAY(Res.string.tuesday),
        WEDNESDAY(Res.string.wednesday),
        THURSDAY(Res.string.thursday),
        FRIDAY(Res.string.friday),
        SATURDAY(Res.string.saturday);
    }

    data class EquipmentUiState(
        val toolName: String = "",
        val toolId: Int = 0,
        val isSelected: Boolean = false
    )


    data class NotificationSettings(
        val isWaterReminderEnabled: Boolean = false,
        val isWorkoutReminderEnabled: Boolean = false,
        val isBodyWeightReminderEnabled: Boolean = false,
        val isChallengesReminderEnabled: Boolean = false,
    )

    sealed class NotificationType {
        object Workout : NotificationType()
        object Water : NotificationType()
        object BodyWeight : NotificationType()
        object Challenges : NotificationType()
    }
}
