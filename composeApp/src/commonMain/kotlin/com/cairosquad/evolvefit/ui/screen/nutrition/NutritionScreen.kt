package com.cairosquad.evolvefit.ui.screen.nutrition

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.BottomSheet
import com.cairosquad.evolvefit.design_system.component.PrimaryButton
import com.cairosquad.evolvefit.design_system.component.SnackBar
import com.cairosquad.evolvefit.design_system.composables.InputField
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.screen.nutrition.content.MealHistoryItem
import com.cairosquad.evolvefit.ui.screen.nutrition.content.MealTypeDropdownMenu
import com.cairosquad.evolvefit.ui.screen.nutrition.content.NutritionSummaryCard
import com.cairosquad.evolvefit.ui.screen.nutrition.content.SuggestedMeals
import com.cairosquad.evolvefit.ui.screen.nutrition.content.TodayMeals
import com.cairosquad.evolvefit.ui.util.ObserveAsEffect
import com.cairosquad.evolvefit.viewmodel.nutrition.NutritionEffect
import com.cairosquad.evolvefit.viewmodel.nutrition.NutritionInteractionListener
import com.cairosquad.evolvefit.viewmodel.nutrition.NutritionScreenState
import com.cairosquad.evolvefit.viewmodel.nutrition.NutritionViewModel
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.add_button
import evolvefit.composeapp.generated.resources.enter_water_intake
import evolvefit.composeapp.generated.resources.ic_end_arrow
import evolvefit.composeapp.generated.resources.ic_scan
import evolvefit.composeapp.generated.resources.ic_water_drop
import evolvefit.composeapp.generated.resources.meal_added_snackbar
import evolvefit.composeapp.generated.resources.meal_history
import evolvefit.composeapp.generated.resources.nutrition
import evolvefit.composeapp.generated.resources.track_water_intake
import evolvefit.composeapp.generated.resources.view_all
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun NutritionScreen(
    navigateToSuggestedMeals: () -> Unit,
    navigateToMealDetails: (Long) -> Unit,
    navigateToMealsHistory: () -> Unit,
    nutritionViewModel: NutritionViewModel = koinViewModel()
) {
    val state by nutritionViewModel.screenState.collectAsState()
    ObserveAsEffect(nutritionViewModel.effect) { effect ->
        when (effect) {
            NutritionEffect.NavigateToMealHistory -> {
                navigateToMealsHistory()
            }

            is NutritionEffect.NavigateToSuggestedMealDetails -> {
                navigateToMealDetails(effect.mealId)
            }

            NutritionEffect.NavigateToSuggestedMeals -> {
                navigateToSuggestedMeals()
            }
        }
    }
    NutritionScreenContent(state = state, listener = nutritionViewModel)
}

@Composable
private fun NutritionScreenContent(
    state: NutritionScreenState,
    listener: NutritionInteractionListener
) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Theme.color.surfaces.surface)
        ) {
            stickyHeader {
                NutritionHeader()
            }
            item { NutritionSummaryCard(listener = listener, state = state) }
            item { ScanMeal() }
            item {
                TodayMeals(
                    state = state,
                    listener = listener
                )
            }
            if (state.suggestedMeals.isNotEmpty()) {
                item {
                    SuggestedMeals(
                        state = state,
                        listener = listener
                    )
                }
            }

           // if (state.mealHistory.isNotEmpty()) {
                item {
                    MealHistoryViewAll(listener = listener)
                }
                items(state.mealsHistoryForDay) { mealHistory ->
                    MealHistoryItem(meal = mealHistory)
                }
//            } else {
//                item {
//                    Box(contentAlignment = Alignment.Center) {
//                        StateMessage(
//                            modifier = Modifier.padding(top = 16.dp, bottom = 12.dp),
//                            image = painterResource(Res.drawable.im_no_meals_recorded),
//                            title = stringResource(Res.string.no_meals_title),
//                            description = stringResource(Res.string.no_meals_description)
//                        )
//                    }
//                }
//            }
        }
        AddWaterIntakeBottomSheet(
            modifier = Modifier.align(Alignment.BottomCenter),
            listener = listener,
            state = state
        )
        MealTypeDropdownMenu(
            state = state,
            listener = listener,
            Modifier
                .align(Alignment.BottomCenter)
        )
        MealAddedSnackBar(
            modifier = Modifier.align(Alignment.BottomCenter),
            isVisible = state.isAddMealSnackBarVisible,
        )

    }
}

@Composable
private fun NutritionHeader() {
    Row(
        modifier = Modifier
            .padding(bottom = 16.dp)
            .fillMaxWidth()
            .background(Theme.color.surfaces.surface),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .windowInsetsPadding(WindowInsets.statusBars),
            text = stringResource(Res.string.nutrition),
            style = Theme.textStyle.title.largeBold16,
            color = Theme.color.surfaces.onSurface
        )
    }
}

@Composable
private fun MealHistoryViewAll(listener: NutritionInteractionListener) {
    SeeAll(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 32.dp, bottom = 12.dp),
        onViewAllClick = listener::onViewAllMealHistoryClicked,
        sectionTitle = stringResource(Res.string.meal_history)
    )
}

@Composable
private fun ScanMeal(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .padding(top = 12.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Theme.color.surfaces.surfaceContainer)
            .padding(vertical = 12.dp, horizontal = 8.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically

    ) {

        Icon(
            modifier = Modifier
                .clip(CircleShape)
                .background(Theme.color.surfaces.surfaceVariant)
                .padding(10.dp),
            painter = painterResource(Res.drawable.ic_scan),
            contentDescription = null,
            tint = Color.Unspecified
        )
        ScanMealTextBlock(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth()
                .weight(1f),
        )
        Icon(
            painter = painterResource(Res.drawable.ic_end_arrow),
            contentDescription = null,
            tint = Color.Unspecified
        )
    }
}

@Composable
fun ScanMealTextBlock(modifier: Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Scan Meal",
            style = Theme.textStyle.title.largeBold14,
            color = Theme.color.surfaces.onSurfaceContainer
        )
        Text(
            text = "Take a picture of your meal to count calories.",
            style = Theme.textStyle.body.mediumMedium12,
            color = Theme.color.surfaces.outline
        )
    }
}

@Composable
fun SeeAll(
    onViewAllClick: () -> Unit,
    sectionTitle: String,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Text(
            modifier = Modifier.weight(1f),
            text = sectionTitle,
            style = Theme.textStyle.label.mediumMedium16,
            color = Theme.color.surfaces.onSurface
        )
        Text(
            modifier = Modifier.clickable(
                onClick = { onViewAllClick() }
            ),
            text = stringResource(Res.string.view_all),
            style = Theme.textStyle.body.mediumMedium14,
            color = Theme.color.surfaces.onSurfaceVariant
        )
        Icon(
            modifier = Modifier
                .clickable(onClick = { onViewAllClick() })
                .padding(start = 4.dp),
            painter = painterResource(Res.drawable.ic_end_arrow),
            contentDescription = null,
            tint = Color.Unspecified
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
                value = state.waterAmountInput,
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
                    listener.onConfirmAddWaterClicked(state.waterAmountInput.toFloat())
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

