package com.cairosquad.evolvefit.viewmodel.workout


data class WorkoutScreenState(
    val selectedFocusArea: FocusAreaUiState = FocusAreaUiState.CORE,
    val allWorkouts: List<WorkoutSuggestedUiState> = emptyList(),
    val focusAreas: List<FocusAreaUiState> = listOf()

) {
    data class WorkoutSuggestedUiState(
        val id: String,
        val title: String,
        val duration: String,
        val focusArea: FocusAreaUiState,
        val imageUrl: String
    )

    enum class FocusAreaUiState {
        BACK,
        LEGS,
        SHOULDERS,
        ARMS,
        CORE,
        CHEST
    }
}