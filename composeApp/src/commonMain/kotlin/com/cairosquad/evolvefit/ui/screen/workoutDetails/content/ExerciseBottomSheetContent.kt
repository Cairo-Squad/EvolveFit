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
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.screen.workoutDetails.component.ImageCarousel
import com.cairosquad.evolvefit.ui.screen.workoutDetails.component.BulletPointText
import com.cairosquad.evolvefit.viewmodel.workoutDetails.WorkoutDetailsScreenState
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.close
import evolvefit.composeapp.generated.resources.focus_abs
import evolvefit.composeapp.generated.resources.focus_calves
import evolvefit.composeapp.generated.resources.focus_core
import evolvefit.composeapp.generated.resources.focus_lower_back
import evolvefit.composeapp.generated.resources.focus_quadriceps
import evolvefit.composeapp.generated.resources.focus_shoulders
import org.jetbrains.compose.resources.stringResource

@Composable
fun ExerciseBottomSheetContent(
    exercise: WorkoutDetailsScreenState.ExerciseUiState?,
    onDismissBottomSheet: () -> Unit
) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
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
                ImageCarousel(exercise.images)
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Instructions",
                    style = Theme.textStyle.headline.largeBold18,
                    color = Theme.color.surfaces.onSurface,
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    exercise.instructions.forEach { instruction ->
                        BulletPointText(instruction)
                    }
                }
            }
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(
                    text = "Equipment",
                    style = Theme.textStyle.headline.largeBold18,
                    color = Theme.color.surfaces.onSurface,
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) { BulletPointText(exercise.equipment) }
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Focus Area",
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
                .padding(horizontal = 16.dp)
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
    }
}
