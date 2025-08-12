package com.cairosquad.evolvefit.viewmodel.workout

import com.cairosquad.evolvefit.domain.entity.Workout
import com.cairosquad.evolvefit.domain.model.FocusArea

fun FocusArea.toUiState(): WorkoutScreenState.FocusAreaUiState {
    return when (this) {
        FocusArea.QUADRICEPS -> WorkoutScreenState.FocusAreaUiState.QUADRICEPS
        FocusArea.ABS -> WorkoutScreenState.FocusAreaUiState.ABS
        FocusArea.CALVES -> WorkoutScreenState.FocusAreaUiState.CALVES
        FocusArea.LOWER_BACK -> WorkoutScreenState.FocusAreaUiState.LOWER_BACK
        FocusArea.CORE -> WorkoutScreenState.FocusAreaUiState.CORE
        FocusArea.SHOULDERS -> WorkoutScreenState.FocusAreaUiState.SHOULDERS
        FocusArea.FULL_BODY -> WorkoutScreenState.FocusAreaUiState.FULL_BODY
    }
}

fun WorkoutScreenState.FocusAreaUiState.toDomain(): FocusArea {
    return when (this) {
        WorkoutScreenState.FocusAreaUiState.FULL_BODY -> FocusArea.FULL_BODY
        WorkoutScreenState.FocusAreaUiState.QUADRICEPS -> FocusArea.QUADRICEPS
        WorkoutScreenState.FocusAreaUiState.ABS -> FocusArea.ABS
        WorkoutScreenState.FocusAreaUiState.CALVES -> FocusArea.CALVES
        WorkoutScreenState.FocusAreaUiState.LOWER_BACK -> FocusArea.LOWER_BACK
        WorkoutScreenState.FocusAreaUiState.CORE -> FocusArea.CORE
        WorkoutScreenState.FocusAreaUiState.SHOULDERS -> FocusArea.SHOULDERS
    }
}

fun Workout.toUiState(): WorkoutScreenState.WorkoutUiState {
    return WorkoutScreenState.WorkoutUiState(
        id = id,
        title = name,
        duration = estimatedTimeInSeconds.toString(),
        focusArea = exercises.firstOrNull()?.focusAreas?.firstOrNull()?.toUiState()
            ?: WorkoutScreenState.FocusAreaUiState.FULL_BODY,
        imageUrl = imageUrl,
    )
}