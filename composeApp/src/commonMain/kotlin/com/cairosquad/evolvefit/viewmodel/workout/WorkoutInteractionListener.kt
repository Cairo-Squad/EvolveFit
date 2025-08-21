package com.cairosquad.evolvefit.viewmodel.workout

interface WorkoutInteractionListener {
    fun onSelectFocusArea(focusArea: WorkoutScreenState.FocusAreaUiState)
    fun onWorkoutClicked(id: String)
    fun onAddWorkoutClicked()
    fun onCommunityClicked()
}