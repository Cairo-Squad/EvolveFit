package com.cairosquad.evolvefit.ui.screen.workoutHistory.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.screen.report.content.componant.cards.HistoryWorkoutItem
import com.cairosquad.evolvefit.viewmodel.workout_history.WorkoutHistoryScreenState

fun LazyListScope.workoutHistoryScreenSuccessContent(state: WorkoutHistoryScreenState) {
    val groupedHistory = state.workoutHistory.groupBy { it.dateGroup }
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
        itemsIndexed(workouts) { index, workout ->
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