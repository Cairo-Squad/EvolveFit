package com.cairosquad.evolvefit.viewmodel.community_workout

import com.cairosquad.evolvefit.viewmodel.workout.WorkoutScreenState

interface CommunityWorkoutInteractionListener {
    fun onFocusAreaSelected(focusArea: WorkoutScreenState.FocusAreaUiState)
    fun onWorkoutClicked(id: String)
    fun onBackClicked()
    fun getCommunityWorkout()
    fun onRefresh()
    fun onRetryClicked()
}