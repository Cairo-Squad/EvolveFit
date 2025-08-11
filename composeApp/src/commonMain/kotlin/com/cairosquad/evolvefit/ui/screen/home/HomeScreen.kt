package com.cairosquad.evolvefit.ui.screen.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.design_system.util.NetworkImage
import com.cairosquad.evolvefit.entity.Gender
import com.cairosquad.evolvefit.ui.component.CaloriesNutritionCard
import com.cairosquad.evolvefit.ui.component.WaterNutritionCard
import com.cairosquad.evolvefit.ui.util.ObserveAsEffect
import com.cairosquad.evolvefit.viewmodel.base.ErrorState
import com.cairosquad.evolvefit.viewmodel.home.HomeInteractionListener
import com.cairosquad.evolvefit.viewmodel.home.HomeScreenEffect
import com.cairosquad.evolvefit.viewmodel.home.HomeScreenState
import com.cairosquad.evolvefit.viewmodel.home.HomeViewModel
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.completed
import evolvefit.composeapp.generated.resources.hello_user
import evolvefit.composeapp.generated.resources.ic_thin_check_mark
import evolvefit.composeapp.generated.resources.profile_picture
import evolvefit.composeapp.generated.resources.ready_text_female
import evolvefit.composeapp.generated.resources.ready_text_male
import evolvefit.composeapp.generated.resources.this_week
import evolvefit.composeapp.generated.resources.today_nutrition
import evolvefit.composeapp.generated.resources.your_progress
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    navigateToWorkout: (Long) -> Unit,
    homeViewModel: HomeViewModel = koinViewModel(),
) {
    val state by homeViewModel.screenState.collectAsState()

    ObserveAsEffect(homeViewModel.effect) { effect ->
        when (effect) {
            is HomeScreenEffect.NavigateToWorkout -> {
                navigateToWorkout(effect.workoutId)
            }

            is HomeScreenEffect.ShowErrorSnackBar -> {
                TODO()
            }
        }
    }

    if (state.isLoading) {
        HomeLoadingContent()
    } else if (state.error != null) {
        HomeErrorContent(
            error = state.error!!,
            onRetry = homeViewModel::onRetryClicked
        )
    } else {
        HomeContent(
            state = state,
            interactionListener = homeViewModel
        )
    }
}

@Composable
private fun HomeContent(
    state: HomeScreenState,
    interactionListener: HomeInteractionListener
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Theme.color.surfaces.surface)
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(vertical = 16.dp)
    ) {
        HomeUserHeader(
            user = state.user,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 24.dp)
        )

        HomeProgressBox(
            progress = state.weeklyProgress,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 32.dp)
        )

        HomeSection(
            title = stringResource(Res.string.today_nutrition),
            key = state.caloriesCount > 0.toUInt() && state.waterCount > 0,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 32.dp)
        ) {
            SimpleNutritionRow(
                caloriesValue = state.caloriesCount,
                caloriesGoal = state.caloriesGoal,
                waterValue = state.waterCount,
                waterGoal = state.waterGoal
            )
        }
    }
}

@Composable
private fun HomeUserHeader(
    user: HomeScreenState.HomeUserUiState?,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(user != null) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            NetworkImage(
                model = user?.profilePictureUrl ?: "",
                contentDescription = stringResource(Res.string.profile_picture),
                contentScale = ContentScale.Crop,
                placeholderImageSize = DpSize(40.dp, 40.dp),
                modifier = Modifier
                    .clip(CircleShape)
                    .size(40.dp)
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = stringResource(Res.string.hello_user, user?.name ?: ""),
                    style = Theme.textStyle.body.mediumMedium12,
                    color = Theme.color.surfaces.onSurfaceVariant,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = stringResource(
                        resource = if (user?.gender == Gender.FEMALE) {
                            Res.string.ready_text_female
                        } else {
                            Res.string.ready_text_male
                        }
                    ),
                    style = Theme.textStyle.body.mediumMedium12,
                    color = Theme.color.surfaces.onSurface,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
private fun HomeProgressBox(
    progress: HomeScreenState.WeeklyProgressUiState?,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(visible = progress != null) {
        Column(
            modifier = modifier
                .clip(RoundedCornerShape(8.dp))
                .fillMaxWidth()
                .background(Theme.color.surfaces.surfaceContainer)
                .padding(
                    horizontal = 12.dp,
                    vertical = 16.dp
                ),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ProgressHeaderRow()

            WeeklyProgressBar(
                progressDays = progress?.progressDays ?: emptyMap()
            )

            StatsRow(
                goal = progress?.goal ?: "",
                currentWeight = progress?.currentWeight ?: 0f,
                weightUnit = progress?.weightUnit ?: "",
                activityPercentage = progress?.activityPercentage ?: 0.toUInt()
            )
        }
    }
}

@Composable
private fun ProgressHeaderRow(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(Res.string.your_progress),
            style = Theme.textStyle.headline.largeBold16,
            color = Theme.color.surfaces.onSurface,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(end = 8.dp)
        )

        Text(
            text = stringResource(Res.string.this_week),
            style = Theme.textStyle.label.smallRegular14,
            color = Theme.color.surfaces.onSurfaceVariant,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

private const val WEEK_DAYS_NUM = 7

@Composable
private fun WeeklyProgressBar(
    progressDays: Map<Int, Boolean>,
    modifier: Modifier = Modifier
) {
    FlowRow(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        maxItemsInEachRow = WEEK_DAYS_NUM
    ) {
        progressDays.entries.forEach { entry ->
            DayBox(
                day = entry.key,
                isCompleted = entry.value,
                modifier = Modifier
                    .then(
                        if (entry.key != progressDays.entries.last().key) {
                            Modifier
                                .padding(end = 8.dp)
                        } else {
                            Modifier
                        }
                    )
            )
        }
    }
}

@Composable
private fun StatsRow(
    goal: String,
    currentWeight: Float,
    weightUnit: String,
    activityPercentage: UInt,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                vertical = 8.dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

    }
}

@Composable
private fun DayBox(
    day: Int,
    isCompleted: Boolean,
    modifier: Modifier = Modifier
) {
    val backgroundColor = if (isCompleted) {
        Theme.color.brand.primary
    } else {
        Theme.color.surfaces.outlineVariant
    }

    Box(
        modifier = modifier
            .clip(CircleShape)
            .size(36.dp)
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        AnimatedContent(
            targetState = isCompleted,
            transitionSpec = {
                fadeIn().togetherWith(fadeOut())
            }
        ) {
            if (it) {
                Icon(
                    painter = painterResource(Res.drawable.ic_thin_check_mark),
                    contentDescription = stringResource(Res.string.completed),
                    tint = Theme.color.brand.onPrimary,
                    modifier = Modifier
                        .size(24.dp)
                )
            } else {
                Text(
                    text = day.toString(),
                    style = Theme.textStyle.body.mediumMedium14,
                    color = Theme.color.surfaces.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
private fun HomeSection(
    title: String,
    key: Boolean,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    AnimatedVisibility(key) {
        Column(
            modifier = modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = title,
                style = Theme.textStyle.label.mediumMedium16,
                color = Theme.color.surfaces.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            content()
        }
    }
}

@Composable
private fun SimpleNutritionRow(
    caloriesValue: UInt,
    caloriesGoal: UInt,
    waterValue: Float,
    waterGoal: Float,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        CaloriesNutritionCard(
            value = caloriesValue,
            goal = caloriesGoal,
            modifier = Modifier
                .weight(1f)
        )

        WaterNutritionCard(
            value = waterValue,
            goal = waterGoal,
            modifier = Modifier
                .weight(1f)
        )
    }
}

@Composable
private fun HomeErrorContent(
    error: ErrorState,
    onRetry: () -> Unit
) {
    // TODO
}

@Composable
private fun HomeLoadingContent() {
    // TODO
}