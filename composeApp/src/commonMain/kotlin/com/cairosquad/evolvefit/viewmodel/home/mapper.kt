package com.cairosquad.evolvefit.viewmodel.home

import com.cairosquad.evolvefit.domain.entity.Profile
import com.cairosquad.evolvefit.domain.entity.Workout

fun Workout.toHomeWorkoutUiState(isSaved: Boolean): HomeScreenState.HomeWorkoutUiState {
    return HomeScreenState.HomeWorkoutUiState(
        id = id,
        name = name,
        imageUrl = imageUrl,
        durationInMins = convertSecondsToMinutesNoReminder(estimatedTimeInSeconds.toUInt()),
        type = exercises.firstOrNull()?.focusAreas?.firstOrNull()?.name ?: "",
        isSaved = isSaved
    )
}

private fun convertSecondsToMinutesNoReminder(seconds: UInt): UInt {
    return (seconds / 60u)
}

fun Profile.toHomeUserUiState(): HomeScreenState.HomeUserUiState {
    return HomeScreenState.HomeUserUiState(
        name = name,
        gender = gender,
        profilePictureUrl = imageUrl
    )
}