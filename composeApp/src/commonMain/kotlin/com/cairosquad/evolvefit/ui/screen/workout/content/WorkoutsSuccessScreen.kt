package com.cairosquad.evolvefit.ui.screen.workout.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import com.cairosquad.evolvefit.viewmodel.workout.WorkoutInteractionListener
import com.cairosquad.evolvefit.viewmodel.workout.WorkoutScreenState

@Composable
fun WorkoutsSuccessScreen(
    lazyListState: LazyListState,
    state: WorkoutScreenState,
    listener: WorkoutInteractionListener
) {
    Column {
        WorkoutFocusAreaFilter(
            lazyListState = lazyListState,
            focusArea = WorkoutScreenState.FocusAreaUiState.entries,
            selectedFocusArea = state.selectedFocusArea,
            onSelectFocusArea = listener::onFocusAreaSelected
        )
        Workouts(
            workouts = state.allWorkouts,
            selected = state.selectedFocusArea,
            onClickWorkout = listener::onWorkoutClicked
        )
    }
}