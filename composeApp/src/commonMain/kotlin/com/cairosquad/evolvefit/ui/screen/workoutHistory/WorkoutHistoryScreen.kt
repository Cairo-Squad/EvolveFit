package com.cairosquad.evolvefit.ui.screen.workoutHistory

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.appbar.CustomAppBar
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.screen.report.componant.cards.HistoryWorkoutItem
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ic_back
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun WorkoutHistoryScreen(
    navigateBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .background(Theme.color.surfaces.surface)
            .windowInsetsPadding(WindowInsets.systemBars)
            .fillMaxSize()
    ) {
        CustomAppBar(
            modifier = Modifier.padding(horizontal = 16.dp),
            title = "Workout History",
            header = {
                Box(
                    modifier = Modifier.size(40.dp)
                        .clip(CircleShape)
                        .clickable(onClick = navigateBack)
                        .padding(8.dp)
                ) {
                    Image(
                        painter = painterResource(Res.drawable.ic_back),
                        contentDescription = "Back Button",
                        colorFilter = ColorFilter.tint(Theme.color.surfaces.onSurface)
                    )
                }
            }
        )
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(16.dp)
        ) {
            stickyHeader {
                Text(
                    modifier = Modifier
                        .align(Alignment.Start)
                        .fillMaxWidth()
                        .background(Theme.color.surfaces.surface)
                        .padding(vertical = 12.dp),
                    text = "Today",
                    style = Theme.textStyle.label.smallRegular14,
                    color = Theme.color.surfaces.onSurfaceVariant
                )
            }
            items(10) {
                HistoryWorkoutItem(
                    modifier = Modifier.then(
                        if (it == 9) Modifier.padding(bottom = 16.dp) else Modifier.padding(bottom = 12.dp)
                    ),
                    workoutName = "Bodyweight Squats",
                    workoutDate = "Today, 8:30 AM",
                    workoutImage = "",
                    exerciseCount = 15,
                    duration = "45 Min",
                    level = "Beginner"
                )
            }
            stickyHeader {
                Text(
                    modifier = Modifier
                        .align(Alignment.Start)
                        .fillMaxWidth()
                        .background(Theme.color.surfaces.surface)
                        .padding(vertical = 12.dp),
                    text = "Yesterday",
                    style = Theme.textStyle.label.smallRegular14,
                    color = Theme.color.surfaces.onSurfaceVariant
                )
            }
            items(10) {
                HistoryWorkoutItem(
                    modifier = Modifier.then(
                        if (it == 9) Modifier.padding(bottom = 16.dp) else Modifier
                    ),
                    workoutName = "Bodyweight Squats",
                    workoutDate = "Today, 8:30 AM",
                    workoutImage = "",
                    exerciseCount = 15,
                    duration = "45 Min",
                    level = "Beginner"
                )
            }
        }
    }
}

@Preview
@Composable
private fun WorkoutHistoryScreenPreview() {
    WorkoutHistoryScreen({})
}