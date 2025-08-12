package com.cairosquad.evolvefit.viewmodel.workoutDetails

interface WorkoutDetailsInteractionListener {
    fun onBackClick()
    fun onShareClick()
    fun onAddToFavoriteClick()
    fun onExerciseClick(exerciseName: String)
    fun onExerciseBottomSheetDismiss()
    fun onStartWorkoutClick()
}