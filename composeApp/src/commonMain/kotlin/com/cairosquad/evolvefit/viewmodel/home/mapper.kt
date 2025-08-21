package com.cairosquad.evolvefit.viewmodel.home

import com.cairosquad.evolvefit.domain.entity.Profile
import com.cairosquad.evolvefit.domain.entity.Workout
import com.cairosquad.evolvefit.domain.entity.WorkoutSuggested
import com.cairosquad.evolvefit.domain.model.MeasurementStandard
import com.cairosquad.evolvefit.domain.usecase.home.model.WeeklyProgress
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.kilogram
import evolvefit.composeapp.generated.resources.pound
import evolvefit.composeapp.generated.resources.stay_shape
import evolvefit.composeapp.generated.resources.weight_gain
import evolvefit.composeapp.generated.resources.weight_loss
import org.jetbrains.compose.resources.StringResource

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
        goal = goal.asStringRes(),
        currentWeight = weight,
        weightUnit = preferredMeasurementStandard.asUnit()
    ) ?: HomeScreenState.WeeklyProgressUiState(
        goal = goal.asStringRes(),
        currentWeight = weight,
        weightUnit = preferredMeasurementStandard.asUnit()
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

fun Profile.FitnessGoal.asStringRes(): StringResource {
    return when (this) {
        Profile.FitnessGoal.LOSE_WEIGHT -> Res.string.weight_loss
        Profile.FitnessGoal.GAIN_WEIGHT -> Res.string.weight_gain
        Profile.FitnessGoal.STAY_IN_SHAPE -> Res.string.stay_shape
    }
}

fun MeasurementStandard.asUnit(): StringResource {
    return when (this) {
        MeasurementStandard.METRIC -> Res.string.kilogram
        MeasurementStandard.IMPERIAL -> Res.string.pound
    }
}