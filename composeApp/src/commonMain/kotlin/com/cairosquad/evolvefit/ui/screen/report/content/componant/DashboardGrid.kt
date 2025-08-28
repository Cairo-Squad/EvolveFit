package com.cairosquad.evolvefit.ui.screen.report.content.componant

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.ui.screen.report.content.componant.cards.CaloriesCard
import com.cairosquad.evolvefit.ui.screen.report.content.componant.cards.TimeSpendCard
import com.cairosquad.evolvefit.ui.screen.report.content.componant.cards.TotalWorkoutCard
import com.cairosquad.evolvefit.ui.screen.report.content.componant.cards.WaterCard
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun DashboardGrid(
    isAnimationStarted: Boolean,
    timeSpent: String,
    waterConsumed: Float,
    totalWorkout: String,
    expectedCalories: Int,
    takenCalories: Int,
    modifier: Modifier = Modifier,
) {
    BoxWithConstraints(
        modifier = modifier
    ) {
        val isWide = maxWidth > 487.dp
        if (isWide) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    TimeSpendCard(timeSpent)
                    TotalWorkoutCard(totalWorkout)
                }
                CaloriesCard(
                    modifier = Modifier
                        .weight(1f)
                        .height(270.dp),
                    expectedCalories = expectedCalories,
                    takenCalories = takenCalories,
                    isAnimationStarted = isAnimationStarted
                )
                WaterCard(
                    modifier = Modifier
                        .weight(1f)
                        .height(270.dp),
                    waterConsumed = waterConsumed,
                    isAnimationStarted = isAnimationStarted,
                )
            }
        } else {
            LazyVerticalStaggeredGrid(
                modifier = Modifier.heightIn(min = 349.dp, max = 800.dp),
                columns = StaggeredGridCells.Adaptive(160.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalItemSpacing = 8.dp,
                userScrollEnabled = false
            ) {
                item {
                    TimeSpendCard(timeSpent)
                }
                item {
                    CaloriesCard(
                        modifier = Modifier.height(214.dp),
                        expectedCalories = expectedCalories,
                        takenCalories = takenCalories,
                        isAnimationStarted = isAnimationStarted
                    )
                }
                item {
                    WaterCard(
                        modifier = Modifier.height(214.dp),
                        waterConsumed = waterConsumed,
                        isAnimationStarted = isAnimationStarted
                    )
                }
                item {
                    TotalWorkoutCard(totalWorkout)
                }
            }
        }
    }
}

@Preview
@Composable
private fun DashboardGridPreview() {
    AppTheme {
        DashboardGrid(
            isAnimationStarted = true,
            timeSpent = "2hr",
            waterConsumed = 4f,
            totalWorkout = "16",
            expectedCalories = 3400,
            takenCalories = 1234,
        )
    }
}