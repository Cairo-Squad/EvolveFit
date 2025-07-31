package com.cairosquad.evolvefit.design_system.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cairosquad.evolvefit.design_system.theme.Theme
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ic_arrow_down
import evolvefit.composeapp.generated.resources.ic_arrow_up
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
private fun LanguageSelector(
    selectedLanguage: String,
    onLanguageSelected: (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.weight(1f))
        LanguageDropdown(
            selectedLanguage = selectedLanguage,
            onLanguageSelected = onLanguageSelected
        )
    }
}

val languages = listOf(
    "English", "العربية"
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LanguageDropdown(
    selectedLanguage: String = "English",
    onLanguageSelected: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    val selectedLanguage = languages.find { it == selectedLanguage } ?: languages.first()

    Box(modifier = modifier) {
        Row(
            modifier = Modifier
                .background(
                    Color.Transparent,
                    RoundedCornerShape(6.dp)
                )
                .padding(horizontal = 12.dp, vertical = 8.dp)
                .clickable { expanded = !expanded },
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
                        resource = if (isExpanded) Res.drawable.ic_arrow_up else Res.drawable.ic_arrow_down
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
            languages.forEach { language ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = language,
                            color = Theme.color.surfaces.textColor,
                            fontSize = 14.sp,
                            lineHeight = 14.sp,
                            letterSpacing = 0.sp,
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
