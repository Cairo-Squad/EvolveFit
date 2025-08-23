package com.cairosquad.evolvefit.viewmodel.workout_details


interface WorkoutDetailsInteractionListener {
    fun onBackClicked()
    fun onShareClicked()
    fun onToggleFavoriteClicked(workoutId: String, isSaved: Boolean)
    fun onExerciseClicked(exercise: WorkoutDetailsScreenState.ExerciseUiState)
    fun onExerciseBottomSheetDismiss()
    fun onShareBottomSheetDismiss()
    fun onStartWorkoutClicked(workoutId: String)
    fun onShareWithCommunityClicked(workoutId: String)
    fun onCopyLinkClicked(workoutId: String)
}