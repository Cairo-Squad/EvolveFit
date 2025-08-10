package com.cairosquad.evolvefit.ui.screen.createExercise

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.CheckboxItem
import com.cairosquad.evolvefit.design_system.component.CheckboxStyle
import com.cairosquad.evolvefit.design_system.component.PrimaryButton
import com.cairosquad.evolvefit.design_system.component.appbar.ActionIconButton
import com.cairosquad.evolvefit.design_system.component.appbar.CustomAppBar
import com.cairosquad.evolvefit.design_system.composables.InputField
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.screen.createExercise.content.RowWithIcon
import com.cairosquad.evolvefit.ui.screen.register.content.RegisterHeader
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ic_app_logo
import evolvefit.composeapp.generated.resources.ic_back
import evolvefit.composeapp.generated.resources.login
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CreateExerciseScreen(
    navigateBack: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Theme.color.surfaces.surface)
            .padding(horizontal = 16.dp)
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CustomAppBar(
            modifier = Modifier.padding(bottom = 16.dp),
            header = {
                ActionIconButton(
                    icon = painterResource(Res.drawable.ic_back),
                    contentDescription = "Back",
                    tint = Theme.color.surfaces.onSurface,
                    onClick = {}
                )
            }
        )
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                RegisterHeader(
                    modifier = Modifier.padding(bottom = 24.dp),
                    title = "Create Exercise",
                    description = "Add a new exercise to personalize your workout and stay on track."
                )
            }
            item {
                Image(
                    modifier = Modifier.padding(bottom = 8.dp),
                    painter = painterResource(Res.drawable.ic_app_logo),
                    contentDescription = "Upload image"
                )
            }
            item {
                Text(
                    modifier = Modifier.padding(bottom = 24.dp)
                        .clickable(onClick = {}),
                    text = "Upload image ",
                    style = Theme.textStyle.label.smallRegular12,
                    color = Theme.color.surfaces.onSurfaceVariant
                )
            }
            item {
                InputField(
                    modifier = Modifier.padding(bottom = 12.dp),
                    value = "",
                    onValueChange = {},
                    placeholder = "Enter exercise name"
                )
            }
            item {
                RowWithIcon(
                    modifier = Modifier.padding(bottom = 12.dp),
                    isIconClicked = false,
                    onIconClicked = {}
                )
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(bottom = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    var isChecked by remember { mutableStateOf(false) }
                    CheckboxItem(
                        modifier = Modifier.weight(1f),
                        text = "Add Duration",
                        isChecked = isChecked,
                        onCheckedChange = { isChecked = !isChecked },
                        style = CheckboxStyle.Tick
                    )
                    CheckboxItem(
                        modifier = Modifier.weight(1f),
                        text = "Add Reps",
                        isChecked = isChecked,
                        onCheckedChange = { isChecked = !isChecked },
                        style = CheckboxStyle.Tick
                    )
                }
            }
            item {
                RowWithIcon(
                    modifier = Modifier.padding(bottom = 12.dp),
                    isIconClicked = false,
                    onIconClicked = {}
                )
            }
            item {
                InputField(
                    modifier = Modifier
                        .height(100.dp),
                    value = "",
                    onValueChange = {},
                    isSingleLine = false,
                    maxCharacters = 3000,

                    )
            }
            item {
                PrimaryButton(
                    modifier = Modifier.padding(top = 40.dp),
                    text = stringResource(Res.string.login),
                    onClick = {}
                )
            }
        }
    }
}

@Preview
@Composable
private fun CreateExerciseScreenPreview() {
    AppTheme(
        isDarkTheme = true
    ) {
        CreateExerciseScreen(navigateBack = {})
    }
}