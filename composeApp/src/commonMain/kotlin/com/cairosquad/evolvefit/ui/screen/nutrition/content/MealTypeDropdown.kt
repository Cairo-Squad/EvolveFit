package com.cairosquad.evolvefit.ui.screen.nutrition.content

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.BottomSheet
import com.cairosquad.evolvefit.design_system.component.CheckboxItem
import com.cairosquad.evolvefit.design_system.component.CheckboxStyle
import com.cairosquad.evolvefit.design_system.component.PrimaryButton
import com.cairosquad.evolvefit.design_system.composables.InputField
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.viewmodel.nutrition.NutritionScreenState
import com.cairosquad.evolvefit.viewmodel.nutrition.NutritionViewModel
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.add_button
import evolvefit.composeapp.generated.resources.add_new_meal
import evolvefit.composeapp.generated.resources.calories
import evolvefit.composeapp.generated.resources.ic_arrow_down
import evolvefit.composeapp.generated.resources.ic_fire
import evolvefit.composeapp.generated.resources.log_meal_details
import evolvefit.composeapp.generated.resources.meal_name_placeholder
import evolvefit.composeapp.generated.resources.nutrition
import org.jetbrains.compose.resources.stringResource

@Composable
fun MealTypeDropdownMenu(
    state: NutritionScreenState,
    nutritionViewModel: NutritionViewModel,
    modifier: Modifier = Modifier
) {
    val mealTypeOptions = NutritionScreenState.MealType.entries.associateWith {
        stringResource(it.displayName)
    }
    val rotation by animateFloatAsState(
        targetValue = if (state.isMealTypeMenuExpanded) 180f else 0f,
        animationSpec = tween(durationMillis = 300),
        label = "arrow_rotation"
    )
    val arrowRotationModifier = Modifier.graphicsLayer {
        rotationZ = rotation
        transformOrigin = TransformOrigin(0.5f, 0.5f)
    }
    AnimatedVisibility(
        modifier = modifier.fillMaxWidth(),
        visible = state.isAddMealSheetVisible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        BottomSheet(
            isVisible = state.isAddMealSheetVisible, onDismiss = {
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
                MealNameInputField(
                    modifier = Modifier.padding(top = 16.dp),
                    mealName = state.mealNameInput,
                    onValueChange = nutritionViewModel::onMealNameChanged
                )

                Row(
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .fillMaxWidth()
                ) {

                    MealCaloriesInputField(
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .weight(1f),
                        mealCalories = state.mealCaloriesInput,
                        onValueChange = nutritionViewModel::onMealCaloriesChanged)

                    Box(modifier = Modifier.weight(1f)) {
                        InputField(
                            value = stringResource(state.selectedMeal.displayName),
                            onValueChange = {},
                            trailingIcon = Res.drawable.ic_arrow_down,
                            onTrailingIconClick = nutritionViewModel::onToggleMealTypeMenu,
                            trailingIconModifier = arrowRotationModifier,
                            readOnly = true
                        )
                        DropdownMenu(
                            items = mealTypeOptions.values.toList(),
                            selectedItem = mealTypeOptions[state.selectedMeal] ?: "",
                            expanded = state.isMealTypeMenuExpanded,
                            onItemClicked = { selected ->
                                val selectedType =
                                    mealTypeOptions.entries.first { it.value == selected }.key
                                nutritionViewModel.onMealTypeSelected(selectedType)
                            },
                            onDismissRequest = {},
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .fillMaxWidth()
                                .padding(top = 48.dp)
                        )
                    }
                }
                PrimaryButton(
                    modifier = Modifier
                        .padding(top = 40.dp, bottom = 16.dp),
                    text = stringResource(Res.string.add_button),
                    isEnabled = state.isAddButtonEnabled,
                    onClick = {
                        nutritionViewModel.onConfirmAddMealClicked(
                            NutritionScreenState.MealHistory(
                                name = state.mealNameInput,
                                type = state.selectedMeal,
                                calories = state.mealCaloriesInput.toInt()
                            )
                        )
                    })
            }
        }
    }
}

@Composable
private fun MealNameInputField(
    mealName: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    InputField(
        modifier = modifier,
        value = mealName,
        onValueChange = onValueChange,
        placeholder = stringResource(Res.string.meal_name_placeholder),
        leadingIcon = Res.drawable.nutrition
    )
}

@Composable
private fun MealCaloriesInputField(
    mealCalories: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    InputField(
        modifier = modifier,
        value = mealCalories,
        keyboardType = KeyboardType.Number,
        onValueChange = onValueChange,
        placeholder = stringResource(Res.string.calories),
        leadingIcon = Res.drawable.ic_fire
    )
}

@Composable
fun DropdownMenu(
    items: List<String>,
    selectedItem: String?,
    expanded: Boolean,
    onItemClicked: (String) -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(
        visible = expanded,
        enter = fadeIn(animationSpec = tween(500)),
        exit = fadeOut(animationSpec = tween(500))
    ) {
        Column(
            modifier = modifier.fillMaxWidth().clip(RoundedCornerShape(8.dp))
                .background(Theme.color.surfaces.surfaceContainer).padding(8.dp)
        ) {
            items.forEach { item ->
                val isSelected = item == selectedItem

                CheckboxItem(
                    text = item, isChecked = isSelected, onCheckedChange = {
                        onItemClicked(item)
                        onDismissRequest()
                    }, style = CheckboxStyle.Tick
                )
            }
        }
    }
}