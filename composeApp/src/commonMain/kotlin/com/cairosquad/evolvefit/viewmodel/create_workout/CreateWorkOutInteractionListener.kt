package com.cairosquad.evolvefit.viewmodel.create_workout

import com.cairosquad.evolvefit.viewmodel.create_workout.CreateWorkOutScreenState.WorkoutLevel
import com.cairosquad.evolvefit.viewmodel.onboarding.models.UiImage

interface CreateWorkOutInteractionListener {
    fun onNameChanged(newName: String)
    fun onGoalSelected(goal: WorkoutLevel)
    fun onGoalIconClicked()
    fun onDescriptionChanged(desc: String)
    fun onImageClicked()
    fun onNextClicked()
    fun onBackClicked()
    fun onExitClicked()
    fun onExitConfirmClicked()
    fun onCancelExitClicked()
    fun onAddClicked()
    fun onSearchQueryChanged(query: String)
    fun onExerciseCheckedChanged(exercise: CreateWorkOutScreenState.ExerciseUiState)
    fun onAddWorkoutClicked()
    fun onImageSelected(image: UiImage)
    fun onImagePickerDismiss()
    fun onRetryClicked()
    fun onRefresh()
}
