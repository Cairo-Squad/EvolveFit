package com.cairosquad.evolvefit.ui.screen.report.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.screen.report.content.componant.DashboardGridLoading

@Composable
fun ReportScreenLoading() {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            DashboardGridLoading()
        }
        item {
            Box(
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(Theme.color.surfaces.surfaceContainer)
                    .padding(horizontal = 12.dp, vertical = 16.dp)
            )
        }
        item {
            Box(
                modifier = Modifier
                    .height(108.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(Theme.color.surfaces.surfaceContainer)
                    .padding(horizontal = 12.dp, vertical = 16.dp)
            )
        }
        item {
            Box(
                modifier = Modifier
                    .height(108.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(Theme.color.surfaces.surfaceContainer)
                    .padding(horizontal = 12.dp, vertical = 16.dp)
            )
        }
        item {
            Box(
                modifier = Modifier
                    .height(108.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(Theme.color.surfaces.surfaceContainer)
                    .padding(horizontal = 12.dp, vertical = 16.dp)
            )
        }
    }
}