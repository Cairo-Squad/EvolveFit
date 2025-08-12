package com.cairosquad.evolvefit.viewmodel.editProfile

import kotlinx.datetime.LocalDate

data class EditProfileScreenState(
    val user: UserUiState = UserUiState(),
    val showBottomSheet: Boolean = false,
) {
    data class UserUiState(
        val fullName: String = "",
        val email: String = "",
        val dateOfBirth: LocalDate? = null,
        val gender: String = "",
        val height: Float = 0f,
        val weight: Float = 0f,
        val mainGoal: String = "",
        val profileImageUri: String="",
        val preferredMeasurementStandard: String ="",
    )

    enum class MeasurementStandard {
        METRIC,
        IMPERIAL
    }

}

