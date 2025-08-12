package com.cairosquad.evolvefit.ui.screen.report

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cairosquad.evolvefit.design_system.component.appbar.ActionIconButton
import com.cairosquad.evolvefit.design_system.component.appbar.CustomAppBar
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.screen.report.componant.ActivityRow
import com.cairosquad.evolvefit.ui.screen.report.componant.DashboardGridSection
import com.cairosquad.evolvefit.ui.screen.report.componant.cards.BarChartCard
import com.cairosquad.evolvefit.ui.screen.report.componant.cards.HistoryWorkoutCard
import com.cairosquad.evolvefit.ui.screen.report.componant.cards.LineChartCard
import com.cairosquad.evolvefit.ui.screen.report.componant.cards.MusclesCard
import com.cairosquad.evolvefit.viewmodel.report.ReportInteractionListener
import com.cairosquad.evolvefit.viewmodel.report.ReportScreenState
import com.cairosquad.evolvefit.viewmodel.report.ReportViewModel
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ic_export
import evolvefit.composeapp.generated.resources.workout_report
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ReportScreen(
    viewModel: ReportViewModel = koinViewModel(),
    navigateToWorkoutHistory: () -> Unit
) {
    val uiState by viewModel.screenState.collectAsStateWithLifecycle()
    ReportScreenContent(
        screenState = uiState,
        listener = viewModel,
        navigateToWorkoutHistory = navigateToWorkoutHistory
    )
}

@Composable
private fun ReportScreenContent(
    screenState: ReportScreenState,
    listener: ReportInteractionListener,
    navigateToWorkoutHistory: () -> Unit
) {

    var isAnimationStarted by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        isAnimationStarted = true
    }

    Column(
        modifier = Modifier
            .windowInsetsPadding(WindowInsets.statusBars)
            .background(Theme.color.surfaces.surface)
    ) {
        CustomAppBar(
            title = stringResource(Res.string.workout_report),
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
                ActivityRow(
                    onMenuClicked = listener::onDropDownMenuClicked
                )
            }
            item {
                DashboardGridSection(
                    screenState = screenState,
                    onDropDownMenuItemClicked = listener::onDropDownMenuItemClicked,
                    onDropDownMenuDismiss = listener::onDropDownMenuDismiss,
                    isAnimationStarted = isAnimationStarted
                )
            }
            item {
                BarChartCard(
                    modifier = Modifier.padding(top = 16.dp),
                    data = screenState.report.workoutPerWeek.workoutsCount.map { it.toFloat() },
                    labels = screenState.report.workoutPerWeek.day,
                    isAnimationStarted = isAnimationStarted
                )
            }
            item {
                LineChartCard(
                    modifier = Modifier.padding(top = 16.dp),
                    data = screenState.report.timeSpendPerWeek.time.map { it.toFloat() },
                    labels = screenState.report.timeSpendPerWeek.day,
                    totalTime = screenState.report.timeSpent,
                    isAnimationStarted = isAnimationStarted
                )
            }
            item {
                MusclesCard(
                    modifier = Modifier.padding(top = 16.dp),
                    musclesName = screenState.report.mostTrainedMuscles.muscle,
                    trainedMusclesPercentage = screenState.report.mostTrainedMuscles.percentage,
                    isAnimationStarted = isAnimationStarted
                )
            }
            item {
                HistoryWorkoutCard(
                    modifier = Modifier.padding(top = 16.dp),
                    onViewAllHistoryClicked = navigateToWorkoutHistory,
                )
            }
        }
    }
}

@Preview
@Composable
private fun ReportScreenPreview() {
    AppTheme(isDarkTheme = true) {
        ReportScreenContent(
            ReportScreenState(),
            navigateToWorkoutHistory = {},
            listener = object : ReportInteractionListener {
                override fun onViewAllHistoryWorkoutsClicked() {}
                override fun onShareClicked() {}
                override fun onDropDownMenuClicked() {}
                override fun onDropDownMenuDismiss() {}
                override fun onDropDownMenuItemClicked(item: String) {}
            }
        )
    }
}