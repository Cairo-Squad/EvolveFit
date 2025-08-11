package com.cairosquad.evolvefit.viewmodel.report

data class ReportScreenState(
    val waterConsumed: Float = 0f,
    val timeSpend: String = "",
    val caloriesTaken: Int = 0,
    val expectedCalories: Int = 100,
    val totalWorkouts: String = "",
    val workoutPerWeek: WorkoutPerDay = WorkoutPerDay(),
    val timeSpendPerWeek: TimeSpendPerDay = TimeSpendPerDay(),
    val mostTrainedMuscles: TrainedMuscle = TrainedMuscle(),
    val workoutHistory: List<WorkoutHistory> = emptyList(),
    val isDropDownMenuOpen: Boolean = false,

) {
    data class WorkoutPerDay(
        val workoutsCount: List<Int> = emptyList(),
        val day: List<String> = emptyList()
    )

    data class TimeSpendPerDay(
        val time: List<Long> = emptyList(),
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
        val duration: Long = 0,
        val level: String = ""
    )
}
