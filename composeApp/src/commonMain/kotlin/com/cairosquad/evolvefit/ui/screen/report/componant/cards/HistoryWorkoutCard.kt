package com.cairosquad.evolvefit.ui.screen.report.componant.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun HistoryWorkoutCard(
    onViewAllHistoryClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    ReportCard(
        modifier = modifier,
        title = "Workout History",
        value = ""
    ) {
        repeat(4) {
            HistoryWorkoutItem(
                modifier = Modifier.padding(vertical = 12.dp),
                workoutName = "Bodyweight Squats",
                workoutDate = "Today, 8:30 AM",
                workoutImage = "",
                exerciseCount = 5,
                duration = "25 min",
                level = "Beginner",
            )
            Box(
                modifier = Modifier.fillMaxWidth()
                    .height(1.dp)
                    .background(Theme.color.surfaces.outlineVariant)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Box(modifier = Modifier
            .clip(CircleShape)
            .clickable(onClick = onViewAllHistoryClicked)
            .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Text(
                text = "View all",
                style = Theme.textStyle.label.mediumMedium16,
                color = Theme.color.brand.primary,
            )
        }
    }
}

@Preview
@Composable
private fun HistoryWorkoutCardPreview() {
    HistoryWorkoutCard({})
}