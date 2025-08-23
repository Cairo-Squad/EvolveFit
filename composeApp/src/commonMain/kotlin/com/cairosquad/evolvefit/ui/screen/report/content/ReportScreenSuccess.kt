package com.cairosquad.evolvefit.ui.screen.report.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.ui.component.RefreshBox
import com.cairosquad.evolvefit.ui.screen.report.content.componant.ActivityRow
import com.cairosquad.evolvefit.ui.screen.report.content.componant.DashboardGridSection
import com.cairosquad.evolvefit.ui.screen.report.content.componant.cards.BarChartCard
import com.cairosquad.evolvefit.ui.screen.report.content.componant.cards.HistoryWorkoutCard
import com.cairosquad.evolvefit.ui.screen.report.content.componant.cards.LineChartCard
import com.cairosquad.evolvefit.ui.screen.report.content.componant.cards.MusclesCard
import com.cairosquad.evolvefit.viewmodel.report.ReportInteractionListener
import com.cairosquad.evolvefit.viewmodel.report.ReportScreenState

@Composable
fun ReportScreenSuccess(
    screenState: ReportScreenState,
    listener: ReportInteractionListener,
) {
    var isAnimationStarted by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        isAnimationStarted = true
    }
    RefreshBox(
        isRefreshing = screenState.isRefreshing,
        onRefresh = listener::onRefresh
    ) {
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                ActivityRow(
                    screenState = screenState,
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
            if (screenState.report.workoutPerWeek.day.isNotEmpty()) {
                item {
                    BarChartCard(
                        data = screenState.report.workoutPerWeek.workoutsCount.map { it.toFloat() },
                        labels = screenState.report.workoutPerWeek.day,
                        isAnimationStarted = isAnimationStarted
                    )
                }
            }
            if (screenState.report.timeSpentPerWeek.day.isNotEmpty()) {
                item {
                    LineChartCard(
                        data = screenState.report.timeSpentPerWeek.timeInSeconds.map { it.toFloat() },
                        labels = screenState.report.timeSpentPerWeek.day,
                        totalTime = screenState.report.timeSpent,
                        isAnimationStarted = isAnimationStarted
                    )
                }
            }
            if (screenState.report.mostTrainedMuscles.muscle.isNotEmpty()) {
                item {
                    MusclesCard(
                        musclesName = screenState.report.mostTrainedMuscles.muscle,
                        trainedMusclesPercentage = screenState.report.mostTrainedMuscles.percentage,
                        isAnimationStarted = isAnimationStarted
                    )
                }
            }
            if (screenState.workoutHistory.isNotEmpty()) {
                item {
                    HistoryWorkoutCard(
                        state = screenState,
                        onViewAllHistoryClicked = listener::onViewAllHistoryWorkoutsClicked,
                    )
                }
            }
        }
    }
}