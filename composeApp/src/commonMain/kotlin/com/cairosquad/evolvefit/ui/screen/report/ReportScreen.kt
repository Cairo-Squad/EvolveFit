package com.cairosquad.evolvefit.ui.screen.report

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
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
import com.cairosquad.evolvefit.ui.screen.report.componant.cards.DynamicWorkoutsChartCard
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
    val data by remember { mutableStateOf(listOf(5f, 3f, 5f, 4f, 5f, 3f, 1f)) }
    val labels by remember {
        mutableStateOf(
            listOf(
                "Sat",
                "Sun",
                "Mon",
                "Tue",
                "Wed",
                "Thu",
                "Fri"
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
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
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
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
                            .padding(top = 8.dp)
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
                DynamicWorkoutsChartCard(
                    data = data,
                    labels = labels,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
            item {

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