package com.cairosquad.evolvefit.ui.screen.home

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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
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
fun HomeLoadingScreen() {
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
    HomeLoadingScreenContent(brush)
}

@Composable
private fun HomeLoadingScreenContent(shimmerBrush: Brush) {
    Column(
        modifier = Modifier
            .height(726.dp)
            .width(360.dp)
            .background(Theme.color.surfaces.surface)
            .padding(horizontal = 16.dp)
    ) {
        Profile(
            modifier = Modifier
                .windowInsetsPadding(WindowInsets.statusBars)
                .padding(bottom = 24.dp),
            shimmerBrush = shimmerBrush,
        )
        DailyProgressContent(shimmerBrush = shimmerBrush)
        TodayNutritionTitle(shimmerBrush = shimmerBrush)
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            CaloriesCard(shimmerBrush = shimmerBrush)
            WaterCard(shimmerBrush = shimmerBrush)
        }
        RecommendationTitle(shimmerBrush = shimmerBrush)
        WorkoutsForYou(shimmerBrush = shimmerBrush)
    }
}

@Composable
private fun Profile(
    shimmerBrush: Brush,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ShimmerOverlayShape(
            modifier = Modifier
                .size(40.dp),
            shimmerBrush = shimmerBrush,
        )
        Column {
            ShimmerOverlayShape(
                modifier = Modifier
                    .height(14.dp)
                    .width(120.dp),
                shimmerBrush = shimmerBrush
            )
            ShimmerOverlayShape(
                modifier = Modifier
                    .height(14.dp)
                    .width(200.dp)
                    .padding(top = 4.dp),
                shimmerBrush = shimmerBrush
            )
        }
    }
}

@Composable
private fun DailyProgressContent(
    shimmerBrush: Brush,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Theme.color.surfaces.surfaceContainer)
            .padding(horizontal = 12.dp, vertical = 16.dp)

    ) {
        UserProgressTitle(shimmerBrush = shimmerBrush)
        Column {
            DaysOfWeek(
                shimmerBrush = shimmerBrush,
                modifier = Modifier
                    .padding(top = 16.dp)
            )
            Row(
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                WeightGoalContent(shimmerBrush = shimmerBrush)
                WeightCurrentContent(shimmerBrush = shimmerBrush)
                ActivityContent(shimmerBrush = shimmerBrush)
            }
        }
    }
}

@Composable
private fun UserProgressTitle(
    shimmerBrush: Brush,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        ShimmerOverlayShape(
            modifier = Modifier
                .size(height = 19.dp, width = 98.dp),
            shimmerBrush = shimmerBrush
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        ShimmerOverlayShape(
            modifier = Modifier
                .size(height = 17.dp, width = 65.dp),
            shimmerBrush = shimmerBrush
        )
    }
}

@Composable
private fun DaysOfWeek(
    shimmerBrush: Brush,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(7) {
            ShimmerOverlayShape(
                modifier = Modifier.size(36.dp),
                shimmerBrush = shimmerBrush
            )
        }
    }
}

@Composable
private fun WeightGoalContent(
    shimmerBrush: Brush,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Column {
            ShimmerOverlayShape(
                modifier = Modifier
                    .size(height = 16.dp, width = 72.dp),
                shimmerBrush = shimmerBrush
            )
            ShimmerOverlayShape(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .size(height = 17.dp, width = 72.dp),
                shimmerBrush = shimmerBrush
            )
        }
        ShimmerOverlayShape(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .height(41.dp)
                .width(2.dp),
            shape = RoundedCornerShape(8.dp),
            shimmerBrush = shimmerBrush
        )
    }
}

@Composable
private fun WeightCurrentContent(
    shimmerBrush: Brush,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Column {
            ShimmerOverlayShape(
                modifier = Modifier
                    .size(height = 16.dp, width = 99.dp),
                shimmerBrush = shimmerBrush
            )
            ShimmerOverlayShape(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .size(height = 17.dp, width = 46.dp),
                shimmerBrush = shimmerBrush
            )
        }
        ShimmerOverlayShape(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .height(41.dp)
                .width(2.dp),
            shape = RoundedCornerShape(8.dp),
            shimmerBrush = shimmerBrush
        )
    }
}

@Composable
private fun ActivityContent(
    shimmerBrush: Brush,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        ShimmerOverlayShape(
            modifier = Modifier
                .size(height = 16.dp, width = 70.5.dp),
            shimmerBrush = shimmerBrush
        )
        ShimmerOverlayShape(
            modifier = Modifier
                .padding(top = 8.dp)
                .size(height = 17.dp, width = 32.dp),
            shimmerBrush = shimmerBrush
        )
    }
}

@Composable
private fun TodayNutritionTitle(
    shimmerBrush: Brush,
    modifier: Modifier = Modifier
) {
    ShimmerOverlayShape(
        modifier = modifier
            .padding(top = 32.dp, bottom = 12.dp)
            .size(height = 19.dp, width = 121.dp),
        shimmerBrush = shimmerBrush
    )
}

@Composable
private fun CaloriesCard(
    shimmerBrush: Brush,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .height(132.dp)
            .width(158.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Theme.color.surfaces.surfaceContainer)
            .padding(12.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            ShimmerOverlayShape(
                modifier = Modifier
                    .size(32.dp),
                shimmerBrush = shimmerBrush
            )
            ShimmerOverlayShape(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .size(height = 17.dp, width = 51.dp),
                shimmerBrush = shimmerBrush
            )
        }
        Row(
            modifier = Modifier
                .padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ShimmerOverlayShape(
                modifier = Modifier
                    .size(height = 14.dp, width = 32.dp),
                shimmerBrush = shimmerBrush
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
            ShimmerOverlayShape(
                modifier = Modifier
                    .size(height = 14.dp, width = 31.dp),
                shimmerBrush = shimmerBrush
            )
        }
        ShimmerOverlayShape(
            modifier = Modifier
                .padding(top = 8.dp)
                .size(height = 8.dp, width = 134.dp),
            shimmerBrush = shimmerBrush
        )
        ShimmerOverlayShape(
            modifier = Modifier
                .padding(top = 8.dp)
                .size(height = 14.dp, width = 119.dp),
            shimmerBrush = shimmerBrush
        )
    }
}

@Composable
private fun WaterCard(
    shimmerBrush: Brush,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .height(132.dp)
            .width(158.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Theme.color.surfaces.surfaceContainer)
            .padding(12.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            ShimmerOverlayShape(
                modifier = Modifier
                    .size(32.dp),
                shimmerBrush = shimmerBrush
            )
            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
            ) {
                ShimmerOverlayShape(
                    modifier = Modifier
                        .size(height = 17.dp, width = 51.dp),
                    shimmerBrush = shimmerBrush
                )
                ShimmerOverlayShape(
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .size(height = 17.dp, width = 74.dp),
                    shimmerBrush = shimmerBrush
                )
            }

        }
        Row(
            modifier = Modifier
                .padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ShimmerOverlayShape(
                modifier = Modifier
                    .size(height = 14.dp, width = 46.dp),
                shimmerBrush = shimmerBrush
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
            ShimmerOverlayShape(
                modifier = Modifier
                    .size(height = 14.dp, width = 46.dp),
                shimmerBrush = shimmerBrush
            )
        }
        ShimmerOverlayShape(
            modifier = Modifier
                .padding(top = 8.dp)
                .size(height = 8.dp, width = 134.dp),
            shimmerBrush = shimmerBrush
        )
        ShimmerOverlayShape(
            modifier = Modifier
                .padding(top = 8.dp)
                .size(height = 14.dp, width = 101.dp),
            shimmerBrush = shimmerBrush
        )
    }
}

@Composable
private fun RecommendationTitle(
    shimmerBrush: Brush,
    modifier: Modifier = Modifier
) {
    ShimmerOverlayShape(
        modifier = modifier
            .padding(top = 32.dp, bottom = 12.dp)
            .size(height = 19.dp, width = 86.dp),
        shimmerBrush = shimmerBrush
    )
}

@Composable
private fun WorkoutsForYou(
    shimmerBrush: Brush,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(5) {
            WorkoutCard(shimmerBrush = shimmerBrush)
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
            .width(268.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Theme.color.surfaces.surfaceContainer)
            .padding(12.dp)
    ) {
        ShimmerOverlayShape(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(40.dp),
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
                ShimmerOverlayShape(
                    modifier = Modifier
                        .size(height = 19.dp, width = 102.dp),
                    shimmerBrush = shimmerBrush
                )
                ShimmerOverlayShape(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .size(height = 14.dp, width = 96.dp),
                    shimmerBrush = shimmerBrush
                )
            }
        }

    }
}

@Preview
@Composable
private fun HomeLoadingScreenPreview() {
    AppTheme(isDarkTheme = true) {
        HomeLoadingScreen()
    }
}