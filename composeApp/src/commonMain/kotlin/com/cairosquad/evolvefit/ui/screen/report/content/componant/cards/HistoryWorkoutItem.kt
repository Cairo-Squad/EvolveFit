package com.cairosquad.evolvefit.ui.screen.report.content.componant.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.design_system.util.NetworkImage
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.exercises
import evolvefit.composeapp.generated.resources.level_label
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun HistoryWorkoutItem(
    workoutName: String,
    workoutDate: String,
    workoutImage: String,
    exerciseCount: Int,
    duration: String,
    level: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            NetworkImage(
                modifier = Modifier
                    .size(width = 68.dp, height = 52.dp)
                    .clip(RoundedCornerShape(4.dp)),
                model = workoutImage,
                contentDescription = "Workout Image",
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = workoutName,
                    style = Theme.textStyle.label.mediumMedium16,
                    color = Theme.color.surfaces.onSurfaceContainer
                )
                Text(
                    text = workoutDate,
                    style = Theme.textStyle.label.smallRegular12,
                    color = Theme.color.surfaces.onSurfaceVariant
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            WorkoutDetailsItem(
                modifier = Modifier.weight(1f),
                title = stringResource(Res.string.exercises),
                value = exerciseCount.toString()
            )
            WorkoutDetailsItem(
                modifier = Modifier.weight(1f),
                title = "Duration",
                value = duration
            )
            WorkoutDetailsItem(
                modifier = Modifier.weight(1f),
                title = stringResource(Res.string.level_label),
                value = level
            )
        }
    }
}

@Composable
private fun WorkoutDetailsItem(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            style = Theme.textStyle.label.smallRegular12,
            color = Theme.color.surfaces.onSurfaceVariant
        )
        Text(
            text = value,
            style = Theme.textStyle.title.largeBold14,
            color = Theme.color.surfaces.onSurfaceContainer
        )
    }
}

@Preview
@Composable
private fun HistoryWorkoutItemPreview() {
    HistoryWorkoutItem(
        workoutName = "Bodyweight Squats",
        workoutDate = "Today, 8:30 AM",
        workoutImage = "",
        exerciseCount = 5,
        duration = "45 min",
        level = "Intermediate"
    )
}