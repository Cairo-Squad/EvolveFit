package com.cairosquad.evolvefit.viewmodel.workout_history

import com.cairosquad.evolvefit.domain.entity.WorkoutHistory
import com.cairosquad.evolvefit.viewmodel.utils.formatIsoToTodayTime

fun WorkoutHistory.toUiState() = WorkoutHistoryScreenState.WorkoutHistoryUiState(
    name = name,
    imageUrl = imageUrl,
    date = formatIsoToTodayTime(createdAt),
    dateGroup = extractDayGroup(formatIsoToTodayTime(createdAt)),
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

private fun extractDayGroup(formatted: String): String {
    return formatted.substringBeforeLast(",").trim()
}