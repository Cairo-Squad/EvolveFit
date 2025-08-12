package com.cairosquad.evolvefit.viewmodel.editProfile

import kotlinx.datetime.LocalDate

data class EditProfileScreenState(
    val profile: ProfileUiState = ProfileUiState(),
    val showBottomSheet: Boolean = false,
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

    enum class MeasurementStandard {
        METRIC,
        IMPERIAL
    }

}

