package com.cairosquad.evolvefit.viewmodel.createWorkOut

import com.cairosquad.evolvefit.domain.entity.Exercise
import com.cairosquad.evolvefit.viewmodel.onboarding.models.UiImage

interface CreateWorkOutInteractionListener {
    fun onNameChanged(newName: String)
    fun onGoalSelected(goal: String)
    fun onDescriptionChanged(desc: String)
    fun onImageClicked()
    fun onNextClicked()
    fun onBackClicked()
    fun onExitClicked()
    fun onAddClicked()
    fun onSearchQueryChanged(query: String)
    fun onExerciseCheckedChanged(exercise: CreateWorkOutScreenState.ExerciseUiState)
    fun onAddWorkoutClicked()
    fun onImageSelected(image: UiImage)
    fun onImagePickerDismiss()
    fun onExitClicked()
}
