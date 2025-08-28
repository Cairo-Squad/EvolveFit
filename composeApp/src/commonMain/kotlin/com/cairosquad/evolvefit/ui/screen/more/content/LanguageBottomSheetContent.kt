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
import com.cairosquad.evolvefit.viewmodel.more.MoreScreenState
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.arabic
import evolvefit.composeapp.generated.resources.choose_language
import evolvefit.composeapp.generated.resources.confirm
import evolvefit.composeapp.generated.resources.english
import evolvefit.composeapp.generated.resources.language_description
import org.jetbrains.compose.resources.stringResource


@Composable
fun LanguageBottomSheetContent(
    state: MoreScreenState,
    onConfirm: (selectedLanguage: Language) -> Unit,
    onSelectLanguage: (selectedLanguage: Language) -> Unit
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
                modifier = Modifier
                    .padding(bottom = 4.dp),
                text = stringResource(Res.string.choose_language),
                style = Theme.textStyle.label.mediumMedium16,
                color = Theme.color.surfaces.onSurface,
            )
            Text(
                modifier = Modifier
                    .padding(bottom = 4.dp),
                text = stringResource(Res.string.language_description),
                style = Theme.textStyle.label.mediumMedium12,
                color = Theme.color.surfaces.onSurfaceVariant,
            )
        }
        CheckboxItem(
            text = stringResource(Res.string.english),
            isChecked = state.tempLanguage== Language.ENGLISH,
            onCheckedChange = { onSelectLanguage(Language.ENGLISH) }
        )
        CheckboxItem(
            text = stringResource(Res.string.arabic),
            isChecked = state.tempLanguage== Language.ARABIC,
            onCheckedChange = { onSelectLanguage(Language.ARABIC) }
        )
        PrimaryButton(
            modifier = Modifier
                .padding(bottom = 16.dp, top = 38.dp),
            text = stringResource(Res.string.confirm),
            onClick = { onConfirm (state.tempLanguage)},
            isEnabled = true,
            enabledTextColor = Theme.color.brand.onPrimary,
            textStyle = Theme.textStyle.body.mediumMedium14,
        )
    }
}
