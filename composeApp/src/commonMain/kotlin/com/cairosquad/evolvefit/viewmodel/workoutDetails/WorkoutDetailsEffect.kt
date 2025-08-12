package com.cairosquad.evolvefit.viewmodel.workoutDetails

import com.cairosquad.evolvefit.viewmodel.register.RegisterEffect

sealed class WorkoutDetailsEffect {
    object NavigateToPlayWorkout: WorkoutDetailsEffect()
    object NavigateBack: WorkoutDetailsEffect()

}