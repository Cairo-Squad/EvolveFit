package com.cairosquad.evolvefit.viewmodel.home

import com.cairosquad.evolvefit.domain.entity.Profile
import com.cairosquad.evolvefit.domain.entity.Workout
import com.cairosquad.evolvefit.domain.entity.WorkoutSuggested
import com.cairosquad.evolvefit.domain.usecase.home.model.WeeklyProgress

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

fun WorkoutSuggested.toHomeWorkoutUiState(isSaved: Boolean): HomeScreenState.HomeWorkoutUiState {
    return HomeScreenState.HomeWorkoutUiState(
        id = id,
        name = name,
        imageUrl = imageUrl,
        durationInMins = convertSecondsToMinutesNoReminder(durationSeconds.toUInt()),
        type = focusArea.firstOrNull()?.name ?: "",
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

fun Profile.toWeeklyProgressUiState(weeklyProgress: HomeScreenState.WeeklyProgressUiState?): HomeScreenState.WeeklyProgressUiState {
    return weeklyProgress?.copy(
        goal = goal.name,
        currentWeight = weight,
        weightUnit = preferredMeasurementStandard.name
    ) ?: HomeScreenState.WeeklyProgressUiState(
        goal = goal.name,
        currentWeight = weight,
        weightUnit = preferredMeasurementStandard.name
    )
}

fun WeeklyProgress.toWeeklyProgressUiState(weeklyProgress: HomeScreenState.WeeklyProgressUiState?): HomeScreenState.WeeklyProgressUiState {
    return weeklyProgress?.copy(
        activityPercentage = activityPercentage.toUInt(),
        progressDays = weeklyProgressChecks
    ) ?: HomeScreenState.WeeklyProgressUiState(
        activityPercentage = activityPercentage.toUInt(),
        progressDays = weeklyProgressChecks
    )
}