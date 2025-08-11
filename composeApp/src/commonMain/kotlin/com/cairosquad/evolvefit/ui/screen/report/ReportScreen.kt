package com.cairosquad.evolvefit.ui.screen.report

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.appbar.ActionIconButton
import com.cairosquad.evolvefit.design_system.component.appbar.CustomAppBar
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.component.DropdownMenu
import com.cairosquad.evolvefit.ui.screen.report.componant.DashboardGrid
import com.cairosquad.evolvefit.ui.screen.report.componant.WeekFilter
import com.cairosquad.evolvefit.ui.screen.report.componant.cards.BarChartCard
import com.cairosquad.evolvefit.ui.screen.report.componant.cards.LineChartCard
import com.cairosquad.evolvefit.ui.screen.report.componant.cards.MusclesCard
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ic_export
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ReportScreen(
    navigateToWorkoutHistory: () -> Unit
) {
    ReportScreenContent(
        navigateToWorkoutHistory = navigateToWorkoutHistory
    )
}

@Composable
private fun ReportScreenContent(
    navigateToWorkoutHistory: () -> Unit
) {
    val data by remember { mutableStateOf(listOf(0f, 3f, 2f, 4f, 5f, 3f, 1f)) }
    val labels by remember { mutableStateOf(listOf("Sat", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri")) }
    val musclesName = listOf("Chest", "Back", "Legs", "Arms", "Shoulders")
    val musclesPercentage = listOf(0.32f, 0.24f, 0.68f, 0.49f, 0.11f)

    Column(
        modifier = Modifier
            .windowInsetsPadding(WindowInsets.statusBars)
            .background(Theme.color.surfaces.surface)
    ) {
        CustomAppBar(
            title = "Workout Report",
            tail = {
                ActionIconButton(
                    icon = painterResource(Res.drawable.ic_export),
                    contentDescription = "",
                    onClick = {},
                    tint = Theme.color.surfaces.onSurface,
                )
            }
        )
        LazyColumn(
            contentPadding = PaddingValues(16.dp)
        ) {
            item {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = "Activity",
                        color = Theme.color.surfaces.onSurface,
                        style = Theme.textStyle.title.mediumMedium16
                    )
                    WeekFilter(
                        currentWeek = "This Week",
                        onMenuClick = {}
                    )
                }
            }
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    DashboardGrid(
                        modifier = Modifier
                            .padding(top = 8.dp),
                    )
                    DropdownMenu(
                        modifier = Modifier
                            .width(160.dp)
                            .align(Alignment.TopEnd),
                        items = listOf("This Week", "Last Week"),
                        selectedItem = "Week",
                        expanded = false,
                        onItemClicked = {},
                        onDismissRequest = {}
                    )
                }
            }
            item {
                BarChartCard(
                    modifier = Modifier.padding(top = 16.dp),
                    data = data,
                    labels = labels,
                )
            }
            item {
                LineChartCard(
                    modifier = Modifier.padding(top = 16.dp),
                    data = data,
                    labels = labels,
                    totalTime = "2h 35min",
                )
            }
            item {
                MusclesCard(
                    modifier = Modifier.padding(top = 16.dp),
                    musclesName = musclesName,
                    trainedMusclesPercentage = musclesPercentage,
                )
            }
        }
    }
}


@Preview
@Composable
private fun ReportScreenPreview() {
    AppTheme(isDarkTheme = true) {
        ReportScreenContent({})
    }
}