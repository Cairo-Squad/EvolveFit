package com.cairosquad.evolvefit.ui.screen.workoutDetails.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.Chip
import com.cairosquad.evolvefit.design_system.component.PrimaryButton
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.screen.workoutDetails.component.ImageCarousel
import com.cairosquad.evolvefit.viewmodel.workoutDetails.WorkoutDetailsScreenState
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.bullet_point
import evolvefit.composeapp.generated.resources.close
import evolvefit.composeapp.generated.resources.equipment
import evolvefit.composeapp.generated.resources.focus_abs
import evolvefit.composeapp.generated.resources.focus_area
import evolvefit.composeapp.generated.resources.focus_calves
import evolvefit.composeapp.generated.resources.focus_core
import evolvefit.composeapp.generated.resources.focus_full_body
import evolvefit.composeapp.generated.resources.focus_lower_back
import evolvefit.composeapp.generated.resources.focus_quadriceps
import evolvefit.composeapp.generated.resources.focus_shoulders
import evolvefit.composeapp.generated.resources.instructions
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ExerciseBottomSheetContent(
    exercise: WorkoutDetailsScreenState.ExerciseUiState?,
    onDismissBottomSheet: () -> Unit
) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        exercise?.let {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = exercise.name,
                    style = Theme.textStyle.title.largeBold16,
                    color = Theme.color.surfaces.onSurface,
                )
                ImageCarousel(exercise.images,exercise.type)
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = stringResource(Res.string.instructions),
                    style = Theme.textStyle.headline.largeBold18,
                    color = Theme.color.surfaces.onSurface,
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    exercise.instructions.forEach { instruction ->
                        Text(
                            text = stringResource(Res.string.bullet_point, instruction),
                            style = Theme.textStyle.label.smallRegular14,
                            color = Theme.color.surfaces.onSurfaceVariant,
                            modifier = Modifier.padding(bottom = 12.dp)
                        )
                    }
                }
            }
            Column(
                modifier = Modifier.align(Alignment.Start),
                verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(
                    text = stringResource(Res.string.equipment),
                    style = Theme.textStyle.headline.largeBold18,
                    color = Theme.color.surfaces.onSurface,
                )
                Text(
                    text = stringResource(Res.string.bullet_point, exercise.equipment),
                    style = Theme.textStyle.label.smallRegular14,
                    color = Theme.color.surfaces.onSurfaceVariant,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = stringResource(Res.string.focus_area),
                    style = Theme.textStyle.headline.largeBold18,
                    color = Theme.color.surfaces.onSurface,
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    exercise.focusAreas.forEach { area ->
                        Chip(
                            title = focusAreasToStrings(area)
                        )
                    }
                }
            }
        }
        PrimaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.End)
                .padding(bottom = 16.dp),
            text = stringResource(Res.string.close),
            onClick = onDismissBottomSheet,
            isEnabled = true
        )
    }
}

@Composable
private fun focusAreasToStrings(focusAreas: WorkoutDetailsScreenState.FocusArea): String {
    return when (focusAreas) {
        WorkoutDetailsScreenState.FocusArea.QUADRICEPS -> stringResource(Res.string.focus_quadriceps)
        WorkoutDetailsScreenState.FocusArea.ABS -> stringResource(Res.string.focus_abs)
        WorkoutDetailsScreenState.FocusArea.CALVES -> stringResource(Res.string.focus_calves)
        WorkoutDetailsScreenState.FocusArea.LOWER_BACK -> stringResource(Res.string.focus_lower_back)
        WorkoutDetailsScreenState.FocusArea.CORE -> stringResource(Res.string.focus_core)
        WorkoutDetailsScreenState.FocusArea.SHOULDERS -> stringResource(Res.string.focus_shoulders)
        WorkoutDetailsScreenState.FocusArea.FULL_BODY -> stringResource(Res.string.focus_full_body)

    }
}

@Preview
@Composable
fun ExerciseBottomSheetContentPreview() {
    val dummyExercise = WorkoutDetailsScreenState.ExerciseUiState(
        name = "Squats",
        instructions = listOf(
            "Stand with your feet shoulder-width apart",
            "Lower your hips until your thighs are parallel to the floor",
            "Push through your heels to return to standing"
        ),
        images = listOf(
            "https://via.placeholder.com/150",
            "https://via.placeholder.com/150"
        ),
        type = WorkoutDetailsScreenState.ExerciseType.Reps(count = 12),
        equipment = "Barbell",
        focusAreas = listOf(
            WorkoutDetailsScreenState.FocusArea.QUADRICEPS,
            WorkoutDetailsScreenState.FocusArea.CORE
        )
    )

    AppTheme(isDarkTheme = true) {
        ExerciseBottomSheetContent(
            exercise = dummyExercise,
            onDismissBottomSheet = {}
        )
    }
}
