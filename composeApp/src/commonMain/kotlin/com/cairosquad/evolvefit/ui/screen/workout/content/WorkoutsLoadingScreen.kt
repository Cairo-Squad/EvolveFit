package com.cairosquad.evolvefit.ui.screen.workout.content

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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
fun WorkoutsLoadingScreen(
    isItemsLoading: Boolean,
) {
    val infiniteTransition =
        rememberInfiniteTransition(label = stringResource(Res.string.shimmer_loading))

    val shimmerEffectAnimation by infiniteTransition.animateFloat(
        initialValue = -200f,
        targetValue = 500f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1500, easing = EaseInOut),
            repeatMode = RepeatMode.Restart
        )
    )
    val brush = Brush.linearGradient(
        colors = Theme.color.gradiant.shimmerGradientColors,
        start = Offset(shimmerEffectAnimation, shimmerEffectAnimation),
        end = Offset(shimmerEffectAnimation + 190f, shimmerEffectAnimation + 190f)
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.color.surfaces.surface),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isItemsLoading) {
            item {
                CategoriesShimmerRow(shimmerBrush = brush)
            }
            item {
                WorkoutsShimmerList(shimmerBrush = brush)
            }
        } else {
            item {
                WorkoutsShimmerList(shimmerBrush = brush)
            }
        }
    }
}

@Composable
private fun CategoriesShimmerRow(shimmerBrush: Brush) {
    LazyRow(
        modifier = Modifier
            .padding(top = 12.dp, bottom = 24.dp, start = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(30) {
            ShimmerOverlayShape(
                modifier = Modifier
                    .size(height = 40.dp, width = 68.dp),
                background = Theme.color.surfaces.surfaceContainer,
                shimmerBrush = shimmerBrush
            )
        }
    }
}

@Composable
private fun WorkoutsShimmerList(shimmerBrush: Brush) {
    LazyColumn(
        modifier = Modifier.heightIn(max = 10_000.dp).padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),

        ) {
        items(20) {
            WorkoutCardShimmered(shimmerBrush = shimmerBrush)
        }
    }
}

@Composable
private fun WorkoutCardShimmered(
    shimmerBrush: Brush,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(140.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Theme.color.surfaces.surfaceContainer)
    ) {
        BottomContent(
            modifier = Modifier
                .align(Alignment.BottomCenter),
            shimmerBrush = shimmerBrush
        )
    }
}

@Composable
private fun BottomContent(
    shimmerBrush: Brush,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(bottom = 12.dp)
            .padding(horizontal = 12.dp)
            .height(50.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        VerticalDividerShimmer(shimmerBrush)
        WorkoutInfoShimmer(
            modifier = Modifier
                .weight(1f),
            shimmerBrush = shimmerBrush
        )
        IconPlaceholderShimmer(shimmerBrush)
    }
}

@Composable
private fun VerticalDividerShimmer(shimmerBrush: Brush) {
    ShimmerOverlayShape(
        modifier = Modifier
            .fillMaxHeight()
            .width(2.dp),
        shimmerBrush = shimmerBrush
    )
}

@Composable
private fun WorkoutInfoShimmer(
    shimmerBrush: Brush,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(start = 12.dp)
            .padding(vertical = 4.dp)
            .fillMaxHeight()
    ) {
        ShimmerOverlayShape(
            modifier = Modifier
                .padding(bottom = 8.dp)
                .size(height = 19.dp, width = 102.dp),
            shimmerBrush = shimmerBrush
        )
        ShimmerOverlayShape(
            modifier = Modifier
                .size(height = 16.dp, width = 104.dp),
            shimmerBrush = shimmerBrush
        )
    }
}

@Composable
private fun IconPlaceholderShimmer(shimmerBrush: Brush) {
    Box(
        modifier = Modifier
            .padding(end = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        ShimmerOverlayShape(
            modifier = Modifier
                .size(33.3.dp),
            shimmerBrush = shimmerBrush
        )
    }
}

@Preview
@Composable
private fun WorkoutsLoadingScreenPreview(
) {
    AppTheme(isDarkTheme = true) {
        WorkoutsLoadingScreen(false)
    }
}