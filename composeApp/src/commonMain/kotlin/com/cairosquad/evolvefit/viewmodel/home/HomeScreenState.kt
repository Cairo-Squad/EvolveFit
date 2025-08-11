package com.cairosquad.evolvefit.viewmodel.home

import com.cairosquad.evolvefit.viewmodel.base.ErrorState

data class HomeScreenState(
    val isLoading: Boolean = false,
    val error: ErrorState? = null,
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
        val currentWeight: Float = 0f,
        val weightUnit: String = "",
        val activityPercentage: Int = 0,
        val progressDays: Map<Int, Boolean> = emptyMap(),
    )

    data class HomeWorkoutUiState(
        val id: String = "",
        val name: String = "",
        val imageUrl: String = "",
        val durationInMins: Int = 0,
        val type: String = "",
        val isSaved: Boolean = false,
    )
}
