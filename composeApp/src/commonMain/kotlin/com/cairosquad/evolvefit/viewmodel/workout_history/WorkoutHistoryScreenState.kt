package com.cairosquad.evolvefit.viewmodel.workout_history

data class WorkoutHistoryScreenState(
    val workoutHistory: List<WorkoutHistoryUiState> = emptyList(),
    val screenStatus: ScreenStatus = ScreenStatus.LOADING,
    val isRefreshing: Boolean = false
) {
    data class WorkoutHistoryUiState(
        val imageUrl: String = "",
        val name: String = "",
        val date: String = "",
        val dateGroup: String = "",
        val exercisesCount: Int = 0,
        val duration: String = "",
        val level: String = ""
    )

    enum class ScreenStatus {
        LOADING,
        SUCCESS,
        ERROR
    }
}
