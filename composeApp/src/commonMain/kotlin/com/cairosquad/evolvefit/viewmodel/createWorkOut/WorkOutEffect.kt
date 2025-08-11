package com.cairosquad.evolvefit.viewmodel.createWorkOut

sealed interface WorkOutEffect {
    data object NavigateBack : WorkOutEffect
    data object NavigateToCreateExercise : WorkOutEffect
    data object NavigateToWorkouts : WorkOutEffect
    object NavigateToAllExercises : WorkOutEffect
}