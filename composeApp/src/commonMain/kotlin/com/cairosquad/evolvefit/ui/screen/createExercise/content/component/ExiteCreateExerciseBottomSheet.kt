package com.cairosquad.evolvefit.ui.screen.createExercise.content.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.BottomSheet
import com.cairosquad.evolvefit.design_system.component.PrimaryButton
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.cancel
import evolvefit.composeapp.generated.resources.exit_confirmation_message
import evolvefit.composeapp.generated.resources.exit_confirmation_title
import evolvefit.composeapp.generated.resources.exit_without_saving
import evolvefit.composeapp.generated.resources.im_save_dark
import evolvefit.composeapp.generated.resources.im_save_light
import evolvefit.composeapp.generated.resources.save_image
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ExitCreateExerciseBottomSheet(
    isVisible: Boolean = false,
    onDismiss: () -> Unit = {},
    onCancelClicked: () -> Unit = {},
    onExitWithoutSavingClicked: () -> Unit = {}
) {
    val saveImage =
        if (isSystemInDarkTheme()) painterResource(Res.drawable.im_save_dark)
        else painterResource(Res.drawable.im_save_light)
    BottomSheet(
        isVisible = isVisible,
        onDismiss = onDismiss,
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.padding(bottom = 16.dp)
                    .padding(horizontal = 98.dp),
                painter = saveImage,
                contentDescription = stringResource(Res.string.save_image)
            )
            Text(
                modifier = Modifier
                    .padding(bottom = 8.dp),
                text = stringResource(Res.string.exit_confirmation_title),
                style = Theme.textStyle.title.mediumMedium16,
                color = Theme.color.surfaces.onSurface
            )
            Text(
                modifier = Modifier
                    .padding(bottom = 24.dp),
                text = stringResource(Res.string.exit_confirmation_message),
                style = Theme.textStyle.label.smallRegular12,
                color = Theme.color.surfaces.onSurfaceVariant
            )
            PrimaryButton(
                modifier = Modifier.padding(top = 12.dp),
                text = stringResource(Res.string.cancel),
                onClick = onCancelClicked
            )
            PrimaryButton(
                text = stringResource(Res.string.exit_without_saving),
                enabledContainerColor = Color.Transparent,
                enabledTextColor = Theme.color.surfaces.onSurfaceContainer,
                onClick = onExitWithoutSavingClicked
            )
        }

    }
}


@Preview()
@Composable
fun ExiteCreateExerciseBottomSheetPreview() {
    AppTheme(isDarkTheme = false) {
        ExitCreateExerciseBottomSheet(
            isVisible = true,
            onDismiss = {},
            onCancelClicked = {},
            onExitWithoutSavingClicked = {}
        )
    }
}