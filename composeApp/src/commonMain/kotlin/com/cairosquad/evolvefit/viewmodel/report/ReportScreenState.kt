package com.cairosquad.evolvefit.viewmodel.report

import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.this_week
import org.jetbrains.compose.resources.StringResource

data class ReportScreenState(
    val report: ReportUiState = ReportUiState(),
    val workoutHistory: List<WorkoutHistoryUiState> = emptyList(),
    val isDropDownMenuOpen: Boolean = false,
    val weeks: List<WeekItem> = emptyList(),
    val selectedWeek: WeekItem = WeekItem(),

    val isRefreshing: Boolean = false
) {
    data class WeekItem(
        val key: String = "",
        val label: StringResource = Res.string.this_week
    )

    data class ReportUiState(
        val waterConsumed: Float = 0f,
        val timeSpent: String = "",
        val takenCaloriesInKcal: Int = 0,
        val expectedCalories: Int = 100,
        val totalWorkouts: String = "",
        val workoutPerWeek: WorkoutPerWeek = WorkoutPerWeek(),
        val timeSpentPerWeek: TimeSpentPerWeek = TimeSpentPerWeek(),
        val mostTrainedMuscles: TrainedMuscle = TrainedMuscle(),
    )

    data class WorkoutPerWeek(
        val workoutsCount: List<Int> = emptyList(),
        val day: List<StringResource> = emptyList()
    )

    data class TimeSpentPerWeek(
        val timeInSeconds: List<Long> = emptyList(),
        val day: List<StringResource> = emptyList()
    )

    data class TrainedMuscle(
        val muscle: List<StringResource> = emptyList(),
        val percentage: List<Float> = emptyList()
    )

    data class WorkoutHistoryUiState(
        val imageUrl: String = "",
        val name: String = "",
        val date: String = "",
        val exercisesCount: Int = 0,
        val duration: String = "",
        val level: String = ""
    )
}
