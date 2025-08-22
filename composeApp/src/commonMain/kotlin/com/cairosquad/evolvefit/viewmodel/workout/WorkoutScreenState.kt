package com.cairosquad.evolvefit.viewmodel.workout

import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.all
import evolvefit.composeapp.generated.resources.arms
import evolvefit.composeapp.generated.resources.back
import evolvefit.composeapp.generated.resources.chest
import evolvefit.composeapp.generated.resources.legs
import evolvefit.composeapp.generated.resources.shoulders
import org.jetbrains.compose.resources.StringResource


data class WorkoutScreenState(
    val selectedFocusArea: FocusAreaUiState = FocusAreaUiState.ALL,
    val allWorkouts: List<WorkoutSuggestedUiState> = emptyList(),
    val focusAreas: List<FocusAreaUiState> = FocusAreaUiState.entries,
    val screenStatus: ScreenStatus = ScreenStatus.LOADING,
    val isRefreshing: Boolean = false,
    val errorMessage: String? = null
) {
    data class WorkoutSuggestedUiState(
        val id: String,
        val title: String,
        val duration: String,
        val focusArea: FocusAreaUiState,
        val imageUrl: String
    )

    enum class FocusAreaUiState(val nameResId: StringResource) {
        ALL(Res.string.all),
        BACK(Res.string.back),
        LEGS(Res.string.legs),
        SHOULDERS(Res.string.shoulders),
        ARMS(Res.string.arms),
        CHEST(Res.string.chest)
    }

    enum class ScreenStatus { LOADING, SUCCESS, FAIL }

}