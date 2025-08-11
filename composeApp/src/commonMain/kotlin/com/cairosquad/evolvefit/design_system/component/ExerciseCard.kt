package com.cairosquad.evolvefit.design_system.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.design_system.util.NetworkImage
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.exercise_image
import evolvefit.composeapp.generated.resources.seconds
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ExerciseCard(
    title: String,
    modifier: Modifier = Modifier,
    model: String,
    timeInSeconds: Int? = null,
    reps: Int? = null,
    sets: Int? = null,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    val unitText = when {
        timeInSeconds != null -> "$timeInSeconds ${stringResource(Res.string.seconds)}"
        reps != null && sets != null -> "$sets X$reps"
        else -> ""
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        NetworkImage(
            modifier = Modifier
                .size(width = 88.dp, height = 68.dp)
                .clip(RoundedCornerShape(8.dp)),
            model = model,
            contentDescription = stringResource(Res.string.exercise_image),
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 12.dp),
        ) {
            Text(
                text = title,
                style = Theme.textStyle.body.mediumMedium14,
                color = Theme.color.surfaces.onSurfaceContainer
            )
            Text(
                text = unitText,
                style = Theme.textStyle.label.smallRegular12,
                color = Theme.color.surfaces.onSurfaceVariant,
                modifier = Modifier.padding(top = 12.dp)
            )
        }

        CustomTick(
            isChecked = isChecked,
            onCheckedChange = onCheckedChange
        )
    }
}

@Preview
@Composable
private fun ExerciseCardPreview() {
    var isChecked by remember { mutableStateOf(false) }

    AppTheme(isDarkTheme = true) {
        Column(
            Modifier
                .background(Theme.color.surfaces.surface)
                .padding(16.dp)
        ) {
            ExerciseCard(
                title = "Jumping Jacks",
                timeInSeconds = 30,
                model = "",
                isChecked = isChecked,
                onCheckedChange = { isChecked = it }
            )

            ExerciseCard(
                title = "Push-ups",
                reps = 10,
                sets = 3,
                model = "",
                isChecked = !isChecked,
                onCheckedChange = { isChecked = !isChecked }
            )
        }
    }
}

