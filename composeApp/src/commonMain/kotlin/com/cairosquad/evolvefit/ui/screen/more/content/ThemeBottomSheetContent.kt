package com.cairosquad.evolvefit.ui.screen.more.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.CheckboxItem
import com.cairosquad.evolvefit.design_system.component.PrimaryButton
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.viewmodel.profile.MoreScreenState
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.dark
import evolvefit.composeapp.generated.resources.light


@Composable
fun ThemeBottomSheetContent(
    state: MoreScreenState,
    onThemeSelected: (MoreScreenState.Theme) -> Unit,
    onConfirm: () -> Unit
) {
    val selectedTheme = state.currentTheme

    Column(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp).padding(bottom = 4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = "Choose Theme",
                style = Theme.textStyle.label.mediumMedium16,
                color = Theme.color.surfaces.onSurface
            )
            Text(
                text = "Select your preferred look and feel for the app.",
                style = Theme.textStyle.label.mediumMedium12,
                color = Theme.color.surfaces.onSurfaceVariant
            )
        }

        CheckboxItem(
            text = "Dark Mode",
            isChecked = selectedTheme == MoreScreenState.Theme.DARK,
            icon = Res.drawable.dark,
            onCheckedChange = { onThemeSelected(MoreScreenState.Theme.DARK) }
        )
        CheckboxItem(
            text = "Light Mode",
            isChecked = selectedTheme == MoreScreenState.Theme.LIGHT,
            icon = Res.drawable.light,
            onCheckedChange = { onThemeSelected(MoreScreenState.Theme.LIGHT) }
        )
        PrimaryButton(
            text = "Confirm",
            onClick = onConfirm,
            modifier = Modifier.padding(bottom = 16.dp, top = 38.dp),
            isEnabled = true,
            enabledTextColor = Theme.color.brand.onPrimary,
            textStyle = Theme.textStyle.body.mediumMedium14,
        )
    }
}
