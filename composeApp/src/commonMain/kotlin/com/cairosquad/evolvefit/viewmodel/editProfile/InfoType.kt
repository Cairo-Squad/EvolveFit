package com.cairosquad.evolvefit.viewmodel.editProfile

import com.cairosquad.evolvefit.viewmodel.editProfile.EditProfileScreenState.ProfileUiState
import kotlinx.datetime.LocalDate

sealed class InfoType<T>(
    val updater: (ProfileUiState, T) -> ProfileUiState
) {
    object FULL_NAME : InfoType<String>({ profile, value ->
        profile.copy(fullName = value)
    })

    object GENDER : InfoType<String>({ profile, value ->
        profile.copy(gender = value)
    })

    object HEIGHT : InfoType<Float>({ profile, value ->
        profile.copy(height = value)
    })

    object WEIGHT : InfoType<Float>({ profile, value ->
        profile.copy(weight = value)
    })

    object MAIN_GOAL : InfoType<String>({ profile, value ->
        profile.copy(mainGoal = value)
    })

    object MEASUREMENT_STANDARD : InfoType<String>({ profile, value ->
        profile.copy(preferredMeasurementStandard = value)
    })

    object DATE_OF_BIRTH : InfoType<LocalDate?>({ profile, value ->
        profile.copy(dateOfBirth = value)
    })
}
