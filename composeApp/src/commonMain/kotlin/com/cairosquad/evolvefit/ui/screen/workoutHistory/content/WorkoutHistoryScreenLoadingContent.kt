package com.cairosquad.evolvefit.ui.screen.workoutHistory.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.screen.report.content.componant.cards.LoadingHistoryWorkoutCard

fun LazyListScope.workoutHistoryScreenLoadingContent() {
    items(10) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ){
            Box(
                modifier = Modifier.size(37.dp, 17.dp)
                    .clip(CircleShape)
                    .background(Theme.color.surfaces.surfaceContainer),
            )
            LoadingHistoryWorkoutCard()
        }
    }
}
