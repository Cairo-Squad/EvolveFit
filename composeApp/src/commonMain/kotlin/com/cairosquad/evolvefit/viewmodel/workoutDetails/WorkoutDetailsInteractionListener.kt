package com.cairosquad.evolvefit.viewmodel.workoutDetails


interface WorkoutDetailsInteractionListener {
    fun onClickBack()
    fun onClickShare()
    fun onClickAddToFavorite(workoutId: String)
    fun onClickExercise(exercise: WorkoutDetailsScreenState.ExerciseUiState)
    fun onExerciseBottomSheetDismiss()
    fun onClickStartWorkout(workoutId: String)
    fun onClickShareWithCommunity(workoutId: String)
    fun onClickCopyLink(workoutId: String)
}