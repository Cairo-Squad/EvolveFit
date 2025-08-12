package com.cairosquad.evolvefit.viewmodel.community_workout

import com.cairosquad.evolvefit.viewmodel.workout.WorkoutScreenState

interface CommunityWorkoutInteractionListener {
    fun onSelectFocusArea(focusArea: WorkoutScreenState.FocusAreaUiState)
    fun onClickWorkout(id: String)
    fun onClickBack()
    fun getCommunityWorkout()
}