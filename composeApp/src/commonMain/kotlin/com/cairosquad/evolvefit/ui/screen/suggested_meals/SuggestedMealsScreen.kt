package com.cairosquad.evolvefit.ui.screen.suggestedMeals

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.MealCard
import com.cairosquad.evolvefit.design_system.component.StateMessage
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.screen.suggested_meals.LoadingMealCard
import com.cairosquad.evolvefit.ui.screen.suggested_meals.TopHeader
import com.cairosquad.evolvefit.ui.util.ObserveAsEffect
import com.cairosquad.evolvefit.viewmodel.suggested_meals.SuggestedMealsEffect
import com.cairosquad.evolvefit.viewmodel.suggested_meals.SuggestedMealsScreenState
import com.cairosquad.evolvefit.viewmodel.suggested_meals.SuggestedMealsViewModel
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.failed_to_load_meals
import evolvefit.composeapp.generated.resources.im_no_meals_recorded
import evolvefit.composeapp.generated.resources.no_meals_description
import evolvefit.composeapp.generated.resources.no_meals_title
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SuggestedMealsScreen(
    navigateBack: () -> Unit,
    navigateToMealDetails: (Long) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SuggestedMealsViewModel = koinViewModel()
) {
    val state by viewModel.screenState.collectAsState()
    ObserveAsEffect(viewModel.effect) { effect ->
        when (effect) {
            is SuggestedMealsEffect.NavigateBack -> navigateBack()
            is SuggestedMealsEffect.NavigateToMealDetails -> navigateToMealDetails(effect.mealId)
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.color.surfaces.surface)
            .padding(horizontal = 16.dp)
    ) {
        TopHeader(onBackClick = { viewModel.onBackClicked() })
        Crossfade(
            targetState = state.screenStatus,
            animationSpec = tween(durationMillis = 300)
        ) { screenStatus ->
            when (screenStatus) {
                SuggestedMealsScreenState.ScreenStatus.LOADING -> {
                    Spacer(Modifier.height(16.dp))
                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(124.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(20) { LoadingMealCard() }
                    }
                }
                SuggestedMealsScreenState.ScreenStatus.ERROR -> {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        StateMessage(
                            image = painterResource(Res.drawable.im_no_meals_recorded),
                            title = "Error",
                            description = state.errorMessage ?: stringResource(Res.string.failed_to_load_meals)                        )
                    }
                }
                SuggestedMealsScreenState.ScreenStatus.SUCCESS -> {
                    if (state.suggestedMeals.isEmpty()) {
                        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            StateMessage(
                                image = painterResource(Res.drawable.im_no_meals_recorded),
                                title = stringResource(Res.string.no_meals_title),
                                description = stringResource(Res.string.no_meals_description),
                                modifier = Modifier.padding(bottom = 48.dp)
                            )
                        }
                    } else {
                        LazyVerticalGrid(
                            columns = GridCells.Adaptive(124.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(vertical = 16.dp)
                        ) {
                            items(state.suggestedMeals) { meal ->
                                MealCard(
                                    title = meal.name,
                                    mealType = stringResource(meal.type.displayName),
                                    calories = meal.calories,
                                    model = meal.imageUrl
                                )
                            }
                        }
                    }
                }
            }
        }
    }
@Preview
@Composable
fun Preview() {
    SuggestedMealsScreen(
        navigateBack = {},
        navigateToMealDetails = {20}

    )
}}
