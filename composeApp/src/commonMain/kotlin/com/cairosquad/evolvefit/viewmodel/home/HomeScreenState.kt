package com.cairosquad.evolvefit.viewmodel.home

data class HomeScreenState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val user: HomeUserUiState = HomeUserUiState(),
    val weeklyProgress: WeeklyProgressUiState = WeeklyProgressUiState(),
    val caloriesCount: Int = 0,
    val caloriesGoal: Int = 0,
    val waterCount: Float = 0f,
    val waterGoal: Float = 0f,
    val personalizedWorkouts: List<HomeWorkoutUiState> = emptyList()
) {
    data class HomeUserUiState(
        val name: String = "",
        val profilePictureUrl: String = "",
    )

    data class WeeklyProgressUiState(
        val goal: String = "",
        val currentWeight: String = "",
        val weightUnit: String = "",
        val activityPercentage: Int = 0,
        val startDay: Int = 1,
        val endDay: Int = 7,
        val today: Int = 1,
    )

    data class HomeWorkoutUiState(
        val id: String = "",
        val name: String = "",
        val imageUrl: String = "",
        val durationInMins: Int = 0,
        val isSaved: Boolean = false,
    )
}
