package com.cairosquad.evolvefit.design_system.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.clickable
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cairosquad.evolvefit.design_system.theme.Theme
import evolvefit.composeapp.generated.resources.*
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CustomAppBar(
    type: AppBarType,
    selectedLanguage: String = "English",
    onLanguageSelected: (String) -> Unit = {},
    currentStep: Int = 1,
    totalSteps: Int = 8,
    onBackClick: () -> Unit = {},
    userName: String = "",
    greetingMessage: String = "Have a nice day!",
    userAvatarEmoji: String = "👨",
    title: String = "",
    onActionClick: () -> Unit = {},
    onBookmarkClick: () -> Unit = {},
    onShareClick: () -> Unit = {},
    backgroundColor: Color = Color.Transparent,
    contentColor: Color = Theme.color.surfaces.onSurface
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .padding(16.dp)
    ) {
        when (type) {
            AppBarType.LANGUAGE_SELECTOR -> LanguageSelectorAppBar(
                selectedLanguage = selectedLanguage,
                onLanguageSelected = onLanguageSelected
            )

            AppBarType.PROGRESS_WITH_BACK -> ProgressAppBar(
                currentStep = currentStep,
                totalSteps = totalSteps,
                onBackClick = onBackClick,
                contentColor = contentColor
            )

            AppBarType.USER_GREETING -> UserGreetingAppBar(
                userName = userName,
                greetingMessage = greetingMessage,
                userAvatarEmoji = userAvatarEmoji,
                contentColor = contentColor
            )

            AppBarType.SIMPLE_TITLE -> CenteredTitleAppBar(title, contentColor)

            AppBarType.TITLE_WITH_BACK -> TitleWithBackAppBar(title, onBackClick, contentColor)

            AppBarType.TITLE_WITH_ACTION -> TitleWithActionAppBar(
                title,
                onActionClick,
                contentColor
            )

            AppBarType.NAVIGATION_WITH_ACTIONS -> NavigationWithActionsAppBar(
                onBackClick = onBackClick,
                onBookmarkClick = onBookmarkClick,
                onShareClick = onShareClick,
                contentColor = contentColor
            )
        }
    }
}

@Composable
private fun LanguageSelectorAppBar(
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

@Composable
private fun ProgressAppBar(
    currentStep: Int,
    totalSteps: Int,
    onBackClick: () -> Unit,
    contentColor: Color
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onBackClick) {
            Icon(
                painter = painterResource(Res.drawable.ic_back),
                contentDescription = "Back",
                tint = contentColor
            )
        }

        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.weight(1f)
            ) {
                repeat(totalSteps) { index ->
                    val stepColor by animateColorAsState(
                        targetValue = if (index < currentStep)
                            Theme.color.brand.primary
                        else
                            Theme.color.surfaces.surfaceVariant,
                        animationSpec = tween(300),
                        label = "stepColor"
                    )
                    Box(
                        modifier = Modifier
                            .width(32.dp)
                            .height(4.dp)
                            .background(stepColor, RoundedCornerShape(2.dp))
                    )
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = "$currentStep/$totalSteps",
                color = Theme.color.surfaces.onSurfaceVariant,
                style = Theme.textStyle.label.smallRegular12
            )
        }
    }
}

@Composable
private fun UserGreetingAppBar(
    userName: String,
    greetingMessage: String,
    userAvatarEmoji: String,
    contentColor: Color
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Transparent, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(text = userAvatarEmoji, fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column {
            Text(
                text = "Hello $userName",
                color = Theme.color.surfaces.onSurfaceVariant,
                style = Theme.textStyle.body.mediumMedium12
            )
            Text(
                text = greetingMessage,
                color = contentColor,
                style = Theme.textStyle.body.mediumMedium12
            )
        }
    }
}

@Composable
private fun CenteredTitleAppBar(title: String, contentColor: Color) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            color = contentColor,
            style = Theme.textStyle.title.largeBold16
        )
    }
}

@Composable
private fun TitleWithBackAppBar(title: String, onBackClick: () -> Unit, contentColor: Color) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onBackClick) {
            Icon(
                painter = painterResource(Res.drawable.ic_back),
                contentDescription = "Back",
                tint = contentColor
            )
        }

        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = title,
                color = contentColor,
                style = Theme.textStyle.title.largeBold16
            )
        }
    }
}

@Composable
private fun TitleWithActionAppBar(title: String, onActionClick: () -> Unit, contentColor: Color) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = title,
                color = contentColor,
                style = Theme.textStyle.title.largeBold16
            )
        }

        IconButton(onClick = onActionClick) {
            Icon(
                painter = painterResource(Res.drawable.ic_group),
                contentDescription = "Action",
                tint = Theme.color.surfaces.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun NavigationWithActionsAppBar(
    onBackClick: () -> Unit,
    onBookmarkClick: () -> Unit,
    onShareClick: () -> Unit,
    contentColor: Color
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onBackClick) {
            Icon(
                painter = painterResource(Res.drawable.ic_back),
                contentDescription = "Back",
                tint = contentColor
            )
        }

        Row {
            IconButton(onClick = onBookmarkClick) {
                Icon(
                    painter = painterResource(Res.drawable.ic_bookmark),
                    contentDescription = "Bookmark",
                    tint = contentColor
                )
            }

            IconButton(onClick = onShareClick) {
                Icon(
                    painter = painterResource(Res.drawable.ic_share),
                    contentDescription = "Share",
                    tint = contentColor
                )
            }
        }
    }
}

enum class AppBarType {
    LANGUAGE_SELECTOR,
    PROGRESS_WITH_BACK,
    USER_GREETING,
    SIMPLE_TITLE,
    TITLE_WITH_BACK,
    TITLE_WITH_ACTION,
    NAVIGATION_WITH_ACTIONS
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
fun View(){
    CustomAppBar(
        type = AppBarType.LANGUAGE_SELECTOR,
    )

}