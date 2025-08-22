package com.cairosquad.evolvefit.viewmodel.workout

import com.cairosquad.evolvefit.domain.entity.WorkoutSuggested
import com.cairosquad.evolvefit.domain.model.FocusArea

fun FocusArea.toUiState(): WorkoutScreenState.FocusAreaUiState {
    return when (this) {
        FocusArea.CORE -> WorkoutScreenState.FocusAreaUiState.All
        FocusArea.ARMS -> WorkoutScreenState.FocusAreaUiState.ARMS
        FocusArea.BACK -> WorkoutScreenState.FocusAreaUiState.BACK
        FocusArea.SHOULDERS -> WorkoutScreenState.FocusAreaUiState.SHOULDERS
        FocusArea.CHEST -> WorkoutScreenState.FocusAreaUiState.CHEST
        FocusArea.LEGS -> WorkoutScreenState.FocusAreaUiState.LEGS
    }
}

fun WorkoutScreenState.FocusAreaUiState.toDomain(): FocusArea {
    return when (this) {
        WorkoutScreenState.FocusAreaUiState.All -> FocusArea.CORE
        WorkoutScreenState.FocusAreaUiState.CHEST -> FocusArea.CHEST
        WorkoutScreenState.FocusAreaUiState.SHOULDERS -> FocusArea.SHOULDERS
        WorkoutScreenState.FocusAreaUiState.ARMS -> FocusArea.ARMS
        WorkoutScreenState.FocusAreaUiState.BACK -> FocusArea.BACK
        WorkoutScreenState.FocusAreaUiState.LEGS -> FocusArea.LEGS
    }
}

fun WorkoutSuggested.toUiState(): WorkoutScreenState.WorkoutSuggestedUiState {
    return WorkoutScreenState.WorkoutSuggestedUiState(
        id = id,
        title = name,
        duration = durationSeconds.toString(),
        focusArea = focusArea.firstOrNull()?.toUiState()
            ?: WorkoutScreenState.FocusAreaUiState.All,
        imageUrl = imageUrl
    )
}