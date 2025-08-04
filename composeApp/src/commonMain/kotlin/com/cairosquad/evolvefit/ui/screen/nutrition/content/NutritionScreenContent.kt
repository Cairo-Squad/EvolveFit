package com.cairosquad.evolvefit.ui.screen.nutrition.content

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun NutritionScreenContent(nutritionViewModel: NutritionViewModel) {
    val state by nutritionViewModel.screenState.collectAsState()
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Theme.color.surfaces.surface)
        ) {
            stickyHeader {
                Row(
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .fillMaxWidth()
                        .background(Theme.color.surfaces.surface),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier
                            .windowInsetsPadding(WindowInsets.statusBars)
                            .padding(vertical = 14.5.dp),
                        text = stringResource(Res.string.nutrition),
                        style = Theme.textStyle.title.largeBold16,
                        color = Theme.color.surfaces.onSurface
                    )
                }
            }
            item {
                NutritionSummaryCard(listener = nutritionViewModel)
                ScanMeal()
                TodayMeals(
                    state = state,
                    listener = nutritionViewModel
                )
                SuggestedMeals(
                    state = state,
                    listener = nutritionViewModel
                )
                SeeAll(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 32.dp, bottom = 12.dp),
                    onViewAllClick = nutritionViewModel::onViewAllMealHistoryClicked,
                    sectionTitle = stringResource(Res.string.meal_history)
                )
            }
            items(state.mealHistory) {
                MealHistoryItem(meal = it)
            }
        }
        AddWaterIntakeBottomSheet(
            modifier = Modifier.align(Alignment.BottomCenter),
            listener = nutritionViewModel,
            state = state
        )
        MealTypeDropdown(
            state = state, nutritionViewModel = nutritionViewModel, Modifier.align(
                Alignment.BottomCenter
            )
        )
        LaunchedEffect(state.isAddMealSnackBarVisible) {
            if (!state.isAddMealSnackBarVisible) return@LaunchedEffect
            delay(2000)
            nutritionViewModel.onSnackBarHided()
        }
        SnackBar(
            modifier = Modifier.align(Alignment.BottomCenter),
            text = stringResource(Res.string.meal_added_snackbar),
            isVisible = state.isAddMealSnackBarVisible
        )
    }
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
        Row(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Theme.color.surfaces.surfaceVariant),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_scan),
                contentDescription = null,
                tint = Color.Unspecified
            )
        }
        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth().weight(1f),
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
        Icon(
            painter = painterResource(Res.drawable.ic_end_arrow),
            contentDescription = null,
            tint = Color.Unspecified
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
            modifier = Modifier.padding(start = 4.dp)
                .clickable(onClick = { onViewAllClick() }),
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
    var waterAmount by remember { mutableStateOf("") }
    var buttonIsEnabled by remember { mutableStateOf(false) }
    LaunchedEffect(waterAmount) {
        buttonIsEnabled = waterAmount.isNotBlank()
    }
    AnimatedVisibility(
        modifier = modifier.fillMaxWidth(),
        visible = state.isAddWaterSheetVisible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        BottomSheet(
            isVisible = state.isAddWaterSheetVisible,
            onDismiss = { listener.onDismissWaterClicked() }) {
            Column(
                modifier = Modifier
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
                    value = waterAmount,
                    onValueChange = { waterAmount = it },
                    keyboardType = KeyboardType.Number,
                    placeholder = "e.g., 1.5 L",
                    leadingIcon = Res.drawable.ic_water_drop
                )
                PrimaryButton(
                    modifier = Modifier.padding(top = 40.dp, bottom = 16.dp),
                    text = stringResource(Res.string.add_button),
                    isEnabled = buttonIsEnabled,
                    onClick = {
                        listener.onConfirmAddWaterClicked(waterAmount.toFloat())
                        waterAmount = ""
                    })
            }
        }
    }
}
