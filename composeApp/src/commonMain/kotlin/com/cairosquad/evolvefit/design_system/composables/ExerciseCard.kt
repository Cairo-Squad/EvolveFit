package com.cairosquad.evolvefit.design_system.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.im_default_image
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ExerciseCard(
    title: String,
    time: String,
    modifier: Modifier = Modifier,
    img: DrawableResource = Res.drawable.im_default_image
) {
    Row(
        modifier = modifier.fillMaxWidth().height(68.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(img),
            contentDescription = "Exercise Image",
            Modifier
                .width(88.dp)
                .clip(RoundedCornerShape(8.dp))
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
                text = time,
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
    ExerciseCard(title = "Bodyweight Squats", time = "30 sec")
}