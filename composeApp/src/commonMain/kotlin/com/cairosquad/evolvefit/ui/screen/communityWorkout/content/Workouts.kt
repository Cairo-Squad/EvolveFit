package com.cairosquad.evolvefit.ui.screen.communityWorkout.content

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.WorkoutCard
import com.cairosquad.evolvefit.viewmodel.workout.WorkoutScreenState


@Composable
fun Workouts(
    workouts: List<WorkoutScreenState.WorkoutSuggestedUiState>,
    onClickWorkout: (String) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(workouts) { workout ->
            WorkoutCard(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .clickable { onClickWorkout(workout.id) },
                title = workout.title,
                duration = workout.duration,
                focusArea = workout.focusArea.name,
                model = workout.imageUrl,
            )
        }
    }
}
