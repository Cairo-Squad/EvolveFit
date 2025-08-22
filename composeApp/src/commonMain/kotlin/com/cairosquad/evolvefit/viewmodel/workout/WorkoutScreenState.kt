package com.cairosquad.evolvefit.viewmodel.workout


data class WorkoutScreenState(
    val selectedFocusArea: FocusAreaUiState = FocusAreaUiState.All,
    val allWorkouts: List<WorkoutSuggestedUiState> = emptyList(),
    val focusAreas: List<FocusAreaUiState> = FocusAreaUiState.entries,
    val screenStatus: ScreenStatus = ScreenStatus.LOADING,
    val isRefreshing: Boolean = false,
    val errorMessage: String? = null
) {
    data class WorkoutSuggestedUiState(
        val id: String,
        val title: String,
        val duration: String,
        val focusArea: FocusAreaUiState,
        val imageUrl: String
    )

    enum class FocusAreaUiState {
        All,
        BACK,
        LEGS,
        SHOULDERS,
        ARMS,
        CHEST
    }

    enum class ScreenStatus { LOADING, SUCCESS, FAIL }

}