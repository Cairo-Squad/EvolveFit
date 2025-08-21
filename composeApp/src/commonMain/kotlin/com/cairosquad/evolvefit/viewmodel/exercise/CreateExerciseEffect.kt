package com.cairosquad.evolvefit.viewmodel.exercise

sealed class CreateExerciseEffect {
    object NavigateToAllExercises : CreateExerciseEffect()
    object CancelCreateExercise : CreateExerciseEffect()
}