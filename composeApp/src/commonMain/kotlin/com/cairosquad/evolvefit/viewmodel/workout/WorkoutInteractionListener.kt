package com.cairosquad.evolvefit.viewmodel.workout

interface WorkoutInteractionListener {
    fun onFocusAreaSelected(focusArea: WorkoutScreenState.FocusAreaUiState)
    fun onAddWorkoutClicked()
    fun onCommunityClicked()
    fun onRefresh()
    fun onRetryClicked()
    fun onWorkoutClicked(id: String)
}