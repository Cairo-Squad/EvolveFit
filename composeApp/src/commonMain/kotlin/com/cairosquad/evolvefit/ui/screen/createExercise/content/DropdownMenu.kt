package com.cairosquad.evolvefit.ui.screen.createExercise.content

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
    selectedItem: String?,
    expanded: Boolean,
    onItemSelected: (String) -> Unit,
    onDismissRequest: () -> Unit,
    isChecked: (String) -> Boolean
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        modifier = Modifier.background(Theme.color.surfaces.surface)
    ) {
        items.forEach { item ->
            DropdownMenuItem(
                text = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = item,
                            style = Theme.textStyle.label.smallRegular12,
                            color = Theme.color.surfaces.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        CustomTick(
                            isChecked = item == selectedItem,
                            onCheckedChange = { onItemSelected(item) }
                        )
                    }
                },
                onClick = { onItemSelected(item) }
            )
        }
    }
}


@Preview
@Composable
private fun CustomDropdownMenuPrev() {
    AppTheme(isDarkTheme = true) {
        var selectedItem by remember { mutableStateOf<String?>(null) }
        var expanded by remember { mutableStateOf(true) }

        Text(
            modifier = Modifier
                .clickable { expanded = !expanded }
                .padding(16.dp),
            text = "Open Dropdown",
            style = Theme.textStyle.label.smallRegular12,
            color = Theme.color.surfaces.onSurfaceVariant
        )
        CustomDropdownMenu(
            items = listOf("Upper Body", "Lower Body", "Full Body"),
            selectedItem = selectedItem,
            onItemSelected = { selectedItem = it },
            expanded = expanded,
            onDismissRequest = { expanded = false },
            isChecked = { it == selectedItem }
        )
    }
}
