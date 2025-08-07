package com.cairosquad.evolvefit.viewmodel.workouts

interface WorkoutsInteractionListener {
    fun onBodyPartSelected(bodyPart: String)
    fun onWorkoutClicked(id: Long)
    fun onAddWorkoutClicked()
    fun onCommunityClicked()
}