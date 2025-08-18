package com.cairosquad.evolvefit.ui.screen.report.componant.cards

import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.shimmer_loading
import org.jetbrains.compose.resources.stringResource

@Composable
fun LoadingHistoryWorkoutCard() {
    val infiniteTransition =
        rememberInfiniteTransition(label = stringResource(Res.string.shimmer_loading))

    val shimmerEffectAnimation by infiniteTransition.animateFloat(
        initialValue = -200f,
        targetValue = 500f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1200, easing = EaseInOut),
            repeatMode = RepeatMode.Restart
        )
    )
    val brush = Brush.linearGradient(
        colors = Theme.color.gradiant.loadingGradientColors,
        start = Offset(shimmerEffectAnimation, shimmerEffectAnimation),
        end = Offset(shimmerEffectAnimation + 190f, shimmerEffectAnimation + 190f)
    )

    Box {
        Column(
            modifier = Modifier.background(
                Theme.color.surfaces.surfaceContainer,
                shape = RoundedCornerShape(8.dp)
            ).padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(width = 68.dp, height = 52.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(Theme.color.surfaces.surfaceVariant)
                )
                BasicDetailsLoading()
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                WorkoutDetails()
            }
        }
        Box(
            modifier = Modifier.height(123.dp)
                .fillMaxWidth()
                .background(brush, RoundedCornerShape(8.dp))
        )
    }
}

@Composable
private fun RowScope.WorkoutDetails() {
    Column(
        modifier = Modifier.weight(1f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Box(
            modifier = Modifier
                .size(width = 49.dp, height = 14.dp)
                .clip(CircleShape)
                .background(Theme.color.surfaces.surfaceVariant)
        )
        Box(
            modifier = Modifier
                .size(width = 17.dp, height = 17.dp)
                .clip(CircleShape)
                .background(Theme.color.surfaces.surfaceVariant)
        )
    }
    Column(
        modifier = Modifier.weight(1f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Box(
            modifier = Modifier
                .size(width = 47.dp, height = 14.dp)
                .clip(CircleShape)
                .background(Theme.color.surfaces.surfaceVariant)
        )
        Box(
            modifier = Modifier
                .size(width = 43.dp, height = 17.dp)
                .clip(CircleShape)
                .background(Theme.color.surfaces.surfaceVariant)
        )
    }
    Column(
        modifier = Modifier.weight(1f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Box(
            modifier = Modifier
                .size(width = 28.dp, height = 14.dp)
                .clip(CircleShape)
                .background(Theme.color.surfaces.surfaceVariant)
        )
        Box(
            modifier = Modifier
                .size(width = 57.dp, height = 17.dp)
                .clip(CircleShape)
                .background(Theme.color.surfaces.surfaceVariant)
        )
    }
}

@Composable
private fun BasicDetailsLoading() {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier
                .size(width = 136.dp, height = 19.dp)
                .clip(CircleShape)
                .background(Theme.color.surfaces.surfaceVariant)
        )
        Box(
            modifier = Modifier
                .size(width = 83.dp, height = 14.dp)
                .clip(CircleShape)
                .background(Theme.color.surfaces.surfaceVariant)
        )
    }
}