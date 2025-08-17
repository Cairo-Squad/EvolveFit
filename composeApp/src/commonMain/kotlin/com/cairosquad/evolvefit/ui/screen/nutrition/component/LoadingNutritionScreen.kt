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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.shimmer_loading
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun LoadingNutritionScreen() {
    val infiniteTransition =
        rememberInfiniteTransition(label = stringResource(Res.string.shimmer_loading))

    val shimmerEffectAnimation by infiniteTransition.animateFloat(
        initialValue = -200f,
        targetValue = 500f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 5000, easing = EaseInOut),
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
        item {
            NutritionSummaryCard(
                brush = brush
            )
        }
        item { TodayMealsSummary(brush = brush) }
        item { SuggestedMeals(brush = brush) }
        mealHistorySection(shimmerBackground = brush)
    }
}

@Composable
private fun NutritionSummaryCard(
    brush: Brush,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
        ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        DailySummaryCard(
            modifier = Modifier.weight(1f).padding(end = 12.dp),
            shimmerBackground = brush,
            isWaterCard = false
        )
        DailySummaryCard(
            modifier = Modifier.weight(1f),
            shimmerBackground = brush,
            isWaterCard = true
        )
    }
}

@Composable
private fun DailySummaryCard(
    shimmerBackground: Brush,
    modifier: Modifier = Modifier,
    isWaterCard: Boolean = false
) {
    Column(
        modifier = modifier
           .height(178.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Theme.color.surfaces.surfaceContainer)
            .padding(horizontal = 8.dp, vertical = 12.dp)
           ,
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            ShimmeredShape(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .size(height = 20.dp, width = 79.dp),
                shimmerBackground = shimmerBackground
            )
            Row(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(80.dp)
                    .clip(CircleShape)
                    .border(
                        width = 8.dp,
                        //brush = shimmerBackground,
                        color = Theme.color.surfaces.surfaceVariant,
                        shape = CircleShape
                    )
                    .background(Theme.color.surfaces.surfaceContainer),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                ShimmeredShape(
                    modifier = Modifier
                        .size(height = 19.dp, width = 32.dp),
                    shimmerBackground = shimmerBackground
                )
            }
            ShimmeredShape(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(bottom = 9.dp)
                    .size(height = 14.dp, width = 97.dp),
                shimmerBackground = shimmerBackground
            )
            if (isWaterCard) {
                ShimmeredShape(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .size(32.dp),
                    shimmerBackground = shimmerBackground
                )
            }

        }
    }
}

@Composable
private fun TodayMealsSummary(
    brush: Brush,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Row(
            modifier = modifier
                .padding(horizontal = 16.dp)
                .padding(top = 32.dp, bottom = 12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            ShimmeredShape(
                modifier = Modifier
                    .size(height = 19.dp, width = 98.dp),
                shimmerBackground = brush
            )
        }
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
                        shimmerBackground = brush
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
                    ShimmeredShape(
                        modifier = Modifier
                            .size(height = 17.dp, width = 65.dp),
                        shimmerBackground = brush
                    )
                    ShimmeredShape(
                        modifier = Modifier
                            .padding(top = 4.dp)
                            .size(height = 14.dp, width = 45.dp),
                        shimmerBackground = brush
                    )
                }
                ShimmeredShape(
                    modifier = Modifier
                        .size(height = 20.dp, width = 88.dp),
                    shimmerBackground = brush
                )
            }
        }
    }
}

@Composable
private fun TodayMealItem(
    shimmerBackground: Brush,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ShimmeredShape(
            modifier = Modifier
                .size(48.dp),
            shimmerBackground = shimmerBackground
        )
        ShimmeredShape(
            modifier = Modifier
                .padding(top = 8.dp, bottom = 4.dp)
                .size(height = 17.dp, width = 38.dp),
            shimmerBackground = shimmerBackground
        )
        ShimmeredShape(
            modifier = Modifier
                .size(height = 14.dp, width = 45.dp),
            shimmerBackground = shimmerBackground
        )

    }
}

@Composable
private fun SuggestedMeals(
    brush: Brush,
    modifier: Modifier = Modifier
) {
    SeeAll(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 32.dp, bottom = 12.dp),
        shimmerBackground = brush
    )
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(20) {
            SuggestedMealCard(
                shimmerBackground = brush
            )
        }
    }
}

@Composable
private fun SuggestedMealCard(
    shimmerBackground: Brush,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .size(height = 124.dp, width = 158.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Theme.color.surfaces.surfaceVariant)
        ) {}
        ShimmeredShape(
            modifier = Modifier
                .padding(top = 8.dp, bottom = 4.dp)
                .size(height = 17.dp, width = 78.dp),
            shimmerBackground = shimmerBackground
        )
        ShimmeredShape(
            modifier = Modifier
                .size(height = 14.dp, width = 36.dp),
            shimmerBackground = shimmerBackground
        )
    }
}

private fun LazyListScope.mealHistorySection(
    shimmerBackground: Brush,
) {
    item {
        SeeAll(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 32.dp, bottom = 12.dp),
            shimmerBackground = shimmerBackground
        )
    }
    items(30) { mealHistory ->
        MealHistoryItem(shimmerBackground = shimmerBackground)
    }
}

@Composable
fun MealHistoryItem(
    shimmerBackground: Brush,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(brush = shimmerBackground)
            .padding(vertical = 12.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ShimmeredShape(
            modifier = Modifier
                .size(48.dp),
            shimmerBackground = shimmerBackground
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 12.dp),
            verticalArrangement = Arrangement.Center
        ) {
            ShimmeredShape(
                modifier = Modifier
                    .size(height = 17.dp, width = 123.dp),
                shimmerBackground = shimmerBackground
            )
            ShimmeredShape(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .size(height = 14.dp, width = 83.dp),
                shimmerBackground = shimmerBackground
            )
        }
        Column(
            horizontalAlignment = Alignment.End
        ) {
            ShimmeredShape(
                modifier = Modifier
                    .size(height = 17.dp, width = 53.dp),
                shimmerBackground = shimmerBackground
            )
            ShimmeredShape(
                modifier = Modifier
                    .padding(top = 4.dp)
                    .size(height = 12.dp, width = 42.dp),
                shimmerBackground = shimmerBackground
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
        modifier = modifier.background(brush = shimmerBackground),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ShimmeredShape(
            modifier = Modifier
                .size(height = 19.dp, width = 119.dp),
            shimmerBackground = shimmerBackground
        )
        ShimmeredShape(
            modifier = Modifier
                .size(height = 17.dp, width = 71.dp),
            shimmerBackground = shimmerBackground
        )
    }
}

@Composable
fun ShimmeredShape(
    shimmerBackground: Brush?=null,
    modifier: Modifier = Modifier,
    background: Color = Theme.color.surfaces.surfaceVariant
) {
    Row(
        modifier = modifier
            .clip(CircleShape)
            .background(background)
    ) {}
}

@Preview
@Composable
private fun LoadingNutritionScreenPrev() {
    AppTheme(isDarkTheme = true) {
        LoadingNutritionScreen()
    }
}