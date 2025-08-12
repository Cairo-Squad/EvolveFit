package com.cairosquad.evolvefit.ui.screen.report.componant

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
    onDropDownMenuItemClicked: (String) -> Unit,
    onDropDownMenuDismiss: () -> Unit,
    isAnimationStarted: Boolean
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
    ) {
        DashboardGrid(
            modifier = Modifier
                .padding(top = 8.dp),
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
            items = listOf("This Week", "Last Week"),
            selectedItem = "Week",
            expanded = screenState.isDropDownMenuOpen,
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