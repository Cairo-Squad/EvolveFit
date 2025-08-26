package com.cairosquad.evolvefit.ui.screen.mealsHistory.content.components

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
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.appbar.ActionIconButton
import com.cairosquad.evolvefit.design_system.component.appbar.CustomAppBar
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.component.ShimmerOverlayShape
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.back
import evolvefit.composeapp.generated.resources.ic_back
import evolvefit.composeapp.generated.resources.meal_history
import evolvefit.composeapp.generated.resources.shimmer_loading
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MealsHistoryLoadingScreen() {
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
    MealHistoryLoadingContent(shimmerBrush = brush)
}

@Composable
private fun MealHistoryLoadingContent(shimmerBrush: Brush) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.color.surfaces.surface)
    ) {
        CustomAppBar(
            title = stringResource(Res.string.meal_history),
            modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars)
                .padding(horizontal = 16.dp),
            header = {
                ActionIconButton(
                    icon = painterResource(Res.drawable.ic_back),
                    contentDescription = stringResource(Res.string.back),
                    tint = Theme.color.surfaces.onSurface,
                    onClick = {}
                )
            }

        )
        LazyColumn {
            items(5) { meal ->
                GroupedMealHistoryItem(
                    shimmerBrush = shimmerBrush
                )
            }
        }
    }
}

@Composable
private fun GroupedMealHistoryItem(
    shimmerBrush: Brush,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        ShimmerOverlayShape(
            modifier = Modifier
                .padding(bottom = 8.dp, top = 16.dp)
                .padding(horizontal = 16.dp)
                .size(height = 19.dp, width = 71.dp),
            shimmerBrush = shimmerBrush
        )
        Column {
            repeat((0..3).count()) {
                MealHistoryItem(
                    shimmerBrush = shimmerBrush
                )
            }
        }
    }
}

@Composable
private fun MealHistoryItem(
    shimmerBrush: Brush,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ShimmerOverlayShape(
            modifier = Modifier
                .size(48.dp),
            shimmerBrush = shimmerBrush
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 12.dp),
            verticalArrangement = Arrangement.Center
        ) {
            ShimmerOverlayShape(
                modifier = Modifier
                    .size(height = 17.dp, width = 123.dp),
                shimmerBrush = shimmerBrush
            )
            ShimmerOverlayShape(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .size(height = 14.dp, width = 83.dp),
                shimmerBrush = shimmerBrush
            )
        }
        Column(
            horizontalAlignment = Alignment.End
        ) {
            ShimmerOverlayShape(
                modifier = Modifier
                    .size(height = 17.dp, width = 51.dp),
                shimmerBrush = shimmerBrush
            )
            ShimmerOverlayShape(
                modifier = Modifier
                    .padding(top = 4.dp)
                    .size(height = 12.dp, width = 42.dp),
                shimmerBrush = shimmerBrush
            )
        }
    }
}

@Preview
@Composable
private fun MealHistoryLoadingScreenPreview() {
    AppTheme(isDarkTheme = true) {
        MealsHistoryLoadingScreen()
    }
}