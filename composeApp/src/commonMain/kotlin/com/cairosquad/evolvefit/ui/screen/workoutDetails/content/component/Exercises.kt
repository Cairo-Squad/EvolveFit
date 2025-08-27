package com.cairosquad.evolvefit.ui.screen.workoutDetails.content.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.ExerciseCard
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.viewmodel.workout_details.WorkoutDetailsScreenState
import com.cairosquad.evolvefit.viewmodel.workout_details.WorkoutDetailsScreenState.ExerciseType
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.exercises
import evolvefit.composeapp.generated.resources.ic_count
import evolvefit.composeapp.generated.resources.ic_time
import evolvefit.composeapp.generated.resources.seconds
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource


@Composable
fun Exercises(
    modifier: Modifier = Modifier,
    exercises: List<WorkoutDetailsScreenState.ExerciseUiState>,
    onExerciseClick: (WorkoutDetailsScreenState.ExerciseUiState) -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(Res.string.exercises),
                color = Theme.color.surfaces.onSurface,
                style = Theme.textStyle.label.mediumMedium16
            )
            Text(
                text = " (${exercises.size})",
                color = Theme.color.surfaces.outline,
                style = Theme.textStyle.label.smallRegular12,
            )
        }
        exercises.forEach { exercise ->
            ExerciseCard(
                title = exercise.name,
                model = exercise.images.firstOrNull() ?: "",
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .clickable { onExerciseClick(exercise) },
                measurementContent = {
                    MeasurementRow(
                        specificationString = when (exercise.type) {
                            is ExerciseType.Duration -> "${exercise.type.seconds} " +
                                    stringResource(Res.string.seconds)

                            is ExerciseType.Reps -> "X${exercise.type.count}"
                        },
                        specificationIcon = when (exercise.type) {
                            is ExerciseType.Duration -> painterResource(Res.drawable.ic_time)
                            is ExerciseType.Reps -> painterResource(Res.drawable.ic_count)
                        },
                    )
                }
            )
        }
    }
}