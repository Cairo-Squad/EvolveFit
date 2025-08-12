package com.cairosquad.evolvefit.ui.screen.nutrition

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.BottomSheet
import com.cairosquad.evolvefit.design_system.component.PrimaryButton
import com.cairosquad.evolvefit.design_system.component.SnackBar
import com.cairosquad.evolvefit.design_system.component.StateMessage
import com.cairosquad.evolvefit.design_system.composables.InputField
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.screen.nutrition.component.SeeAll
import com.cairosquad.evolvefit.ui.screen.nutrition.component.MealHistoryItem
import com.cairosquad.evolvefit.ui.screen.nutrition.component.NutritionHeader
import com.cairosquad.evolvefit.ui.screen.nutrition.content.MealTypeDropdownMenu
import com.cairosquad.evolvefit.ui.screen.nutrition.component.NutritionSummaryCard
import com.cairosquad.evolvefit.ui.screen.nutrition.component.SuggestedMeals
import com.cairosquad.evolvefit.ui.screen.nutrition.component.TodayMealsSummary
import com.cairosquad.evolvefit.ui.util.ObserveAsEffect
import com.cairosquad.evolvefit.viewmodel.nutrition.NutritionEffect
import com.cairosquad.evolvefit.viewmodel.nutrition.NutritionInteractionListener
import com.cairosquad.evolvefit.viewmodel.nutrition.NutritionScreenState
import com.cairosquad.evolvefit.viewmodel.nutrition.NutritionViewModel
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.add_button
import evolvefit.composeapp.generated.resources.enter_water_intake
import evolvefit.composeapp.generated.resources.ic_water_drop
import evolvefit.composeapp.generated.resources.im_no_meals_recorded
import evolvefit.composeapp.generated.resources.meal_added_snackbar
import evolvefit.composeapp.generated.resources.meal_cant_added_snackbar
import evolvefit.composeapp.generated.resources.meal_history
import evolvefit.composeapp.generated.resources.no_meals_description
import evolvefit.composeapp.generated.resources.no_meals_title
import evolvefit.composeapp.generated.resources.track_water_intake
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun NutritionScreen(
    navigateToSuggestedMeals: () -> Unit,
    navigateToMealDetails: (String) -> Unit,
    navigateToMealsHistory: () -> Unit,
    nutritionViewModel: NutritionViewModel = koinViewModel()
) {
    val state by nutritionViewModel.screenState.collectAsState()

    ObserveAsEffect(nutritionViewModel.effect) { effect ->
        when (effect) {
            NutritionEffect.NavigateToMealHistory -> navigateToMealsHistory()
            is NutritionEffect.NavigateToSuggestedMealDetails -> navigateToMealDetails(effect.mealId)
            NutritionEffect.NavigateToSuggestedMeals -> navigateToSuggestedMeals()
        }
    }

    NutritionContent(
        state = state,
        listener = nutritionViewModel
    )
}

@Composable
private fun NutritionContent(
    state: NutritionScreenState,
    listener: NutritionInteractionListener
) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Theme.color.surfaces.surface)
        ) {
            item { NutritionHeader() }
            item { NutritionSummaryCard(listener = listener, state = state) }
            item { TodayMealsSummary(state = state, listener = listener) }
            item { SuggestedMeals(state = state, listener = listener) }
            mealHistorySection(state, listener)
        }

        MealTypeDropdownMenu(
            state = state,
            listener = listener,
            //   Modifier
            //        .align(Alignment.BottomCenter)
        )
        AddWaterIntakeBottomSheet(
            modifier = Modifier.align(Alignment.BottomCenter),
            listener = listener,
            state = state
        )
        MealAddedSnackBar(
            modifier = Modifier.align(Alignment.BottomCenter),
            isVisible = state.isAddMealSnackBarVisible,
        )
        MealCantAddSnackBar(
            modifier = Modifier.align(Alignment.BottomCenter),
            isVisible = state.isAddMealSnackBarVisible,
        )
    }
}

private fun LazyListScope.mealHistorySection(
    state: NutritionScreenState,
    listener: NutritionInteractionListener
) {
    if (state.todayConsumedMeals.isNotEmpty()) {
        item {
            SeeAll(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                onViewAllClick = listener::onViewAllMealHistoryClicked,
                sectionTitle = stringResource(Res.string.meal_history)
            )
        }
        items(state.todayConsumedMeals) { mealHistory ->
            MealHistoryItem(meal = mealHistory)
        }
    } else {
        item {
            EmptyMealHistory()
        }
    }
}

@Composable
private fun EmptyMealHistory() {
    Box(contentAlignment = Alignment.Center) {
        StateMessage(
            modifier = Modifier.padding(vertical = 16.dp),
            image = painterResource(Res.drawable.im_no_meals_recorded),
            title = stringResource(Res.string.no_meals_title),
            description = stringResource(Res.string.no_meals_description)
        )
    }
}

@Composable
private fun AddWaterIntakeBottomSheet(
    state: NutritionScreenState,
    listener: NutritionInteractionListener,
    modifier: Modifier = Modifier
) {
    BottomSheet(
        isVisible = state.isAddWaterSheetVisible,
        onDismiss = { listener.onDismissWaterClicked() }) {
        Column(
            modifier = modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(Res.string.enter_water_intake),
                style = Theme.textStyle.title.largeBold14,
                color = Theme.color.surfaces.onSurfaceContainer
            )
            Text(
                text = stringResource(Res.string.track_water_intake),
                style = Theme.textStyle.body.mediumMedium12,
                color = Theme.color.surfaces.outline
            )
            InputField(
                modifier = Modifier.padding(top = 16.dp),
                value = state.consumedWaterInput,
                onValueChange = listener::onWaterAmountChange,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                placeholder = "e.g., 1.5 L",
                leadingIcon = Res.drawable.ic_water_drop
            )
            PrimaryButton(
                modifier = Modifier.padding(top = 40.dp, bottom = 16.dp),
                text = stringResource(Res.string.add_button),
                isEnabled = state.isAddButtonEnabled,
                onClick = {
                    listener.onConfirmAddWaterClicked(state.consumedWaterInput.toFloat())
                })
        }
    }

}

@Composable
private fun MealAddedSnackBar(
    isVisible: Boolean,
    modifier: Modifier = Modifier
) {
    SnackBar(
        modifier = modifier,
        text = stringResource(Res.string.meal_added_snackbar),
        isVisible = isVisible
    )
}

@Composable
private fun MealCantAddSnackBar(
    isVisible: Boolean,
    modifier: Modifier = Modifier
) {
    SnackBar(
        modifier = modifier,
        //icon = painterResource(Res.drawable.ic_info),
        text = stringResource(Res.string.meal_cant_added_snackbar),
        isVisible = isVisible
    )
}

