package com.cairosquad.evolvefit.ui.screen.favorites.content.componants

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.StateMessage
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.screen.favorites.content.FavoritesLoadingScreen
import com.cairosquad.evolvefit.viewmodel.favorites.FavoritesInteractionListener
import com.cairosquad.evolvefit.viewmodel.favorites.FavoritesState
import com.cairosquad.evolvefit.viewmodel.favorites.MealsUiModel
import com.cairosquad.evolvefit.viewmodel.favorites.WorkoutsUiModel
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.empty_meals_description
import evolvefit.composeapp.generated.resources.empty_meals_title
import evolvefit.composeapp.generated.resources.empty_workouts_description
import evolvefit.composeapp.generated.resources.empty_workouts_title
import evolvefit.composeapp.generated.resources.im_empty_list_dark
import evolvefit.composeapp.generated.resources.im_empty_list_light
import evolvefit.composeapp.generated.resources.meals_tab_title
import evolvefit.composeapp.generated.resources.workouts_tab_title
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabsWithPager(
    meals: List<MealsUiModel>,
    workouts: List<WorkoutsUiModel>,
    state: FavoritesState,
    listener: FavoritesInteractionListener
) {
    val pagerState = rememberPagerState(pageCount = { 2 })

    LaunchedEffect(state) {
        try {
            if (state.isWorkoutTabSelected) {
                pagerState.animateScrollToPage(0)
            } else {
                pagerState.animateScrollToPage(1)
            }
        } catch (_: Exception) {
        }
    }

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TabItem(
                modifier = Modifier
                    .weight(1f),
                title = stringResource(Res.string.workouts_tab_title),
                selected = pagerState.currentPage == 0,
                onTabClicked = listener::onWorkoutTabSelected
            )
            TabItem(
                modifier = Modifier
                    .weight(1f),
                title = stringResource(Res.string.meals_tab_title),
                selected = pagerState.currentPage == 1,
                onTabClicked = listener::onMealTabSelected
            )
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
            userScrollEnabled = false
        ) { page ->
            when (page) {
                0 -> {
                    when {
                        state.isLoading -> FavoritesLoadingScreen()
                        else -> AnimatedEmptyStateSwitcher(
                            isEmpty = workouts.isEmpty(),
                            emptyTitle = stringResource(Res.string.empty_workouts_title),
                            emptyDescription = stringResource(Res.string.empty_workouts_description)
                        ) {
                            WorkoutList(
                                workouts = workouts,
                                onSaveIconClick = {
                                    listener.deleteWorkout(it)
                                }

                            )
                        }
                    }
                }

                1 -> {
                    when {
                        state.isLoading -> FavoritesLoadingScreen()
                        else -> AnimatedEmptyStateSwitcher(
                            isEmpty = meals.isEmpty(),
                            emptyTitle = stringResource(Res.string.empty_meals_title),
                            emptyDescription = stringResource(Res.string.empty_meals_description)
                        ) {
                            MealsList(
                                meals = meals,
                                onSaveIconClick = {
                                    listener.deleteMeal(it)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun TabItem(
    title: String,
    selected: Boolean,
    modifier: Modifier,
    onTabClicked: () -> Unit
) {
    val targetColor = if (selected)
        Theme.color.brand.primary
    else
        Theme.color.surfaces.onSurface

    val textColor by animateColorAsState(targetValue = targetColor)
    Column(
        modifier = modifier
            .clickable { onTabClicked() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 12.dp),
            text = title,
            color = textColor,
            style = Theme.textStyle.body.mediumMedium14
        )
        if (selected) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(color = Theme.color.brand.primary)
            )
        } else {
            Spacer(modifier = Modifier.height(1.dp))
        }
    }
}

@Composable
private fun AnimatedEmptyStateSwitcher(
    isEmpty: Boolean,
    emptyTitle: String,
    emptyDescription: String,
    listContent: @Composable () -> Unit
) {
    val emptyListScreen = if (Theme.isDark) {
        Res.drawable.im_empty_list_dark
    } else {
        Res.drawable.im_empty_list_light
    }
    AnimatedContent(
        targetState = isEmpty,
        label = "AnimatedListOrMessage"
    ) { empty ->
        if (empty) {
            StateMessage(
                modifier = Modifier
                    .padding(top = 180.dp),
                imageModifier = Modifier
                    .size(height = 120.dp, width = 170.dp),
                verticalArrangement = Arrangement.Top,
                image = painterResource(emptyListScreen),
                title = emptyTitle,
                description = emptyDescription
            )
        } else {
            listContent()
        }
    }
}