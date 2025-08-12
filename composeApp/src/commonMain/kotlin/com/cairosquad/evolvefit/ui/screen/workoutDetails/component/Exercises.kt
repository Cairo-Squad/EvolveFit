package com.cairosquad.evolvefit.ui.screen.workoutDetails.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.design_system.util.NetworkImage
import com.cairosquad.evolvefit.viewmodel.workoutDetails.WorkoutDetailsScreenState
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ic_time
import org.jetbrains.compose.resources.painterResource


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
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = "Exercises ",
                color = Theme.color.surfaces.onSurface,
                style = Theme.textStyle.label.mediumMedium16
            )
            Text(
                text = "(${exercises.size})",
                color = Theme.color.surfaces.outline,
                style = Theme.textStyle.label.smallRegular12
            )
        }
        exercises.forEach { exercise ->
            ExerciseRow(
                exerciseImage = exercise.images.first(),
                exerciseName = exercise.name,
                exerciseMeasurement = exercise.type,
                onExerciseClick = { onExerciseClick(exercise) }
            )
        }
    }
}


@Composable
private fun ExerciseRow(
    exerciseImage: String,
    exerciseName: String,
    exerciseMeasurement: WorkoutDetailsScreenState.ExerciseType,
    onExerciseClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)
            .clickable(onClick = onExerciseClick),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        NetworkImage(
            model = exerciseImage,
            contentDescription = "exercise image",
            modifier = Modifier
                .width(88.dp)
                .height(68.dp)
                .clip(shape = RoundedCornerShape(8.dp)),
        )
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = exerciseName,
                style = Theme.textStyle.body.mediumMedium14,
                color = Theme.color.surfaces.onSurfaceContainer
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {

                Icon(
                    painter = when (exerciseMeasurement) {
                        is WorkoutDetailsScreenState.ExerciseType.Duration -> painterResource(
                            Res.drawable.ic_time
                        )

                        is WorkoutDetailsScreenState.ExerciseType.Reps -> painterResource(Res.drawable.ic_time)
                    },
                    contentDescription = "Measurement icon",
                    tint = Theme.color.surfaces.onSurfaceVariant
                )
                Text(
                    text = when (exerciseMeasurement) {
                        is WorkoutDetailsScreenState.ExerciseType.Duration -> "${exerciseMeasurement.seconds} Second"
                        is WorkoutDetailsScreenState.ExerciseType.Reps -> "X${exerciseMeasurement.count}"
                    },
                    color = Theme.color.surfaces.onSurfaceVariant,
                    style = Theme.textStyle.label.smallRegular12
                )
            }
        }
    }
}
