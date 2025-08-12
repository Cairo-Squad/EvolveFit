package com.cairosquad.evolvefit.viewmodel.editProfile

import com.cairosquad.evolvefit.domain.entity.Profile
import com.cairosquad.evolvefit.domain.model.Langauge
import com.cairosquad.evolvefit.domain.model.MeasurementStandard

fun Profile.toUiState() = EditProfileScreenState.UserUiState(
    fullName = name,
    email = email,
    dateOfBirth = dateOfBirth,
    gender = gender.name,
    height = height,
    weight = weight,
    mainGoal = goal.name,
    profileImageUri = imageUrl,
    preferredMeasurementStandard = preferredMeasurementStandard.name,
)
fun EditProfileScreenState.UserUiState.toDomain(existingProfile: Profile) = existingProfile.copy(
    name = fullName,
    email = email,
    dateOfBirth = dateOfBirth ?: existingProfile.dateOfBirth,
    gender = Profile.Gender.valueOf(gender),
    preferredMeasurementStandard = MeasurementStandard.valueOf(preferredMeasurementStandard),
    height = height,
    weight = weight,
    goal = Profile.FitnessGoal.valueOf(mainGoal),
    imageUrl = profileImageUri
)