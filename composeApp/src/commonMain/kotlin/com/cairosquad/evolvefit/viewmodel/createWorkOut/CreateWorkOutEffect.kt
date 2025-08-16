package com.cairosquad.evolvefit.viewmodel.createWorkOut

sealed interface CreateWorkOutEffect {
    data object NavigateBack : CreateWorkOutEffect
    data object NavigateToCreateExercise : CreateWorkOutEffect
    data object NavigateToWorkouts : CreateWorkOutEffect
    object NavigateToAllExercises : CreateWorkOutEffect
}