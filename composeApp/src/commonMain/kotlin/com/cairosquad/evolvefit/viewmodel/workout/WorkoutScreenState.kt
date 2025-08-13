package com.cairosquad.evolvefit.viewmodel.workout


data class WorkoutScreenState(
    val selectedFocusArea: FocusAreaUiState = FocusAreaUiState.FULL_BODY,
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
        FULL_BODY,
        QUADRICEPS,
        ABS,
        CALVES,
        LOWER_BACK,
        CORE,
        SHOULDERS
    }
}