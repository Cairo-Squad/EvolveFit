package com.cairosquad.evolvefit.viewmodel.home

import com.cairosquad.evolvefit.viewmodel.base.ErrorState

data class HomeScreenState(
    val isLoading: Boolean = false,
    val error: ErrorState? = null,
    val user: HomeUserUiState? = null,
    val weeklyProgress: WeeklyProgressUiState? = null,
    val caloriesCount: UInt = 0.toUInt(),
    val caloriesGoal: UInt = 0.toUInt(),
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
        val activityPercentage: UInt = 0.toUInt(),
        val progressDays: Map<Int, Boolean> = emptyMap(),
    )

    data class HomeWorkoutUiState(
        val id: Long = 0,
        val name: String = "",
        val imageUrl: String = "",
        val durationInMins: UInt = 0.toUInt(),
        val type: String = "",
        val isSaved: Boolean = false,
    )
}
