package com.cairosquad.evolvefit.viewmodel.workout

interface WorkoutInteractionListener {
    fun onSelectFocusArea(focusArea: WorkoutScreenState.FocusAreaUiState)
    fun onClickWorkout(id: String)
    fun onClickAddWorkout()
    fun onClickCommunity()
    fun onRefresh()
}