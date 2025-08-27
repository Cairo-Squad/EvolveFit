package com.cairosquad.evolvefit.ui.screen.createWorkout.content.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.PrimaryButton
import com.cairosquad.evolvefit.viewmodel.create_workout.CreateWorkOutInteractionListener
import com.cairosquad.evolvefit.viewmodel.create_workout.CreateWorkOutScreenState
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.add_exercise_button_
import evolvefit.composeapp.generated.resources.add_exercise_button_with_count_
import evolvefit.composeapp.generated.resources.add_exercises_button_with_count_
import org.jetbrains.compose.resources.stringResource

@Composable
fun AddExerciseButton(
    state: CreateWorkOutScreenState,
    listener: CreateWorkOutInteractionListener,
    modifier: Modifier = Modifier
) {
    PrimaryButton(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .padding(bottom = 24.dp),
        text = when {
            state.exerciseCount > 1 -> stringResource(
                Res.string.add_exercises_button_with_count_,
                state.exerciseCount
            )

            state.exerciseCount == 1 -> stringResource(
                Res.string.add_exercise_button_with_count_,
                state.exerciseCount
            )

            else -> stringResource(Res.string.add_exercise_button_)
        },
        onClick = { listener.onAddWorkoutClicked() },
        isEnabled = state.exerciseCount > 0
    )
}