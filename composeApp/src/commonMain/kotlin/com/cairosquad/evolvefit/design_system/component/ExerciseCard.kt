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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.design_system.util.NetworkImage
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ExerciseCard(
    title: String,
    time: String,
    modifier: Modifier = Modifier,
    unit: String = "Seconds",
    model: String
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        NetworkImage(
            modifier = Modifier
                .size(width = 88.dp, height = 68.dp)
                .clip(RoundedCornerShape(8.dp)),
            model = model,
            contentDescription = "Exercise Image",
        )
        Column(
            modifier = Modifier.weight(1f)
                .padding(start = 12.dp),
        ) {
            Text(
                text = title,
                style = Theme.textStyle.body.mediumMedium14,
                color = Theme.color.surfaces.onSurfaceContainer
            )
            Text(
                text = "$time $unit",
                style = Theme.textStyle.label.smallRegular12,
                color = Theme.color.surfaces.onSurfaceVariant,
                modifier = Modifier.padding(top = 12.dp)
            )
        }
    }
}

@Preview
@Composable
private fun ExerciseCardPreview() {
    AppTheme(isDarkTheme = true) {
        Box(
            Modifier
                .background(Theme.color.surfaces.surface)
                .padding(16.dp)
        ) {
            ExerciseCard(title = "Bodyweight Squats", time = "30", model = "")
        }
    }
}