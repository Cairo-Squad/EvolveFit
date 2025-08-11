package com.cairosquad.evolvefit.viewmodel.playWorkout

import com.cairosquad.evolvefit.entity.Exercise
import com.cairosquad.evolvefit.entity.MeasurementType
import com.cairosquad.evolvefit.entity.Workout
import com.cairosquad.evolvefit.viewmodel.playWorkout.PlayWorkoutScreenState.ExerciseSpecUiState.Reps
import com.cairosquad.evolvefit.viewmodel.playWorkout.PlayWorkoutScreenState.ExerciseSpecUiState.Time

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
        id = id.toString(),
        name = name,
        imageUrls = listOf(imageUrl ?: ""),
        exerciseSpec = when (this.measurementType) {
            MeasurementType.DURATION -> Time(measurementValue)
            MeasurementType.REPS -> Reps(measurementValue)
        }
    )
}