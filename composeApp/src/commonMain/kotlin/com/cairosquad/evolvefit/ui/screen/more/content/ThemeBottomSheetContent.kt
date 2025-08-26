package com.cairosquad.evolvefit.ui.screen.more.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.CheckboxItem
import com.cairosquad.evolvefit.design_system.component.PrimaryButton
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.viewmodel.more.MoreScreenState
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.choose_theme
import evolvefit.composeapp.generated.resources.confirm
import evolvefit.composeapp.generated.resources.dark
import evolvefit.composeapp.generated.resources.dark_mode
import evolvefit.composeapp.generated.resources.light
import evolvefit.composeapp.generated.resources.light_mode
import evolvefit.composeapp.generated.resources.theme_description
import org.jetbrains.compose.resources.stringResource


@Composable
fun ThemeBottomSheetContent(
    state: MoreScreenState,
    onConfirm: (MoreScreenState.Theme) -> Unit,
    onSelectTheme: (MoreScreenState.Theme) -> Unit
) {

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(bottom = 4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = stringResource(Res.string.choose_theme),
                style = Theme.textStyle.label.mediumMedium16,
                color = Theme.color.surfaces.onSurface
            )
            Text(
                text = stringResource(Res.string.theme_description),
                style = Theme.textStyle.label.mediumMedium12,
                color = Theme.color.surfaces.onSurfaceVariant
            )
        }

        CheckboxItem(
            text = stringResource(Res.string.dark_mode),
            isChecked = state.currentTheme == MoreScreenState.Theme.DARK,
            icon = Res.drawable.dark,
            onCheckedChange = { onSelectTheme(MoreScreenState.Theme.DARK) }
        )

        CheckboxItem(
            text = stringResource(Res.string.light_mode),
            isChecked = state.currentTheme == MoreScreenState.Theme.LIGHT,
            icon = Res.drawable.light,
            onCheckedChange = { onSelectTheme(MoreScreenState.Theme.LIGHT) }
        )

        PrimaryButton(
            modifier = Modifier
                .padding(bottom = 16.dp, top = 38.dp),
            text = stringResource(Res.string.confirm),
            onClick = { onConfirm(state.currentTheme) },
            isEnabled = true,
            enabledTextColor = Theme.color.brand.onPrimary,
            textStyle = Theme.textStyle.body.mediumMedium14,
        )
    }
}
