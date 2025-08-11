package com.cairosquad.evolvefit.viewmodel.createWorkOut

import com.cairosquad.evolvefit.viewmodel.onboarding.models.UiImage

interface WorkOutInteractionListener {
    fun onNameChanged(newName: String)
    fun onGoalSelected(goal: String)
    fun onDescriptionChanged(desc: String)
    fun onImageClicked()
    fun onNextClicked()
    fun onBackClicked()
    fun onAddClicked()
    fun onSearchQueryChanged(query: String)
    fun onExerciseCheckedChanged(id: String)
    fun onAddWorkoutClicked()
    fun onImageSelected(image: UiImage)
    fun onImagePickerDismiss()
}
