package com.cairosquad.evolvefit.ui.screen.workoutDetails.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme


@Composable
fun BulletPointText(instruction: String) {
    Row {
        Box(
            modifier = Modifier
                .size(8.dp)
                .background(
                    color = Theme.color.surfaces.onSurfaceVariant,
                    shape = CircleShape
                )
                .padding(top = 6.dp, end = 6.dp)
        )
        Text(
            text = instruction,
            style = Theme.textStyle.label.smallRegular14,
            color = Theme.color.surfaces.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 12.dp)
        )
    }
}