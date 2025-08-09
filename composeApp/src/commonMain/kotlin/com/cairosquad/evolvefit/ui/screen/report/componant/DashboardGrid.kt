package com.cairosquad.evolvefit.ui.screen.report.componant

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
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun DashboardGrid(
    modifier: Modifier = Modifier
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
                    TimeSpendCard()
                    TotalWorkoutCard()
                }
                WaterCard(
                    modifier = Modifier.weight(1f)
                        .height(262.dp)
                )
                CaloriesCard(
                    modifier = Modifier.weight(1f)
                        .height(262.dp)
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
                    TimeSpendCard()
                }
                item {
                    WaterCard(
                        modifier = Modifier.height(214.dp)
                    )
                }
                item {
                    CaloriesCard(
                        modifier = Modifier.height(214.dp)
                    )
                }
                item {
                    TotalWorkoutCard()
                }
            }
        }
    }
}

@Preview
@Composable
private fun DashboardGridPreview() {
    AppTheme {
        DashboardGrid()
    }
}