package com.cairosquad.evolvefit.ui.screen.nutrition.component

import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.component.ShimmerOverlayShape
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.shimmer_loading
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun NutritionLoadingScreen() {
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
        item { NutritionSummaryCard(shimmerBrush = brush) }
        item { TodayMealsSummary(shimmerBrush = brush) }
        item { SuggestedMeals(shimmerBrush = brush) }
        mealHistorySection(shimmerBrush = brush)
    }
}

@Composable
private fun NutritionSummaryCard(
    shimmerBrush: Brush,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        DailySummaryCard(
            modifier = Modifier
                .weight(1f)
                .padding(end = 12.dp),
            shimmerBrush = shimmerBrush,
            isWaterCard = false
        )
        DailySummaryCard(
            modifier = Modifier.weight(1f),
            shimmerBrush = shimmerBrush,
            isWaterCard = true
        )
    }
}

@Composable
private fun DailySummaryCard(
    shimmerBrush: Brush,
    modifier: Modifier = Modifier,
    isWaterCard: Boolean = false
) {
    Column(
        modifier = modifier
            .height(178.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Theme.color.surfaces.surfaceContainer)
            .padding(horizontal = 8.dp, vertical = 12.dp),
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            ShimmerOverlayShape(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .size(height = 20.dp, width = 79.dp),
                shimmerBrush = shimmerBrush
            )
            Row(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(80.dp)
                    .clip(CircleShape)
                    .border(
                        width = 8.dp,
                        color = Theme.color.surfaces.surfaceVariant,
                        shape = CircleShape
                    )
                    .background(Theme.color.surfaces.surfaceContainer),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                ShimmerOverlayShape(
                    modifier = Modifier
                        .size(height = 19.dp, width = 32.dp),
                    shimmerBrush = shimmerBrush
                )
            }
            ShimmerOverlayShape(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(bottom = 9.dp)
                    .size(height = 14.dp, width = 97.dp),
                shimmerBrush = shimmerBrush
            )
            if (isWaterCard) {
                ShimmerOverlayShape(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .size(32.dp),
                    shimmerBrush = shimmerBrush
                )
            }
        }
    }
}

@Composable
private fun TodayMealsSummary(
    shimmerBrush: Brush,
    modifier: Modifier = Modifier
) {
    Box() {
        Column(modifier = modifier) {
            TodayMealsTitle(shimmerBrush = shimmerBrush)
            Column(
                modifier = modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(Theme.color.surfaces.surfaceContainer)
                    .padding(vertical = 16.dp, horizontal = 12.dp)
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(76.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 150.dp),
                    userScrollEnabled = false
                ) {
                    items(4) { meal ->
                        TodayMealItem(
                            shimmerBrush = shimmerBrush
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .padding(top = 20.dp, bottom = 8.dp)
                        .fillMaxWidth()
                        .height(1.dp)
                        .clip(CircleShape)
                        .background(Theme.color.surfaces.outlineVariant)
                ) { }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        ShimmerOverlayShape(
                            modifier = Modifier
                                .size(height = 17.dp, width = 65.dp),
                            shimmerBrush = shimmerBrush
                        )
                        ShimmerOverlayShape(
                            modifier = Modifier
                                .padding(top = 4.dp)
                                .size(height = 14.dp, width = 45.dp),
                            shimmerBrush = shimmerBrush
                        )
                    }
                    ShimmerOverlayShape(
                        modifier = Modifier
                            .size(height = 20.dp, width = 88.dp),
                        shimmerBrush = shimmerBrush
                    )
                }
            }
        }
    }
}

@Composable
private fun TodayMealItem(
    shimmerBrush: Brush,
    modifier: Modifier = Modifier
) {
    Box() {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ShimmerOverlayShape(
                modifier = Modifier
                    .size(48.dp),
                shimmerBrush = shimmerBrush
            )
            ShimmerOverlayShape(
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 4.dp)
                    .size(height = 17.dp, width = 38.dp),
                shimmerBrush = shimmerBrush
            )
            ShimmerOverlayShape(
                modifier = Modifier
                    .size(height = 14.dp, width = 45.dp),
                shimmerBrush = shimmerBrush
            )

        }
    }
}

@Composable
fun TodayMealsTitle(
    shimmerBrush: Brush,
    modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 32.dp, bottom = 12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            ShimmerOverlayShape(
                modifier = Modifier
                    .size(height = 19.dp, width = 98.dp),
                shimmerBrush = shimmerBrush
            )
        }
    }
}

@Composable
private fun SuggestedMeals(
    shimmerBrush: Brush,
    modifier: Modifier = Modifier
) {
    SeeAll(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 32.dp, bottom = 12.dp),
        shimmerBackground = shimmerBrush
    )
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(20) {
            SuggestedMealCard(
                shimmerBrush = shimmerBrush
            )
        }
    }
}

@Composable
private fun SuggestedMealCard(
    shimmerBrush: Brush,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        ShimmerOverlayShape(
            modifier = Modifier
                .size(height = 124.dp, width = 158.dp),
            shape = RoundedCornerShape(8.dp),
            background = Theme.color.surfaces.surfaceVariant,
            shimmerBrush = shimmerBrush
        )
        // TODO remove shimmer or row after opinion designer
//        Row(
//            modifier = Modifier
//                .size(height = 124.dp, width = 158.dp)
//                .clip(RoundedCornerShape(8.dp))
//                .background(Theme.color.surfaces.surfaceVariant)
//        ) {}
        ShimmerOverlayShape(
            modifier = Modifier
                .padding(top = 8.dp, bottom = 4.dp)
                .size(height = 17.dp, width = 78.dp),
            shimmerBrush = shimmerBrush
        )
        ShimmerOverlayShape(
            modifier = Modifier
                .size(height = 14.dp, width = 36.dp),
            shimmerBrush = shimmerBrush
        )
    }
}

private fun LazyListScope.mealHistorySection(
    shimmerBrush: Brush,
) {
    item {
        SeeAll(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 32.dp, bottom = 12.dp),
            shimmerBackground = shimmerBrush
        )
    }
    items(30) { mealHistory ->
        MealHistoryItem(shimmerBrush = shimmerBrush)
    }
}

@Composable
fun MealHistoryItem(
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
                    .size(height = 17.dp, width = 53.dp),
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

@Composable
private fun SeeAll(
    shimmerBackground: Brush,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ShimmerOverlayShape(
            modifier = Modifier
                .size(height = 19.dp, width = 119.dp),
            shimmerBrush = shimmerBackground
        )
        ShimmerOverlayShape(
            modifier = Modifier
                .size(height = 17.dp, width = 71.dp),
            shimmerBrush = shimmerBackground
        )
    }
}

@Preview
@Composable
private fun LoadingNutritionScreenPrev() {
    AppTheme(isDarkTheme = true) {
        NutritionLoadingScreen()
    }
}