package com.cairosquad.evolvefit.ui.screen.workoutHistory.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.screen.report.componant.cards.HistoryWorkoutItem
import com.cairosquad.evolvefit.viewmodel.workoutHistory.WorkoutHistoryScreenState

@Composable
fun WorkoutScreenSuccessContent(
    state: WorkoutHistoryScreenState
) {
    val groupedHistory = state.workoutHistory.groupBy { it.dateGroup }

    LazyColumn(
        horizontalAlignment = Alignment.Start,
        contentPadding = PaddingValues(16.dp)
    ) {

        groupedHistory.forEach { (dayGroup, workouts) ->
            stickyHeader {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Theme.color.surfaces.surface)
                        .padding(vertical = 12.dp),
                    text = dayGroup,
                    style = Theme.textStyle.label.smallRegular14,
                    color = Theme.color.surfaces.onSurfaceVariant
                )
            }
            itemsIndexed(workouts) { index, workout->
                HistoryWorkoutItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .then(
                            if (index == workouts.size - 1)
                                Modifier.padding(bottom = 16.dp)
                            else
                                Modifier.padding(bottom = 12.dp)
                        )
                        .background(
                            color = Theme.color.surfaces.surfaceContainer,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(12.dp),
                    workoutName = workout.name,
                    workoutDate = workout.date,
                    workoutImage = workout.imageUrl,
                    exerciseCount = workout.exercisesCount,
                    duration = workout.duration,
                    level = workout.level
                )
            }
        }
    }
}