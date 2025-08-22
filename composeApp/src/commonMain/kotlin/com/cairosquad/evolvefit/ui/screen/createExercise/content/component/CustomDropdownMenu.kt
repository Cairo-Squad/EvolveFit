package com.cairosquad.evolvefit.ui.screen.createExercise.content.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.CustomTick
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CustomDropdownMenu(
    modifier: Modifier = Modifier,
    items: List<String>,
    onItemSelected: (String) -> Unit,
    isChecked: (String) -> Boolean
) {
    Column(
        modifier = Modifier
            .background(
                color = Theme.color.surfaces.surfaceContainer,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(16.dp)
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items.forEach { item ->
            Row(
                modifier = modifier
                    .fillMaxWidth().clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) { onItemSelected(item) },
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
                )
            }
        }
    }
}

@Preview
@Composable
private fun CustomDropdownMenuPreview() {
    AppTheme(isDarkTheme = true) {
        CustomDropdownMenu(
            items = listOf(
                "Item 1",
                "Item 2",
                "Item 3",
                "Item 4",
                "Item 5",
                "Item 6",
                "Item 7",
                "Item 8",
                "Item 9"
            ),
            onItemSelected = {},
            isChecked = { false }
        )
    }
}