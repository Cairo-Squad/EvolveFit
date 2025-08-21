package com.cairosquad.evolvefit.viewmodel.exercise

sealed class CreateExerciseEffect {
    object NavigateToAllExercises : CreateExerciseEffect()
    data class ShowError(val message: String) : CreateExerciseEffect()
}