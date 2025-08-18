package com.cairosquad.evolvefit.viewmodel.report

import com.cairosquad.evolvefit.domain.entity.Report
import com.cairosquad.evolvefit.domain.entity.WorkoutHistory
import com.cairosquad.evolvefit.domain.model.FocusArea
import com.cairosquad.evolvefit.domain.model.WeekDay
import com.cairosquad.evolvefit.viewmodel.utils.formatIsoToTodayTime
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.arms
import evolvefit.composeapp.generated.resources.back_muscle
import evolvefit.composeapp.generated.resources.chest
import evolvefit.composeapp.generated.resources.core
import evolvefit.composeapp.generated.resources.friday_short
import evolvefit.composeapp.generated.resources.legs
import evolvefit.composeapp.generated.resources.monday_short
import evolvefit.composeapp.generated.resources.saturday_short
import evolvefit.composeapp.generated.resources.shoulders
import evolvefit.composeapp.generated.resources.sunday_short
import evolvefit.composeapp.generated.resources.thursday_short
import evolvefit.composeapp.generated.resources.tuesday_short
import evolvefit.composeapp.generated.resources.wednesday_short

fun Report.toUiState() = ReportScreenState.ReportUiState(
    waterConsumed = waterTakenInLiter,
    timeSpent = formatDuration(timeSpentInSeconds),
    takenCaloriesInKcal = takenCaloriesInKcal,
    expectedCalories = expectedCalories,
    totalWorkouts = totalWorkouts.toString(),
    workoutPerWeek = ReportScreenState.WorkoutPerWeek(
        day = workoutsPerWeek.map { (day, _) -> day.stringRes() },
        workoutsCount = workoutsPerWeek.map { (_, count) -> count },
    ),
    timeSpentPerWeek = ReportScreenState.TimeSpentPerWeek(
        day = timeSpentPerWeek.map { (day, _) -> day.stringRes() },
        timeInSeconds = timeSpentPerWeek.map { (_, time) -> time }
    ),
    mostTrainedMuscles = ReportScreenState.TrainedMuscle(
        muscle = focusedAreas.map { (focusArea, _) -> focusArea.stringRes() },
        percentage = focusedAreas.map { (_, percentage) -> percentage.toFloat() / 100f }
    ),
)

fun WorkoutHistory.toUiState() = ReportScreenState.WorkoutHistoryUiState(
    name = name,
    imageUrl = imageUrl,
    date = formatIsoToTodayTime(createdAt),
    exercisesCount = exercisesCount,
    duration = formatDuration(durationInSeconds),
    level = level.name,
)

private fun formatDuration(seconds: Long): String {
    val totalSeconds = seconds
    val totalMinutes = totalSeconds / 60
    val totalHours = totalMinutes / 60

    return when {
        totalHours > 0 -> "${totalHours}h"
        totalMinutes > 0 -> "${totalMinutes}min"
        else -> "${totalSeconds}s"
    }
}

private fun WeekDay.stringRes() = when (this) {
    WeekDay.MONDAY -> Res.string.monday_short
    WeekDay.TUESDAY -> Res.string.tuesday_short
    WeekDay.WEDNESDAY -> Res.string.wednesday_short
    WeekDay.THURSDAY -> Res.string.thursday_short
    WeekDay.FRIDAY -> Res.string.friday_short
    WeekDay.SATURDAY -> Res.string.saturday_short
    WeekDay.SUNDAY -> Res.string.sunday_short
}

private fun FocusArea.stringRes() = when (this) {
    FocusArea.BACK -> Res.string.back_muscle
    FocusArea.LEGS -> Res.string.legs
    FocusArea.SHOULDERS -> Res.string.shoulders
    FocusArea.ARMS -> Res.string.arms
    FocusArea.CORE -> Res.string.core
    FocusArea.CHEST -> Res.string.chest
}