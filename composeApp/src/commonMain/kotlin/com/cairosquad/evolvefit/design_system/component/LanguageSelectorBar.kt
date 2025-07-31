package com.cairosquad.evolvefit.design_system.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cairosquad.evolvefit.design_system.theme.Theme
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ic_arrow_down
import evolvefit.composeapp.generated.resources.ic_arrow_up
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

private val supportedLanguages = listOf("English", "العربية")

@Composable
fun LanguageSelector(
    selectedLanguage: String,
    onLanguageSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    availableLanguages: List<String> = supportedLanguages
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        Row(
            modifier = Modifier
                .clickable { expanded = !expanded }
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = selectedLanguage,
                color = Theme.color.surfaces.textColor,
                style = Theme.textStyle.label.mediumMedium16
            )

            AnimatedContent(
                targetState = expanded,
                transitionSpec = {
                    fadeIn(tween(200)) togetherWith fadeOut(tween(200))
                },
                label = "arrowSwitch"
            ) { isExpanded ->
                Icon(
                    painter = painterResource(
                        if (isExpanded) Res.drawable.ic_arrow_up else Res.drawable.ic_arrow_down
                    ),
                    contentDescription = null,
                    tint = Theme.color.surfaces.textColor,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            availableLanguages.forEach { language ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = language,
                            color = Theme.color.surfaces.textColor,
                            fontSize = 14.sp,
                            style = Theme.textStyle.label.mediumMedium16
                        )
                    },
                    onClick = {
                        onLanguageSelected(language)
                        expanded = false
                    }
                )
            }
        }
    }
}
@Preview
@Composable
fun LanguageSelectorPreview() {
    var selectedLanguage by remember { mutableStateOf("English") }

    LanguageSelector(
        selectedLanguage = selectedLanguage,
        onLanguageSelected = { selectedLanguage = it }
    )
}
