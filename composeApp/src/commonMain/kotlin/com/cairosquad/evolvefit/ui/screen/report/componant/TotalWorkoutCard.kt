package com.cairosquad.evolvefit.ui.screen.report.componant

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun TotalWorkoutCard(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .height(127.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Theme.color.surfaces.surfaceContainer)
            .padding(horizontal = 12.dp, vertical = 16.dp)
    )
}

@Preview
@Composable
fun TotalWorkoutCardPreview() {
    TotalWorkoutCard(
        modifier = Modifier
            .width(160.dp)
    )
}