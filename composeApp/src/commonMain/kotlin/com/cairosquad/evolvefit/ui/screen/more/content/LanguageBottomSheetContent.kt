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
import com.cairosquad.evolvefit.domain.model.Language
import com.cairosquad.evolvefit.viewmodel.profile.MoreScreenState
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.arabic
import evolvefit.composeapp.generated.resources.english
import org.jetbrains.compose.resources.stringResource


@Composable
fun LanguageBottomSheetContent(
    state: MoreScreenState,
    onConfirm: (selectedLanguage: Language) -> Unit
) {
    var tempSelectedLanguage by remember { mutableStateOf(state.profile.preferredLanguage) }

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
            text = stringResource(Res.string.english),
            isChecked = tempSelectedLanguage == Language.ENGLISH,
            onCheckedChange = { tempSelectedLanguage = Language.ENGLISH }
        )
        CheckboxItem(
            text = stringResource(Res.string.arabic),
            isChecked = tempSelectedLanguage == Language.ARABIC,
            onCheckedChange = { tempSelectedLanguage = Language.ARABIC }
        )
        PrimaryButton(
            text = "Confirm",
            onClick = { onConfirm(tempSelectedLanguage) },
            modifier = Modifier.padding(bottom = 16.dp, top = 38.dp),
            isEnabled = true,
            enabledTextColor = Theme.color.brand.onPrimary,
            textStyle = Theme.textStyle.body.mediumMedium14,
        )
    }
}
