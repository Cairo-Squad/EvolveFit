package com.cairosquad.evolvefit.viewmodel.editProfile

class EditProfileScreenState (
    val fullName : String="",
    val email : String ="",
    val dateOfBirth : String ="",
    val gender: String = "",
    val height: String = "",
    val weight: String = "",
    val mainGoal: String = "",
    val level: String = "",
    val tools: String = "",
    val workoutDays: List<String> = emptyList(),
    val profileImageUri: String? = null,
    val showBottomSheet: Boolean = false,
    )