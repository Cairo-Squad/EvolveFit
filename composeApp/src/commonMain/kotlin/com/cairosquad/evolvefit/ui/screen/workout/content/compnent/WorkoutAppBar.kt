package com.cairosquad.evolvefit.ui.screen.workout.content.compnent

import androidx.compose.runtime.Composable
import com.cairosquad.evolvefit.design_system.component.appbar.ActionIconButton
import com.cairosquad.evolvefit.design_system.component.appbar.CustomAppBar
import com.cairosquad.evolvefit.design_system.theme.Theme
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.community
import evolvefit.composeapp.generated.resources.ic_group
import evolvefit.composeapp.generated.resources.workouts
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun WorkoutAppBar(onCommunityClick: () -> Unit) {
    CustomAppBar(
        title = stringResource(Res.string.workouts),
        tail = {
            ActionIconButton(
                icon = painterResource(Res.drawable.ic_group),
                contentDescription = stringResource(Res.string.community),
                tint = Theme.color.surfaces.onSurface,
                onClick = onCommunityClick
            )
        }
    )
}