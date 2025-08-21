package com.cairosquad.evolvefit.viewmodel.home

import com.cairosquad.evolvefit.domain.entity.Profile
import com.cairosquad.evolvefit.viewmodel.base.ErrorState
import org.jetbrains.compose.resources.StringResource

data class HomeScreenState(
    val error: ErrorState? = null,
    val screenErrorMessage: StringResource? = null,
    val isRefreshing: Boolean = false,
    val user: HomeUserUiState? = null,
    val screenStatus: ScreenStatus = ScreenStatus.LOADING,
    val weeklyProgress: WeeklyProgressUiState? = null,
    val caloriesCount: UInt = 0.toUInt(),
    val caloriesGoal: UInt = 0.toUInt(),
    val waterCount: Float = 0f,
    val waterGoal: Float = 0f,
    val nutritionVisibility: Boolean = false,
    val personalizedWorkouts: List<HomeWorkoutUiState> = emptyList()
) {
    data class HomeUserUiState(
        val name: String = "",
        val gender: Profile.Gender = Profile.Gender.MALE,
        val profilePictureUrl: String = "",
    )

    data class WeeklyProgressUiState(
        val goal: StringResource? = null,
        val currentWeight: Float = 0f,
        val weightUnit: StringResource? = null,
        val activityPercentage: UInt = 0.toUInt(),
        val progressDays: Map<Int, Boolean> = emptyMap(),
    )

    data class HomeWorkoutUiState(
        val id: String = "",
        val name: String = "",
        val imageUrl: String = "",
        val durationInMins: UInt = 0.toUInt(),
        val type: String = "",
        val isSaved: Boolean = false,
    )

    enum class ScreenStatus {
        SUCCESS, LOADING, FAIL
    }
}
