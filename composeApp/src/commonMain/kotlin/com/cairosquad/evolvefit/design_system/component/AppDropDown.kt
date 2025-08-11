package com.cairosquad.evolvefit.design_system.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.composables.InputField
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ic_arrow_down
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CustomDropDownMenu(
    selectedText: String,
    options: List<String>,
    iconPainter: Painter? = null,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = ""
) {
    var expanded by remember { mutableStateOf(false) }

    val isPlaceholder = selectedText.isBlank()
    val displayText = if (isPlaceholder) placeholder else selectedText
    val textColor = if (isPlaceholder)
        Theme.color.surfaces.onSurfaceVariant
    else
        Theme.color.surfaces.onSurfaceContainer

    val rotation by animateFloatAsState(targetValue = if (expanded) 180f else 0f)

    // Wrap everything inside a Box to anchor the dropdown
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .clickable { expanded = !expanded } // make the whole row clickable
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(Theme.color.surfaces.surfaceContainer)
                .padding(horizontal = 12.dp, vertical = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicText(
                modifier = Modifier.padding(start = 8.dp),
                text = displayText,
                style = Theme.textStyle.label.smallRegular14.copy(
                    color = textColor
                )
            )
            Spacer(modifier = Modifier.weight(1f))

            if (iconPainter != null) {
                Icon(
                    painter = iconPainter,
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .graphicsLayer { rotationZ = rotation }
                )
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(Theme.color.surfaces.surfaceContainer)
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = option,
                            style = Theme.textStyle.label.smallRegular14.copy(
                                color = Theme.color.surfaces.onSurfaceContainer
                            )
                        )
                    },
                    onClick = {
                        expanded = false
                        onOptionSelected(option)
                    },
                )
            }
        }
    }
}



@Preview()
@Composable
fun AppDropDownMenuPreview() {
    var selectedOption by remember { mutableStateOf("") }
    val options = listOf("Lose Weight", "Build Muscle", "Stay Fit")

    AppTheme(isDarkTheme = true) {
        CustomDropDownMenu(
            selectedText = selectedOption,
            options = options,
            placeholder = "Choose your goal",
            iconPainter = painterResource(Res.drawable.ic_arrow_down),
            onOptionSelected = { selectedOption = it },
            modifier = Modifier.padding(horizontal = 16.dp).padding(top=30.dp)
        )
    }
}


@Composable
fun CustomAnimatedDropdownMenu(
    items: List<String>,
    selectedItem: String,
    isExpanded: Boolean,
    onItemClicked: (String) -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    itemContent: @Composable (String, Boolean, () -> Unit) -> Unit
) {
    val rotation by animateFloatAsState(
        targetValue = if (isExpanded) 180f else 0f,
        animationSpec = tween(durationMillis = 300),
        label = "arrow_rotation"
    )
    val arrowRotationModifier = Modifier.graphicsLayer {
        rotationZ = rotation
        transformOrigin = TransformOrigin(0.5f, 0.5f)
    }

    Column(modifier = modifier) {
        InputField(
            value = selectedItem,
            onValueChange = {},
            trailingIcon = Res.drawable.ic_arrow_down,
            onTrailingIconClick = onDismissRequest
        )

        AnimatedVisibility(
            visible = isExpanded,
            enter = fadeIn(animationSpec = tween(200)),
            exit = fadeOut(animationSpec = tween(200))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {
                items.forEach { item ->
                    val isSelected = item == selectedItem
                    itemContent(item, isSelected) {
                        onItemClicked(item)
                        onDismissRequest()
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun DropdownMenuPreview() {
    var selectedItem by remember { mutableStateOf("Lunch") }
    var isMenuExpanded by remember { mutableStateOf(false) }
    val items = listOf("Breakfast", "Lunch", "Dinner", "Snack")
    AppTheme(isDarkTheme = true) {
        CustomAnimatedDropdownMenu(
            modifier = Modifier
                .padding(top = 40.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(Theme.color.surfaces.surfaceContainer),
            items = items,
            selectedItem = selectedItem,
            isExpanded = isMenuExpanded,
            onItemClicked = {
                selectedItem = it
            },
            onDismissRequest = {
                isMenuExpanded = !isMenuExpanded
            },
            itemContent = { item, isSelected, click ->
                CheckboxItem(
                    text = item,
                    isChecked = isSelected,
                    onCheckedChange = {
                        click()
                    },
                    style = CheckboxStyle.Tick
                )
            }
        )
    }
}

@Preview
@Composable
private fun DropdownMenuTextPreview() {
    var selectedItem by remember { mutableStateOf("Lunch") }
    var isMenuExpanded by remember { mutableStateOf(false) }
    val items = listOf("Breakfast", "Lunch", "Dinner", "Snack")
    AppTheme(isDarkTheme = true) {
        CustomAnimatedDropdownMenu(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(Theme.color.surfaces.surfaceContainer)
                .padding(top=50.dp),
            items = items,
            selectedItem = selectedItem,
            isExpanded = isMenuExpanded,
            onItemClicked = {
                selectedItem = it
            },
            onDismissRequest = {
                isMenuExpanded = !isMenuExpanded
            },
            itemContent = { item, isSelected, clicked ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .clickable(
                            onClick = { clicked() }
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = item, color = Theme.color.brand.primary)
                }

            }
        )
    }
}