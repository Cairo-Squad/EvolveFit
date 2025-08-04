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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.BottomSheet
import com.cairosquad.evolvefit.design_system.component.PrimaryButton
import com.cairosquad.evolvefit.design_system.composables.CheckboxItem
import com.cairosquad.evolvefit.design_system.composables.CheckboxStyle
import com.cairosquad.evolvefit.design_system.composables.InputField
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.viewmodel.nutrition.NutritionInteractionListener
import com.cairosquad.evolvefit.viewmodel.nutrition.NutritionScreenState
import com.cairosquad.evolvefit.viewmodel.nutrition.NutritionViewModel
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.add_button
import evolvefit.composeapp.generated.resources.add_new_meal
import evolvefit.composeapp.generated.resources.calories
import evolvefit.composeapp.generated.resources.ic_arrow_down
import evolvefit.composeapp.generated.resources.ic_fire
import evolvefit.composeapp.generated.resources.log_meal_details
import evolvefit.composeapp.generated.resources.nutrition
import org.jetbrains.compose.resources.stringResource

@Composable
fun MealTypeDropdown(
    state: NutritionScreenState,
    nutritionViewModel: NutritionViewModel,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    var buttonIsEnabled by remember { mutableStateOf(false) }
    var mealName by remember { mutableStateOf("") }
    var mealCalories by remember { mutableStateOf("") }
    LaunchedEffect(mealName, mealCalories, state.mealTypeSelected) {
        buttonIsEnabled = mealName.isNotBlank() && mealCalories.isNotBlank()
    }
    AnimatedVisibility(
        modifier = modifier.fillMaxWidth(),
        visible = state.isAddMealSheetVisible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        BottomSheet(
            isVisible = state.isAddMealSheetVisible,
            onDismiss = {
                nutritionViewModel.onDismissMealClicked()
            }) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(Res.string.add_new_meal),
                    style = Theme.textStyle.title.largeBold14,
                    color = Theme.color.surfaces.onSurfaceContainer
                )
                Text(
                    text = stringResource(Res.string.log_meal_details),
                    style = Theme.textStyle.body.mediumMedium12,
                    color = Theme.color.surfaces.outline
                )
                InputField(
                    modifier = Modifier.padding(top = 16.dp),
                    value = mealName,
                    onValueChange = { mealName = it },
                    leadingIcon = Res.drawable.nutrition
                )
                Row(
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .fillMaxWidth()
                ) {
                    InputField(
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .weight(1f),
                        value = mealCalories,
                        onValueChange = {
                            mealCalories = it
                        },
                        placeholder = stringResource(Res.string.calories),
                        leadingIcon = Res.drawable.ic_fire
                    )
                    Box(modifier = Modifier.weight(1f)) {
                        InputField(
                            value = state.mealTypeSelected.displayName,
                            onValueChange = {},
                            trailingIcon = Res.drawable.ic_arrow_down,
                            onTrailingIconClick = {
                                expanded = !expanded
                            },
                            readOnly = true,
                            modifier = Modifier.clickable {
                                expanded = !expanded
                            })
                        DropdownMenu(
                            selectedItem = state.mealTypeSelected.displayName,
                            onItemSelected = {
                                expanded = false
                            },
                            expanded = expanded,
                            listener = nutritionViewModel,
                            onDismissRequest = {
                                expanded = false
                            },
                            modifier = Modifier.align(Alignment.TopStart).fillMaxWidth()
                                .padding(top = 48.dp)
                        )
                    }

                }
                PrimaryButton(
                    modifier = Modifier.padding(top = 40.dp, bottom = 16.dp),
                    text = stringResource(Res.string.add_button),
                    isEnabled = buttonIsEnabled,
                    onClick = {
                        nutritionViewModel.onConfirmAddMealClicked()
                        mealName = ""
                        mealCalories = ""
                    })
            }
        }
    }
}

@Composable
fun DropdownMenu(
    selectedItem: String?,
    expanded: Boolean,
    listener: NutritionInteractionListener,
    onItemSelected: () -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(
        visible = expanded,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(Theme.color.surfaces.surfaceContainer).padding(8.dp)
        ) {
            val options = remember {   listOf(
                NutritionScreenState.MealType.Breakfast,
                NutritionScreenState.MealType.Lunch,
                NutritionScreenState.MealType.Dinner,
                NutritionScreenState.MealType.Snacks,
            )}
            options.forEach { item ->
                CheckboxItem(
                    text = item.displayName,
                    isChecked = selectedItem == item.displayName,
                    onCheckedChange = {
                        listener.onMealTypeSelected(item)
                        onItemSelected()
                        onDismissRequest()
                    },
                    style = CheckboxStyle.Tick
                )
            }
        }
    }
}