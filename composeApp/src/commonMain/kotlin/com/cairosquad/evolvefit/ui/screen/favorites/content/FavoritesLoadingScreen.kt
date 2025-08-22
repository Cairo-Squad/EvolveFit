package com.cairosquad.evolvefit.ui.screen.favorites.content

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
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
fun FavoritesLoadingScreen() {
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
    FavoritesLoadingScreenContent(brush)
}

@Composable
private fun FavoritesLoadingScreenContent(
    shimmerBrush: Brush,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Theme.color.surfaces.surface)
            .padding(horizontal = 16.dp)
    ) {
        FavoritesWorkouts(shimmerBrush)
    }
}

@Composable
private fun FavoritesWorkouts(
    shimmerBrush: Brush,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(10) {
            WorkoutCard(shimmerBrush)
        }
    }
}

@Composable
private fun WorkoutCard(
    shimmerBrush: Brush,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(172.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Theme.color.surfaces.surfaceContainer)
            .padding(12.dp)
    ) {
        FavoriteIcon(
            modifier = Modifier
                .align(Alignment.TopEnd),
            shimmerBrush = shimmerBrush
        )
        Row(
            modifier = modifier
                .align(Alignment.BottomStart)
        ) {
            ShimmerOverlayShape(
                modifier = Modifier
                    .padding(end = 12.dp)
                    .height(51.dp)
                    .width(2.dp),
                shape = RoundedCornerShape(8.dp),
                shimmerBrush = shimmerBrush
            )
            Column {
                WorkoutName(shimmerBrush = shimmerBrush)
                WorkoutDetails(shimmerBrush = shimmerBrush)
            }
        }
    }
}

@Composable
fun WorkoutDetails(
    shimmerBrush: Brush,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(top = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ShimmerOverlayShape(
            modifier = Modifier
                .padding(vertical = 4.dp)
                .size(height = 14.dp, width = 50.dp),
            shimmerBrush = shimmerBrush
        )
        ShimmerOverlayShape(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .height(16.dp)
                .width(2.dp),
            shimmerBrush = shimmerBrush
        )
        ShimmerOverlayShape(
            modifier = Modifier
                .padding(vertical = 4.dp)
                .size(height = 14.dp, width = 45.dp),
            shimmerBrush = shimmerBrush
        )
    }
}

@Composable
private fun WorkoutName(
    shimmerBrush: Brush,
    modifier: Modifier = Modifier
) {
    ShimmerOverlayShape(
        modifier = modifier
            .size(height = 19.dp, width = 102.dp),
        shimmerBrush = shimmerBrush
    )
}

@Composable
private fun FavoriteIcon(
    shimmerBrush: Brush,
    modifier: Modifier = Modifier
) {
    ShimmerOverlayShape(
        modifier = modifier
            .size(40.dp),
        shimmerBrush = shimmerBrush
    )
}

@Preview
@Composable
private fun FavoritesLoadingScreenPreview() {
    AppTheme(isDarkTheme = true) {
        FavoritesLoadingScreen()
    }
}