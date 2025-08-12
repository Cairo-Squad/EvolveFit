package com.cairosquad.evolvefit.viewmodel.report

data class ReportScreenState(
    val report: ReportUiState = ReportUiState(),
    val workoutHistory: List<WorkoutHistory> = emptyList(),
    val isDropDownMenuOpen: Boolean = false,

) {

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
        val day: List<String> = emptyList()
    )

    data class TimeSpentPerWeek(
        val timeInMilliSeconds: List<Long> = emptyList(),
        val day: List<String> = emptyList()
    )

    data class TrainedMuscle(
        val muscle: List<String> = emptyList(),
        val percentage: List<Float> = emptyList()
    )

    data class WorkoutHistory(
        val imageUrl: String = "",
        val name: String = "",
        val date: String = "",
        val exercisesCount: Int = 0,
        val durationInSeconds: Int = 0,
        val level: String = ""
    )
}
