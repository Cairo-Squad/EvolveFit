package com.cairosquad.evolvefit.viewmodel.report

import com.cairosquad.evolvefit.domain.entity.Report

fun Report.toUiState() = ReportScreenState.ReportUiState(
    waterConsumed = waterTakenInLiter,
    timeSpent = formatDuration(timeSpentInMilliSeconds),
    takenCaloriesInKcal = takenCaloriesInKcal,
    expectedCalories = expectedCalories,
    totalWorkouts = totalWorkouts.toString(),
    workoutPerWeek = ReportScreenState.WorkoutPerDay(
        day = workoutsPerWeek.map { (day, _) -> day.name.take(3) },
        workoutsCount = workoutsPerWeek.map { (_, count) -> count },
    ),
    timeSpentPerWeek = ReportScreenState.TimeSpentPerDay(
        day = timeSpentPerWeek.map { (day, _) -> day.name.take(3) },
        timeInMilliSeconds = timeSpentPerWeek.map { (_, time) -> time }
    ),
    mostTrainedMuscles = ReportScreenState.TrainedMuscle(
        muscle = focusedAreas.map { (area, _) -> area.name },
        percentage = focusedAreas.map { (_, percentage) -> percentage / 100f }
    ),
)

private fun formatDuration(millis: Long): String {
    val totalSeconds = millis / 1000
    val totalMinutes = totalSeconds / 60
    val totalHours = totalMinutes / 60

    return when {
        totalHours > 0 -> "${totalHours}h"
        totalMinutes > 0 -> "${totalMinutes}min"
        else -> "${totalSeconds}s"
    }
}