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
import com.cairosquad.evolvefit.domain.model.Language
import com.cairosquad.evolvefit.viewmodel.profile.MoreScreenState


@Composable
fun LanguageBottomSheetContent(
    state: MoreScreenState,
    onLanguageSelected: (Language) -> Unit,
    onConfirm: () -> Unit
) {
    val selectedLanguage = state.currentLanguage

    Column(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp).padding(bottom = 4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = "Choose Language",
                style = Theme.textStyle.label.mediumMedium16,
                color = Theme.color.surfaces.onSurface,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "This language will be used throughout the app.",
                style = Theme.textStyle.label.mediumMedium12,
                color = Theme.color.surfaces.onSurfaceVariant,
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }
        CheckboxItem(
            text = "English",
            isChecked = selectedLanguage == Language.ENGLISH,
            onCheckedChange = { }
        )
        CheckboxItem(
            text = "Arabic",
            isChecked = selectedLanguage == Language.ARABIC,
            onCheckedChange = { onLanguageSelected(Language.ARABIC)}
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
