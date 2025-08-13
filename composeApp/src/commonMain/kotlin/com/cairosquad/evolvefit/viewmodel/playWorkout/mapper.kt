package com.cairosquad.evolvefit.viewmodel.playWorkout

import com.cairosquad.evolvefit.domain.entity.Exercise
import com.cairosquad.evolvefit.domain.entity.Workout

fun Workout.toUiState(): PlayWorkoutScreenState.WorkoutUiState {
    return PlayWorkoutScreenState.WorkoutUiState(
        id = id,
        name = name,
        imageUrl = imageUrl,
        exercises = exercises.map { it.toUiState() },
        level = PlayWorkoutScreenState.WorkoutLevelUiState.valueOf(level.name)

    )
}

fun Exercise.toUiState(): PlayWorkoutScreenState.ExerciseUiState {
    return PlayWorkoutScreenState.ExerciseUiState(
        id = id,
        name = name,
        imageUrls = imageUrls,
        exerciseSpec = when (this.specification) {
            is Exercise.Specification.Reps -> {
                PlayWorkoutScreenState.ExerciseSpecUiState.Reps(this.specification.reps)
            }
            is Exercise.Specification.Time -> {
                PlayWorkoutScreenState.ExerciseSpecUiState.Time(this.specification.timeInSeconds)
            }
        }
    )
}