package com.cairosquad.evolvefit.ui.screen.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
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
import com.cairosquad.evolvefit.design_system.component.PrimaryButton
import com.cairosquad.evolvefit.design_system.component.StateMessage
import com.cairosquad.evolvefit.design_system.component.WorkoutCard
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.design_system.util.NetworkImage
import com.cairosquad.evolvefit.domain.entity.Profile
import com.cairosquad.evolvefit.ui.component.CaloriesNutritionCard
import com.cairosquad.evolvefit.ui.component.RefreshBox
import com.cairosquad.evolvefit.ui.component.WaterNutritionCard
import com.cairosquad.evolvefit.ui.util.ObserveAsEffect
import com.cairosquad.evolvefit.viewmodel.home.HomeInteractionListener
import com.cairosquad.evolvefit.viewmodel.home.HomeScreenEffect
import com.cairosquad.evolvefit.viewmodel.home.HomeScreenState
import com.cairosquad.evolvefit.viewmodel.home.HomeViewModel
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.activity
import evolvefit.composeapp.generated.resources.completed
import evolvefit.composeapp.generated.resources.current_weight
import evolvefit.composeapp.generated.resources.hello_user
import evolvefit.composeapp.generated.resources.ic_bookmark_big
import evolvefit.composeapp.generated.resources.ic_bookmark_big_filled
import evolvefit.composeapp.generated.resources.ic_crown
import evolvefit.composeapp.generated.resources.ic_no_internet_light
import evolvefit.composeapp.generated.resources.ic_progress
import evolvefit.composeapp.generated.resources.ic_scale
import evolvefit.composeapp.generated.resources.ic_thin_check_mark
import evolvefit.composeapp.generated.resources.im_no_internet
import evolvefit.composeapp.generated.resources.internet_is_not_available
import evolvefit.composeapp.generated.resources.just_for_you
import evolvefit.composeapp.generated.resources.please_make_sure_you_are_connected_to_the_internet_and_try_again
import evolvefit.composeapp.generated.resources.profile_picture
import evolvefit.composeapp.generated.resources.ready_text_female
import evolvefit.composeapp.generated.resources.ready_text_male
import evolvefit.composeapp.generated.resources.save
import evolvefit.composeapp.generated.resources.the_goal
import evolvefit.composeapp.generated.resources.this_week
import evolvefit.composeapp.generated.resources.today_nutrition
import evolvefit.composeapp.generated.resources.try_again_button
import evolvefit.composeapp.generated.resources.your_progress
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    navigateToWorkout: (String) -> Unit,
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
    Box(modifier = Modifier.fillMaxSize()) {
        RefreshBox(
            isRefreshing = state.isRefreshing,
            onRefresh = { homeViewModel.onRefresh() }
        ) {
            Crossfade(
                targetState = state.screenStatus,
                animationSpec = tween(
                    durationMillis = 400,
                    easing = FastOutSlowInEasing
                )
            ) {
                when (state.screenStatus) {
                    HomeScreenState.ScreenStatus.SUCCESS -> {
                        HomeContent(
                            state = state,
                            interactionListener = homeViewModel
                        )
                    }

                    HomeScreenState.ScreenStatus.LOADING -> {
                        HomeLoadingScreen()
                    }

                    HomeScreenState.ScreenStatus.FAIL -> {
                        HomeErrorContent(
                            onRetry = homeViewModel::onRetryClick
                        )
                    }
                }
            }
        }
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
            visibilityKey = true,
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

        HomeSection(
            title = stringResource(Res.string.just_for_you),
            visibilityKey = state.personalizedWorkouts.isNotEmpty(),
            modifier = Modifier
                .padding(bottom = 32.dp),
            isPaddedStart = true
        ) {
            PersonalizedWorkouts(
                workouts = state.personalizedWorkouts,
                onWorkoutClick = interactionListener::onWorkoutClick,
                onSavedWorkoutClick = interactionListener::onSavedWorkoutClick
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
                        resource = if (user?.gender == Profile.Gender.FEMALE) {
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

            progress?.goal?.let {
                progress.weightUnit?.let { resource ->
                    StatsRow(
                        goal = stringResource(it),
                        currentWeight = progress.currentWeight,
                        weightUnit = stringResource(resource),
                        activityPercentage = progress.activityPercentage
                    )
                }
            }
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
        horizontalArrangement = Arrangement.SpaceBetween,
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
        StatsSection(
            iconRes = Res.drawable.ic_crown,
            title = stringResource(Res.string.the_goal),
            value = goal,
            modifier = Modifier
                .padding(end = 8.dp)
        )

        VerticalDivider(
            color = Theme.color.surfaces.outlineVariant,
            thickness = 1.dp,
            modifier = Modifier
                .height(40.dp)
        )

        StatsSection(
            iconRes = Res.drawable.ic_scale,
            title = stringResource(Res.string.current_weight),
            value = "$currentWeight $weightUnit",
            modifier = Modifier
                .padding(end = 8.dp)
        )

        VerticalDivider(
            color = Theme.color.surfaces.outlineVariant,
            thickness = 1.dp,
            modifier = Modifier
                .height(40.dp)
        )

        StatsSection(
            iconRes = Res.drawable.ic_progress,
            title = stringResource(Res.string.activity),
            value = "$activityPercentage %",
        )
    }
}

@Composable
private fun StatsSection(
    iconRes: DrawableResource,
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                painter = painterResource(iconRes),
                contentDescription = title,
                tint = Theme.color.brand.primary,
                modifier = Modifier
                    .size(16.dp)
            )

            Text(
                text = title,
                style = Theme.textStyle.label.smallRegular12,
                color = Theme.color.surfaces.onSurfaceVariant,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }

        Text(
            text = value,
            style = Theme.textStyle.body.mediumMedium14,
            color = Theme.color.surfaces.onSurfaceContainer,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun HomeSection(
    title: String,
    visibilityKey: Boolean,
    modifier: Modifier = Modifier,
    isPaddedStart: Boolean = false,
    content: @Composable () -> Unit
) {
    AnimatedVisibility(visibilityKey) {
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
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .then(
                        if (isPaddedStart) {
                            Modifier
                                .padding(start = 16.dp)
                        } else {
                            Modifier
                        }
                    )
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
private fun PersonalizedWorkouts(
    workouts: List<HomeScreenState.HomeWorkoutUiState>,
    onWorkoutClick: (String) -> Unit,
    onSavedWorkoutClick: (String, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(
            horizontal = 16.dp
        )
    ) {
        items(workouts) { workout ->
            Box(
                modifier = Modifier
                    .width(268.dp)
                    .height(172.dp)
                    .clickable(onClick = { onWorkoutClick(workout.id) })
            ) {
                WorkoutCard(
                    title = workout.name,
                    duration = "${workout.durationInMins} Min", // TODO: convert to string resources
                    focusArea = workout.type,
                    model = workout.imageUrl,
                    modifier = Modifier
                        .fillMaxSize()
                )

                SaveButton(
                    isSaved = workout.isSaved,
                    onClick = { onSavedWorkoutClick(workout.id, workout.isSaved) },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(12.dp)
                )
            }
        }
    }
}

@Composable
private fun SaveButton(
    isSaved: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .size(40.dp)
            .background(Theme.color.surfaces.onSurfaceAt3)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        AnimatedContent(
            targetState = isSaved,
            transitionSpec = {
                scaleIn(animationSpec = tween(300)).togetherWith(scaleOut(animationSpec = tween(300)))
            }
        ) {
            Icon(
                painter = painterResource(
                    if (it) {
                        Res.drawable.ic_bookmark_big_filled
                    } else {
                        Res.drawable.ic_bookmark_big
                    }
                ),
                contentDescription = stringResource(Res.string.save),
                tint = Theme.color.surfaces.textColor,
                modifier = Modifier
                    .size(20.dp)
            )
        }
    }
}

@Composable
private fun HomeErrorContent(
    onRetry: () -> Unit
) {
    val noInternetIcon = if (isSystemInDarkTheme()) {
        Res.drawable.im_no_internet
    } else {
        Res.drawable.ic_no_internet_light
    }
    Box(modifier = Modifier.fillMaxSize()) {
        StateMessage(
            image = painterResource(noInternetIcon),
            title = stringResource(Res.string.internet_is_not_available),
            description = stringResource(Res.string.please_make_sure_you_are_connected_to_the_internet_and_try_again),
            modifier = Modifier.align(Alignment.Center)
        )
        PrimaryButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 16.dp, vertical = 24.dp),
            text = stringResource(Res.string.try_again_button),
            enabledTextColor = Theme.color.brand.onPrimary,
            textStyle = Theme.textStyle.body.mediumMedium14,
            onClick = onRetry
        )
    }
}