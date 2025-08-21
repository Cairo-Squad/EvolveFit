package com.cairosquad.evolvefit.viewmodel.workout


data class WorkoutScreenState(
    val selectedFocusArea: FocusAreaUiState = FocusAreaUiState.CORE,
    val allWorkouts: List<WorkoutSuggestedUiState> = emptyList(),
    val focusAreas: List<FocusAreaUiState> = FocusAreaUiState.entries,
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
        CORE,
        BACK,
        LEGS,
        SHOULDERS,
        ARMS,
        CHEST
    }
}