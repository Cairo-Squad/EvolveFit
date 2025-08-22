package com.cairosquad.evolvefit.ui.screen.communityWorkout.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.Chip
import com.cairosquad.evolvefit.viewmodel.workout.WorkoutScreenState
import org.jetbrains.compose.resources.stringResource

@Composable
fun FocusAreaFilter(
    focusArea: List<WorkoutScreenState.FocusAreaUiState>,
    selectedFocusArea: WorkoutScreenState.FocusAreaUiState,
    onSelectFocusArea: (WorkoutScreenState.FocusAreaUiState) -> Unit
) {
    LazyRow(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(focusArea.size) { index ->
            val area = focusArea[index]
            Chip(
                title = stringResource(area.nameResId),
                isSelected = selectedFocusArea == area,
                onClick = { onSelectFocusArea(area) }
            )
        }
    }
}