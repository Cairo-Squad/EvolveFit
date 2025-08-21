package com.cairosquad.evolvefit.ui.screen.workoutDetails.content.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.viewmodel.workoutDetails.WorkoutDetailsScreenState
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.advanced
import evolvefit.composeapp.generated.resources.beginner
import evolvefit.composeapp.generated.resources.detail_icon
import evolvefit.composeapp.generated.resources.exercises
import evolvefit.composeapp.generated.resources.ic_level_detail
import evolvefit.composeapp.generated.resources.ic_person
import evolvefit.composeapp.generated.resources.ic_time
import evolvefit.composeapp.generated.resources.intermediate
import evolvefit.composeapp.generated.resources.level_label
import evolvefit.composeapp.generated.resources.second
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
fun DetailsCardsRow(
    level: WorkoutDetailsScreenState.WorkoutLevel,
    exercisesNumber: Int,
    estimatedTimeInSeconds: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        DetailCard(
            icon = painterResource(Res.drawable.ic_person),
            value = exercisesNumber.toString(),
            label = stringResource(Res.string.exercises),
            modifier = Modifier.weight(1f)
        )
        DetailCard(
            icon = painterResource(Res.drawable.ic_time),
            value = estimatedTimeInSeconds.toString(),
            label = stringResource(Res.string.second),
            modifier = Modifier.weight(1f)
        )
        DetailCard(
            icon = painterResource(Res.drawable.ic_level_detail),
            value = stringResource(workoutLevelToString(level)),
            label = stringResource(Res.string.level_label),
            modifier = Modifier.weight(1f)
        )
    }
}


@Composable
private fun DetailCard(
    icon: Painter,
    value: String,
    label: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(shape = RoundedCornerShape(8.dp))
            .border(
                width = 1.dp,
                color = Theme.color.surfaces.outlineVariant,
                shape = RoundedCornerShape(8.dp)
            )
            .background(Theme.color.surfaces.surfaceContainer).padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Icon(
            painter = icon,
            contentDescription = stringResource(Res.string.detail_icon),
            tint = Theme.color.brand.primary,
            modifier = Modifier
                .size(20.dp)
        )
        Text(
            text = value,
            color = Theme.color.surfaces.onSurface,
            style = Theme.textStyle.title.mediumMedium14,
            modifier = Modifier.padding(top=8.dp,bottom = 2.dp)
        )
        Text(
            text = label,
            color = Theme.color.surfaces.onSurfaceVariant,
            style = Theme.textStyle.label.smallRegular12,
        )
    }
}

private fun workoutLevelToString(level: WorkoutDetailsScreenState.WorkoutLevel): StringResource {
    return when (level) {
        WorkoutDetailsScreenState.WorkoutLevel.BEGINNER -> Res.string.beginner
        WorkoutDetailsScreenState.WorkoutLevel.INTERMEDIATE -> Res.string.intermediate
        WorkoutDetailsScreenState.WorkoutLevel.ADVANCED -> Res.string.advanced
    }
}

@Preview
@Composable
fun DetailsCardsRowPreview() {
    AppTheme  {
        DetailsCardsRow(
            level = WorkoutDetailsScreenState.WorkoutLevel.INTERMEDIATE,
            exercisesNumber = 12,
            estimatedTimeInSeconds = 30,
            modifier = Modifier.padding(16.dp)
        )
    }
}
