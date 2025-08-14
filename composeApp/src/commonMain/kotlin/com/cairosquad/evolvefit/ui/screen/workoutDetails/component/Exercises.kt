package com.cairosquad.evolvefit.ui.screen.workoutDetails.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.ExerciseCard
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.viewmodel.workoutDetails.WorkoutDetailsScreenState
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.exercises
import evolvefit.composeapp.generated.resources.exercises_with_count
import org.jetbrains.compose.resources.stringResource


@Composable
fun Exercises(
    modifier: Modifier = Modifier,
    exercises: List<WorkoutDetailsScreenState.ExerciseUiState>,
    onExerciseClick: (WorkoutDetailsScreenState.ExerciseUiState) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .padding(bottom = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(Res.string.exercises),
                color = Theme.color.surfaces.onSurface,
                style = Theme.textStyle.label.mediumMedium16
            )
            Text(
                text = stringResource(Res.string.exercises_with_count,{exercises.size}),
                color = Theme.color.surfaces.outline,
                style = Theme.textStyle.label.smallRegular12,
            )
        }
        exercises.forEach { exercise ->
            ExerciseCard(
                title = exercise.name,
                time = "",
                model = exercise.images.first(),
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .clickable { onExerciseClick(exercise) },
                measurementContent = {
                    MeasurementRow(exercise.type)
                }
            )
        }
    }
}