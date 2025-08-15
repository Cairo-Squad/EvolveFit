package com.cairosquad.evolvefit.viewmodel.workout

import com.cairosquad.evolvefit.domain.entity.Workout
import com.cairosquad.evolvefit.domain.entity.WorkoutSuggested
import com.cairosquad.evolvefit.domain.model.FocusArea

fun FocusArea.toUiState(): WorkoutScreenState.FocusAreaUiState {
    return when (this) {
        FocusArea.SHOULDERS -> WorkoutScreenState.FocusAreaUiState.QUADRICEPS
        FocusArea.CORE -> WorkoutScreenState.FocusAreaUiState.ABS
        FocusArea.ARMS -> WorkoutScreenState.FocusAreaUiState.CALVES
        FocusArea.BACK -> WorkoutScreenState.FocusAreaUiState.LOWER_BACK
        FocusArea.CORE -> WorkoutScreenState.FocusAreaUiState.CORE
        FocusArea.SHOULDERS -> WorkoutScreenState.FocusAreaUiState.SHOULDERS
        FocusArea.CHEST -> WorkoutScreenState.FocusAreaUiState.FULL_BODY
        FocusArea.LEGS -> WorkoutScreenState.FocusAreaUiState.CORE
    }
}

fun WorkoutScreenState.FocusAreaUiState.toDomain(): FocusArea {
    return when (this) {
        WorkoutScreenState.FocusAreaUiState.FULL_BODY -> FocusArea.CHEST
        WorkoutScreenState.FocusAreaUiState.QUADRICEPS -> FocusArea.SHOULDERS
        WorkoutScreenState.FocusAreaUiState.ABS -> FocusArea.CORE
        WorkoutScreenState.FocusAreaUiState.CALVES -> FocusArea.ARMS
        WorkoutScreenState.FocusAreaUiState.LOWER_BACK -> FocusArea.BACK
        WorkoutScreenState.FocusAreaUiState.CORE -> FocusArea.CORE
        WorkoutScreenState.FocusAreaUiState.SHOULDERS -> FocusArea.SHOULDERS
    }
}

fun WorkoutSuggested.toUiState(): WorkoutScreenState.WorkoutUiState {
    return WorkoutScreenState.WorkoutUiState(
        id = id,
        title = name,
        duration = durationSeconds.toString(),
        focusArea = focusArea.firstOrNull()?.toUiState()
            ?: WorkoutScreenState.FocusAreaUiState.FULL_BODY,
        imageUrl = imageUrl
    )
}