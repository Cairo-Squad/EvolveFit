package com.cairosquad.evolvefit.ui.screen.createExercise.content

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.CustomTick
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CustomDropdownMenu(
    items: List<String>,
    expanded: Boolean,
    onItemSelected: (String) -> Unit,
    onDismissRequest: () -> Unit,
    isChecked: (String) -> Boolean
) {
    DropdownMenu(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Theme.color.surfaces.surfaceContainer,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 16.dp),
        expanded = expanded,
        onDismissRequest = onDismissRequest
    ) {
        items.forEach { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        onItemSelected(item)
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = item,
                    style = Theme.textStyle.body.mediumMedium14,
                    color = Theme.color.surfaces.onSurfaceContainer
                )
                Spacer(modifier = Modifier.weight(1f))
                CustomTick(
                    modifier = Modifier.padding(vertical = 8.dp),
                    isChecked = isChecked(item),
                    onCheckedChange = { onItemSelected(item) }
                )
            }
        }
    }
}

@Preview
@Composable
private fun CustomDropdownMenuPrev() {
    AppTheme(isDarkTheme = true) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Theme.color.surfaces.surface)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            var selectedItem by remember { mutableStateOf<String?>(null) }
            var expanded by remember { mutableStateOf(true) }

            Column {
                Text(
                    modifier = Modifier
                        .clickable { expanded = !expanded }
                        .padding(bottom = 8.dp),
                    text = "Open Dropdown",
                    style = Theme.textStyle.label.smallRegular12,
                    color = Theme.color.surfaces.onSurfaceContainer
                )
                CustomDropdownMenu(
                    items = listOf(
                        "Upper Body",
                        "Lower Body",
                        "Full Body",
                        "Upper Body1",
                        "Lower Body1",
                        "Full Body1"
                    ),
                    onItemSelected = { selectedItem = it },
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    isChecked = { it == selectedItem }
                )
            }
        }
    }
}

