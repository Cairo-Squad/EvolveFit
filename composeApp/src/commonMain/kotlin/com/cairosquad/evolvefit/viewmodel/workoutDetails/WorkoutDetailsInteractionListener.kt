package com.cairosquad.evolvefit.viewmodel.workoutDetails


interface WorkoutDetailsInteractionListener {
    fun onBackClick()
    fun onShareClick()
    fun onAddToFavoriteClick(workoutId: String)
    fun onExerciseClick(exercise: WorkoutDetailsScreenState.ExerciseUiState)
    fun onExerciseBottomSheetDismiss()
    fun onStartWorkoutClick(workoutId: String)
}