package com.cairosquad.evolvefit.ui.screen.workoutDetails.content

import androidx.compose.animation.animateColorAsState
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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.appbar.ActionIconButton
import com.cairosquad.evolvefit.design_system.component.appbar.CustomAppBar
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.component.ShimmerOverlayShape
import com.cairosquad.evolvefit.viewmodel.workout_details.WorkoutDetailsInteractionListener
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.back
import evolvefit.composeapp.generated.resources.ic_back
import evolvefit.composeapp.generated.resources.shimmer_loading
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun WorkoutDetailsLoading(
    listener: WorkoutDetailsInteractionListener
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

    val listState = rememberLazyListState()
    val scrollOffsetThreshold = 200
    val isScrolled by remember {
        derivedStateOf {
            val firstItemIndex = listState.firstVisibleItemIndex
            val firstItemOffset = listState.firstVisibleItemScrollOffset
            (firstItemIndex * 200 + firstItemOffset) > scrollOffsetThreshold
        }
    }

    val appBarBackground by animateColorAsState(
        targetValue =
            if (isScrolled) Theme.color.surfaces.surface
            else Theme.color.surfaces.surface.copy(alpha = 0f),
        animationSpec = tween(1000)
    )
    val iconTint by animateColorAsState(
        targetValue = if (isScrolled) Theme.color.surfaces.onSurface
        else Theme.color.surfaces.textColor,
        animationSpec = tween(1000)
    )

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding()
                .padding(bottom = 68.dp)
                .background(color = Theme.color.surfaces.surface),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                ShimmerOverlayShape(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    shimmerBrush = brush,
                    shape = RectangleShape,
                    background = Theme.color.surfaces.outlineVariant
                )
            }
            item {
                ShimmerOverlayShape(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .padding(horizontal = 16.dp)
                        .width(128.dp)
                        .height(22.dp),
                    shimmerBrush = brush,
                    shape = CircleShape,
                )
            }

            item {
                ShimmerOverlayShape(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(42.dp)
                        .padding(horizontal = 16.dp),
                    shimmerBrush = brush,
                    shape = RoundedCornerShape(8.dp),
                )
            }
            item {
                DetailsCardsRowLoading(brush)
            }
            item {
                ShimmerOverlayShape(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .padding(horizontal = 16.dp)
                        .width(96.dp)
                        .height(19.dp),
                    shimmerBrush = brush,
                    shape = CircleShape,
                )
            }
            items(10) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ShimmerOverlayShape(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .width(88.dp)
                            .height(68.dp),
                        shimmerBrush = brush,
                        shape = RoundedCornerShape(8.dp),
                    )
                    Column {
                        ShimmerOverlayShape(
                            modifier = Modifier
                                .width(128.dp)
                                .height(17.dp),
                            shimmerBrush = brush,
                            shape = CircleShape,
                        )
                        ShimmerOverlayShape(
                            modifier = Modifier
                                .padding(top = 12.dp)
                                .width(82.dp)
                                .height(16.dp),
                            shimmerBrush = brush,
                            shape = CircleShape,
                        )
                    }
                }
            }
        }
        CustomAppBar(
            modifier = Modifier
                .background(appBarBackground)
                .statusBarsPadding(),
            title = "",
            header = {
                ActionIconButton(
                    icon = painterResource(Res.drawable.ic_back),
                    contentDescription = stringResource(Res.string.back),
                    tint = iconTint,
                    onClick = listener::onBackClicked
                )
            }
        )
    }
}

@Composable
private fun DetailsCardsRowLoading(brush: Brush) {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        ShimmerOverlayShape(
            modifier = Modifier
                .weight(1f)
                .height(101.33.dp)
                .border(
                    1.dp,
                    Theme.color.surfaces.outlineVariant,
                    RoundedCornerShape(8.dp)
                ),
            shimmerBrush = brush,
            shape = RoundedCornerShape(8.dp),
            background = Theme.color.surfaces.surfaceContainer
        )
        ShimmerOverlayShape(
            modifier = Modifier
                .weight(1f)
                .height(101.33.dp)
                .border(
                    1.dp,
                    Theme.color.surfaces.outlineVariant,
                    RoundedCornerShape(8.dp)
                ),
            shimmerBrush = brush,
            shape = RoundedCornerShape(8.dp),
            background = Theme.color.surfaces.surfaceContainer
        )
        ShimmerOverlayShape(
            modifier = Modifier
                .weight(1f)
                .height(101.33.dp)
                .border(
                    1.dp,
                    Theme.color.surfaces.outlineVariant,
                    RoundedCornerShape(8.dp)
                ),
            shimmerBrush = brush,
            shape = RoundedCornerShape(8.dp),
            background = Theme.color.surfaces.surfaceContainer
        )
    }
}