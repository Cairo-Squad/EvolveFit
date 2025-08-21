package com.cairosquad.evolvefit.ui.screen.communityWorkout.content.component

import androidx.compose.runtime.Composable
import com.cairosquad.evolvefit.design_system.component.appbar.ActionIconButton
import com.cairosquad.evolvefit.design_system.component.appbar.CustomAppBar
import com.cairosquad.evolvefit.design_system.theme.Theme
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.back
import evolvefit.composeapp.generated.resources.ic_back
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun AppBar(navigateBack: () -> Unit) {
    CustomAppBar(
        title = "Community",
        header = {
            ActionIconButton(
                icon = painterResource(Res.drawable.ic_back),
                contentDescription = stringResource(Res.string.back),
                tint = Theme.color.surfaces.onSurface,
                onClick = navigateBack
            )
        }
    )
}