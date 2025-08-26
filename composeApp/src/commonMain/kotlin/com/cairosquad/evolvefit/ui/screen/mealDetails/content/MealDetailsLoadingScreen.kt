package com.cairosquad.evolvefit.ui.screen.mealDetails.content

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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import evolvefit.composeapp.generated.resources.bookmark
import evolvefit.composeapp.generated.resources.ic_back
import evolvefit.composeapp.generated.resources.ic_bookmark_big
import evolvefit.composeapp.generated.resources.ic_default_image
import evolvefit.composeapp.generated.resources.placeholder_image
import evolvefit.composeapp.generated.resources.shimmer_loading
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MealDetailsLoadingScreen() {
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.color.surfaces.surface)
    ) {
        MealDetailsLoadingContent(shimmerBrush = brush)
    }
}

@Composable
private fun MealDetailsLoadingContent(shimmerBrush: Brush) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        MealImagePlaceholder()
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = 307.dp)
                .background(
                    Theme.color.surfaces.surface,
                    shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                )
                .padding(horizontal = 16.dp).padding(bottom = 16.dp)
        ) {
            Column {
                MealDescription(
                    modifier = Modifier.padding(top = 24.dp),
                    shimmerBrush = shimmerBrush
                )
                NutritionalInfo(
                    modifier = Modifier.padding(top = 20.dp),
                    shimmerBrush = shimmerBrush
                )
                IngredientsList(
                    shimmerBrush = shimmerBrush
                )
            }
        }
    }
}

@Composable
private fun MealImagePlaceholder(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(331.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Theme.color.surfaces.surfaceContainer),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier.size(height = 74.dp, width = 96.dp),
                painter = painterResource(Res.drawable.ic_default_image),
                contentDescription =
                    stringResource(Res.string.placeholder_image),
                tint = Theme.color.surfaces.onSurfaceVariant
            )
        }
        MealDetailsAppBar()
    }
}

@Composable
private fun MealDetailsAppBar() {
    CustomAppBar(
        modifier = Modifier
            .windowInsetsPadding(WindowInsets.statusBars)
            .padding(horizontal = 16.dp),
        title = "",
        header = {
            ActionIconButton(
                icon = painterResource(Res.drawable.ic_back),
                contentDescription = stringResource(Res.string.back),
                tint = Theme.color.surfaces.textColor,
                onClick = {}
            )
        },
        tail = {
            IconButton(
                onClick = {}
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_bookmark_big),
                    contentDescription = stringResource(Res.string.bookmark),
                    tint = Theme.color.surfaces.textColor
                )
            }

        }
    )
}

@Composable
private fun MealDescription(
    shimmerBrush: Brush,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        MealTitle(shimmerBrush = shimmerBrush)
        ShimmerOverlayShape(
            modifier = Modifier
                .padding(top = 12.dp)
                .size(height = 17.dp, width = 299.dp),
            shimmerBrush = shimmerBrush
        )
        ShimmerOverlayShape(
            modifier = Modifier
                .padding(top = 8.dp)
                .size(height = 17.dp, width = 136.dp),
            shimmerBrush = shimmerBrush
        )
    }

}

@Composable
private fun MealTitle(
    shimmerBrush: Brush,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        ShimmerOverlayShape(
            modifier = Modifier
                .size(height = 22.dp, width = 100.dp),
            shimmerBrush = shimmerBrush
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        ShimmerOverlayShape(
            modifier = Modifier
                .size(height = 17.dp, width = 43.dp),
            shimmerBrush = shimmerBrush
        )
    }
}

@Composable
private fun NutritionalInfo(
    shimmerBrush: Brush,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(4) {
            NutritionalItem(
                shimmerBrush = shimmerBrush
            )
        }
    }
}

@Composable
private fun NutritionalItem(
    shimmerBrush: Brush,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.width(165.dp)
    ) {
        ShimmerOverlayShape(
            modifier = Modifier
                .size(32.dp),
            shape = RoundedCornerShape(8.dp),
            shimmerBrush = shimmerBrush
        )
        ShimmerOverlayShape(
            modifier = Modifier
                .padding(start = 8.dp)
                .size(height = 14.dp, width = 71.dp),
            shimmerBrush = shimmerBrush
        )
    }
}

@Composable
private fun IngredientsList(
    shimmerBrush: Brush,
    modifier: Modifier = Modifier
) {
    val ingredientWidths = listOf(222.dp, 213.dp, 328.dp, 311.dp, 200.dp, 259.dp, 308.dp)
    Column(modifier = modifier) {
        ShimmerOverlayShape(
            modifier = Modifier
                .padding(top = 32.dp)
                .size(height = 19.dp, width = 80.dp),
            shimmerBrush = shimmerBrush
        )
        Spacer(modifier = Modifier.height(12.dp))
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(ingredientWidths) { width ->
                IngredientsItem(
                    modifier = Modifier.height(17.dp).width(width),
                    shimmerBrush = shimmerBrush
                )
            }
        }
    }
}

@Composable
private fun IngredientsItem(
    shimmerBrush: Brush,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ShimmerOverlayShape(
            modifier = Modifier
                .height(14.dp)
                .fillMaxWidth(),
            shimmerBrush = shimmerBrush
        )
    }
}

@Preview
@Composable
private fun MealDetailsLoadingScreenPreview() {
    AppTheme(isDarkTheme = true) {
        MealDetailsLoadingScreen()
    }
}