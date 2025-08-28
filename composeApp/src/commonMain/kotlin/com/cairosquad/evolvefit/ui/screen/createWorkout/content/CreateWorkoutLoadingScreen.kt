package com.cairosquad.evolvefit.ui.screen.createWorkout.content

import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.component.ShimmerOverlayShape
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.shimmer_loading
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CreateWorkoutLoadingScreen() {
    val infiniteTransition =
        rememberInfiniteTransition(label = stringResource(Res.string.shimmer_loading))

    val shimmerEffectAnimation by infiniteTransition.animateFloat(
        initialValue = -200f, targetValue = 500f, animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1500, easing = EaseInOut),
            repeatMode = RepeatMode.Restart
        )
    )
    val brush = Brush.linearGradient(
        colors = Theme.color.gradiant.shimmerGradientColors,
        start = Offset(shimmerEffectAnimation, shimmerEffectAnimation),
        end = Offset(shimmerEffectAnimation + 190f, shimmerEffectAnimation + 190f)
    )
    CreateWorkoutLoadingScreenContent(shimmerBrush = brush)
}

@Composable
private fun CreateWorkoutLoadingScreenContent(shimmerBrush: Brush) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.color.surfaces.surface)
            .padding(horizontal = 16.dp)
    ) {
        ShimmerOverlayShape(
            modifier = Modifier
                .padding(top = 16.dp)
                .size(height = 17.dp, width = 80.dp),
            background = Theme.color.surfaces.surfaceContainer,
            shimmerBrush = shimmerBrush
        )
        LazyColumn(
            modifier = Modifier
                .padding(top = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(20) {
                WorkoutItem(shimmerBrush = shimmerBrush)
            }
        }
    }
}

@Composable
private fun WorkoutItem(
    shimmerBrush: Brush,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        WorkoutImage()
        WorkoutInformation(
            modifier = Modifier
                .padding(vertical = 11.5.dp)
                .padding(start = 12.dp).weight(1f),
            shimmerBrush = shimmerBrush
        )
        ShimmerOverlayShape(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .size(24.dp),
            background = Theme.color.surfaces.surfaceContainer,
            shimmerBrush = shimmerBrush
        )
    }
}

@Composable
private fun WorkoutImage(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .size(height = 68.dp, width = 88.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Theme.color.surfaces.surfaceContainer),
    ) {}
}

@Composable
private fun WorkoutInformation(
    shimmerBrush: Brush,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        ShimmerOverlayShape(
            modifier = Modifier
                .size(height = 17.dp, width = 120.dp),
            background = Theme.color.surfaces.surfaceContainer,
            shimmerBrush = shimmerBrush
        )
        Row(
            modifier = Modifier
                .padding(top = 12.dp)
        ) {
            ShimmerOverlayShape(
                modifier = Modifier
                    .size(16.dp),
                background = Theme.color.surfaces.surfaceContainer,
                shimmerBrush = shimmerBrush
            )
            ShimmerOverlayShape(
                modifier = Modifier
                    .padding(start = 4.dp)
                    .size(height = 14.dp, width = 56.dp),
                background = Theme.color.surfaces.surfaceContainer,
                shimmerBrush = shimmerBrush
            )
        }
    }
}

@Preview
@Composable
private fun CreateWorkoutLoadingScreenPreview() {
    AppTheme(isDarkTheme = true) {
        CreateWorkoutLoadingScreen()
    }
}