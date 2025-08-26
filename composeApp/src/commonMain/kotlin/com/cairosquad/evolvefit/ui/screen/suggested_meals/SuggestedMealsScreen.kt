package com.cairosquad.evolvefit.ui.screen.suggestedMeals

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
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
import com.cairosquad.evolvefit.design_system.component.appbar.ActionIconButton
import com.cairosquad.evolvefit.design_system.component.appbar.CustomAppBar
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.screen.suggested_meals.content.component.LoadingMealCard
import com.cairosquad.evolvefit.ui.util.ObserveAsEffect
import com.cairosquad.evolvefit.ui.util.noRippleClickable
import com.cairosquad.evolvefit.viewmodel.suggested_meals.SuggestedMealsEffect
import com.cairosquad.evolvefit.viewmodel.suggested_meals.SuggestedMealsInteractionListener
import com.cairosquad.evolvefit.viewmodel.suggested_meals.SuggestedMealsScreenState
import com.cairosquad.evolvefit.viewmodel.suggested_meals.SuggestedMealsViewModel
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.back
import evolvefit.composeapp.generated.resources.failed_to_load_meals
import evolvefit.composeapp.generated.resources.ic_back
import evolvefit.composeapp.generated.resources.im_no_meals_recorded
import evolvefit.composeapp.generated.resources.no_meals_description
import evolvefit.composeapp.generated.resources.no_meals_title
import evolvefit.composeapp.generated.resources.suggested_meals
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun SuggestedMealsScreen(
    navigateBack: () -> Unit,
    navigateToMealDetails: (String) -> Unit,
    viewModel: SuggestedMealsViewModel = koinViewModel()
) {
    val state by viewModel.screenState.collectAsState()

    ObserveAsEffect(viewModel.effect) { effect ->
        when (effect) {
            is SuggestedMealsEffect.NavigateBack -> navigateBack()
            is SuggestedMealsEffect.NavigateToMealDetails -> navigateToMealDetails(effect.mealId)
        }
    }
    SuggestedMealsContent(
        state = state,
        listener = viewModel
    )
}

@Composable
private fun SuggestedMealsContent(
    state: SuggestedMealsScreenState,
    listener: SuggestedMealsInteractionListener,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.color.surfaces.surface)
            .padding(horizontal = 16.dp)
    ) {
        SuggestedMealsAppBar(onBackClick = listener::onBackClicked)
        Crossfade(
            targetState = state.screenStatus,
            animationSpec = tween(durationMillis = 300)
        ) { screenStatus ->
            when (screenStatus) {
                SuggestedMealsScreenState.ScreenStatus.LOADING -> SuggestedMealsLoadingState()
                SuggestedMealsScreenState.ScreenStatus.ERROR -> SuggestedMealsErrorState(state.errorMessage)
                SuggestedMealsScreenState.ScreenStatus.SUCCESS -> SuggestedMealsSuccessState(
                    state.suggestedMeals,
                    listener = listener
                )
            }
        }
    }
}

@Composable
private fun SuggestedMealsAppBar(onBackClick: () -> Unit) {
    CustomAppBar(
        title = stringResource(Res.string.suggested_meals),
        modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars),
        header = {
            ActionIconButton(
                icon = painterResource(Res.drawable.ic_back),
                contentDescription = stringResource(Res.string.back),
                tint = Theme.color.surfaces.onSurface,
                onClick = onBackClick
            )
        }
    )
}

@Composable
private fun SuggestedMealsLoadingState() {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(124.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp)
    ) {
        items(20) { LoadingMealCard() }
    }
}

@Composable
private fun SuggestedMealsErrorState(errorMessage: String?) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        StateMessage(
            image = painterResource(Res.drawable.im_no_meals_recorded),
            title = "Error",
            description = errorMessage ?: stringResource(Res.string.failed_to_load_meals)
        )
    }
}

@Composable
private fun SuggestedMealsSuccessState(
    suggestedMeals: List<SuggestedMealsScreenState.SuggestedMealUiState>,
    listener: SuggestedMealsInteractionListener
) {
    if (suggestedMeals.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            StateMessage(
                image = painterResource(Res.drawable.im_no_meals_recorded),
                title = stringResource(Res.string.no_meals_title),
                description = stringResource(Res.string.no_meals_description),
                modifier = Modifier.padding(bottom = 48.dp)
            )
        }
    } else {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 150.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(vertical = 16.dp),
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.navigationBars)
        ) {
            items(suggestedMeals) { meal ->
                MealCard(
                    title = meal.name,
                    mealType = stringResource(meal.type.displayName),
                    calories = meal.calories,
                    model = meal.imageUrl,
                    modifier = Modifier
                        .noRippleClickable { listener.onMealClicked(meal.id) }
                )
            }
        }
    }
}
