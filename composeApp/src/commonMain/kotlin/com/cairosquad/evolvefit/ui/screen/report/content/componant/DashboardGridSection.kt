package com.cairosquad.evolvefit.ui.screen.report.content.componant

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.ui.component.DropdownMenu
import com.cairosquad.evolvefit.viewmodel.report.ReportScreenState
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun DashboardGridSection(
    screenState: ReportScreenState,
    onDropDownMenuItemClicked: (ReportScreenState.WeekItem) -> Unit,
    onDropDownMenuDismiss: () -> Unit,
    isAnimationStarted: Boolean
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        DashboardGrid(
            modifier = Modifier,
            expectedCalories = screenState.report.expectedCalories,
            takenCalories = screenState.report.takenCaloriesInKcal,
            timeSpent = screenState.report.timeSpent,
            waterConsumed = screenState.report.waterConsumed,
            totalWorkout = screenState.report.totalWorkouts,
            isAnimationStarted = isAnimationStarted
        )
        DropdownMenu(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .width(160.dp),
            items = screenState.weeks,
            selectedItem = screenState.selectedWeek,
            isExpanded = screenState.isDropDownMenuOpen,
            onItemClicked = onDropDownMenuItemClicked,
            onDismissRequest = onDropDownMenuDismiss
        )
    }
}

@Preview
@Composable
private fun DashboardGridSectionPreview() {
    DashboardGridSection(
        screenState = ReportScreenState(),
        onDropDownMenuItemClicked = {},
        onDropDownMenuDismiss = {},
        isAnimationStarted = true
    )
}