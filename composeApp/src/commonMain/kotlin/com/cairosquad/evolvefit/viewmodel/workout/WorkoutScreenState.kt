package com.cairosquad.evolvefit.viewmodel.workout


data class WorkoutScreenState(
    val selectedFocusArea: FocusAreaUiState = FocusAreaUiState.CORE,
    val allWorkouts: List<WorkoutUiState> = emptyList(),
    val focusAreas: List<FocusAreaUiState> = listOf()

) {
    data class WorkoutUiState(
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