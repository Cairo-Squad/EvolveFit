package com.cairosquad.evolvefit.ui.screen.createWorkout.content.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.design_system.util.NetworkImage
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.exercise_image
import org.jetbrains.compose.resources.stringResource

@Composable
fun ExerciseCardWithTick(
    title: String,
    time: String,
    model: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    measurementContent: @Composable () -> Unit
) {
    Row(
        modifier = modifier
            .padding(vertical = 6.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        NetworkImage(
            modifier = Modifier
                .size(width = 88.dp, height = 68.dp)
                .clip(RoundedCornerShape(8.dp)),
            model = model,
            contentDescription = stringResource(Res.string.exercise_image),
        )
        Column(
            modifier = Modifier.weight(1f)
                .padding(start = 12.dp),
        ) {
            Text(
                text = title,
                style = Theme.textStyle.body.mediumMedium14,
                color = Theme.color.surfaces.onSurface
            )
            if (time.isNotEmpty()) {
                Text(
                    text = time,
                    style = Theme.textStyle.label.smallRegular12,
                    color = Theme.color.surfaces.onSurfaceVariant
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            measurementContent()
        }

        CreateCustomTick(
            isChecked = isChecked,
            onCheckedChange = onCheckedChange
        )
    }
}