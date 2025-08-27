package com.cairosquad.evolvefit.ui.screen.workoutDetails.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.Chip
import com.cairosquad.evolvefit.design_system.component.PrimaryButton
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.screen.workoutDetails.content.component.ImageCarousel
import com.cairosquad.evolvefit.ui.util.ScreenSize
import com.cairosquad.evolvefit.viewmodel.workout_details.WorkoutDetailsScreenState
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.bullet_point
import evolvefit.composeapp.generated.resources.close
import evolvefit.composeapp.generated.resources.equipment
import evolvefit.composeapp.generated.resources.focus_abs
import evolvefit.composeapp.generated.resources.focus_area
import evolvefit.composeapp.generated.resources.focus_calves
import evolvefit.composeapp.generated.resources.focus_core
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
        modifier = Modifier.padding(horizontal = 16.dp)
            .heightIn(max = ScreenSize.heightDp.dp * 0.95f)
            .verticalScroll(rememberScrollState())
    ) {
        exercise?.let {
            Text(
                modifier = Modifier.padding(bottom = 12.dp),
                text = exercise.name,
                style = Theme.textStyle.title.largeBold16,
                color = Theme.color.surfaces.onSurface,
            )
            ImageCarousel(exercise.images, exercise.type)
            Text(
                modifier = Modifier.padding(top = 16.dp, bottom = 12.dp),
                text = stringResource(Res.string.instructions),
                style = Theme.textStyle.headline.largeBold18,
                color = Theme.color.surfaces.onSurface,
            )
            exercise.instructions.forEach { instruction ->
                Row(
                    modifier = Modifier.padding(bottom = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "•",
                        style = Theme.textStyle.label.smallRegular14,
                        color = Theme.color.surfaces.onSurfaceVariant,
                    )
                    Text(
                        text = instruction,
                        style = Theme.textStyle.label.smallRegular14,
                        color = Theme.color.surfaces.onSurfaceVariant,
                    )
                }
            }
            Text(
                modifier = Modifier.padding(top = 20.dp, bottom = 12.dp),
                text = stringResource(Res.string.equipment),
                style = Theme.textStyle.headline.largeBold18,
                color = Theme.color.surfaces.onSurface,
            )
            Text(
                modifier = Modifier.padding(bottom = 24.dp),
                text = stringResource(Res.string.bullet_point, exercise.equipment),
                style = Theme.textStyle.label.smallRegular14,
                color = Theme.color.surfaces.onSurfaceVariant,
            )
            Text(
                modifier = Modifier.padding(bottom = 12.dp),
                text = stringResource(Res.string.focus_area),
                style = Theme.textStyle.headline.largeBold18,
                color = Theme.color.surfaces.onSurface,
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                exercise.focusAreas.forEach { area -> Chip(title = focusAreasToStrings(area)) }
            }
        }
        PrimaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.End)
                .padding(bottom = 16.dp, top = 40.dp)
                .navigationBarsPadding(),
            text = stringResource(Res.string.close),
            onClick = onDismissBottomSheet,
            isEnabled = true
        )
    }
}

@Composable
private fun focusAreasToStrings(focusAreas: WorkoutDetailsScreenState.FocusArea): String {
    return when (focusAreas) {
        WorkoutDetailsScreenState.FocusArea.CORE -> stringResource(Res.string.focus_core)
        WorkoutDetailsScreenState.FocusArea.SHOULDERS -> stringResource(Res.string.focus_shoulders)
        WorkoutDetailsScreenState.FocusArea.BACK -> stringResource(Res.string.focus_lower_back)
        WorkoutDetailsScreenState.FocusArea.LEGS -> stringResource(Res.string.focus_calves)
        WorkoutDetailsScreenState.FocusArea.ARMS -> stringResource(Res.string.focus_abs)
        WorkoutDetailsScreenState.FocusArea.CHEST -> stringResource(Res.string.focus_quadriceps)
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
            "https://res.cloudinary.com/dqd5lvkpz/image/upload/v1755011701/evolveFit/images/exercises/747060f3-dca1-46dd-816e-e308eb57eb17-1755011700.png",
            "https://res.cloudinary.com/dqd5lvkpz/image/upload/v1755011701/evolveFit/images/exercises/747060f3-dca1-46dd-816e-e308eb57eb17-1755011700.png"
        ),
        type = WorkoutDetailsScreenState.ExerciseType.Reps(count = 12),
        equipment = "Barbell",
        focusAreas = listOf(
            WorkoutDetailsScreenState.FocusArea.SHOULDERS,
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
