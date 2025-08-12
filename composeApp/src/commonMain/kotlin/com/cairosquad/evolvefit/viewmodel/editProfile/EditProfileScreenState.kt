package com.cairosquad.evolvefit.viewmodel.editProfile

data class EditProfileScreenState(
    val user : UserUiState= UserUiState(),
    val showBottomSheet: Boolean = false,
) {
    data class UserUiState(
        val fullName: String = "",
        val email: String = "",
        val dateOfBirth: String = "",
        val gender: String = "",
        val height: String = "",
        val weight: String = "",
        val mainGoal: String = "",
        val tools: String = "",
        val workoutDays: List<String> = emptyList(),
        val profileImageUri: String? = null,

        )
}

