package com.cairosquad.evolvefit.ui.screen.report.content.componant

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme

@Composable
fun DashboardGridLoading(
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
                    Box(
                        modifier = Modifier
                            .height(127.dp)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(Theme.color.surfaces.surfaceContainer)
                            .padding(horizontal = 12.dp, vertical = 16.dp)
                    )
                    Box(
                        modifier = Modifier
                            .height(127.dp)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(Theme.color.surfaces.surfaceContainer)
                            .padding(horizontal = 12.dp, vertical = 16.dp)
                    )
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(262.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Theme.color.surfaces.surfaceContainer)
                )
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(262.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Theme.color.surfaces.surfaceContainer),
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
                    Box(
                        modifier = Modifier
                            .height(127.dp)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(Theme.color.surfaces.surfaceContainer)
                            .padding(horizontal = 12.dp, vertical = 16.dp)
                    )
                }
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(214.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Theme.color.surfaces.surfaceContainer),
                    )
                }
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(214.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Theme.color.surfaces.surfaceContainer),
                    )
                }
                item {
                    Box(
                        modifier = Modifier
                            .height(127.dp)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(Theme.color.surfaces.surfaceContainer)
                            .padding(horizontal = 12.dp, vertical = 16.dp)
                    )
                }
            }
        }
    }
}