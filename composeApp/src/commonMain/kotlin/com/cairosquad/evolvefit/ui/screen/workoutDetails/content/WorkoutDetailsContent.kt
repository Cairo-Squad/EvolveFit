package com.cairosquad.evolvefit.ui.screen.workoutDetails.content

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.cairosquad.evolvefit.viewmodel.workout_details.WorkoutDetailsInteractionListener
import com.cairosquad.evolvefit.viewmodel.workout_details.WorkoutDetailsScreenState

@Composable
fun WorkoutDetailsContent(
    state: WorkoutDetailsScreenState,
    listener: WorkoutDetailsInteractionListener
) {
    when (state.screenState) {
        WorkoutDetailsScreenState.ScreenState.Loading -> {
            WorkoutDetailsLoading(listener)
        }

        WorkoutDetailsScreenState.ScreenState.Success -> {
            WorkoutDetailsSuccess(
                state = state,
                listener = listener
            )
        }

        WorkoutDetailsScreenState.ScreenState.Error -> {
            Text("Loading")
        }
    }
}