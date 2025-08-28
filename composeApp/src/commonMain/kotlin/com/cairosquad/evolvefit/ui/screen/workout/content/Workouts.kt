package com.cairosquad.evolvefit.ui.screen.workout.content

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.WorkoutCard
import com.cairosquad.evolvefit.viewmodel.workout.WorkoutScreenState
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.min
import org.jetbrains.compose.resources.stringResource

@Composable
fun Workouts(
    workouts: List<WorkoutScreenState.WorkoutSuggestedUiState>,
    selected: WorkoutScreenState.FocusAreaUiState,
    onClickWorkout: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.padding(vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(workouts) { workout ->
            val displayArea =
                if (selected == WorkoutScreenState.FocusAreaUiState.ALL)
                    workout.focusArea
                else
                    selected

            WorkoutCard(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .clickable { onClickWorkout(workout.id) },
                title = workout.title,
                duration = workout.durationInMinutes + " " + stringResource(Res.string.min),
                focusArea = stringResource(displayArea.nameResId),
                model = workout.imageUrl,
            )
        }
    }
}