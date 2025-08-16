package com.cairosquad.evolvefit.ui.screen.favorites.componants

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.StateMessage
import com.cairosquad.evolvefit.design_system.theme.Theme
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabsWithPager(
    meals: List<MealsUiModel>,
    workouts: List<WorkoutsUiModel>
) {
    val tabs = listOf(
        stringResource(Res.string.workouts_tab_title),
        stringResource(Res.string.meals_tab_title)
    )
    val pagerState = rememberPagerState(pageCount = { 2 })
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            tabs.forEachIndexed { index, title ->
                val paddingModifier = Modifier
                    .weight(1f)
                    .then(
                        if (index == 0) Modifier.padding(end = 12.dp)
                        else Modifier.padding(start = 12.dp)
                    )
                TabItem(
                    title = title,
                    selected = pagerState.currentPage == index,
                    modifier = paddingModifier
                ) {
                    CoroutineScope(Dispatchers.Main).launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            when (page) {
                0 -> {
                    AnimatedEmptyStateSwitcher(
                        isEmpty = workouts.isEmpty(),
                        emptyTitle = stringResource(Res.string.empty_workouts_title),
                        emptyDescription = stringResource(Res.string.empty_workouts_description)
                    ) {
                        WorkoutList(workouts = workouts)
                    }
                }

                1 -> {
                    AnimatedEmptyStateSwitcher(
                        isEmpty = meals.isEmpty(),
                        emptyTitle = stringResource(Res.string.empty_meals_title),
                        emptyDescription = stringResource(Res.string.empty_meals_description)
                    ) {
                        MealsList(meals = meals)
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
    Column(
        modifier = modifier
            .clickable { onTabClicked() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 12.dp),
            text = title,
            color = Theme.color.brand.primary,
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
    val emptyListScreen = if (isSystemInDarkTheme()) {
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