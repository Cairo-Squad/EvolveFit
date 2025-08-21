package com.cairosquad.evolvefit.ui.screen.mealDetails.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.cairosquad.evolvefit.design_system.component.appbar.ActionIconButton
import com.cairosquad.evolvefit.design_system.component.appbar.CustomAppBar
import com.cairosquad.evolvefit.design_system.theme.Theme
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.back
import evolvefit.composeapp.generated.resources.bookmark
import evolvefit.composeapp.generated.resources.ic_back
import evolvefit.composeapp.generated.resources.ic_bookmark
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
 fun MealDetailsAppBar(
    onBackClick: () -> Unit,
    onBookmarkClick: () -> Unit
) {
    CustomAppBar(
        modifier = Modifier
            .windowInsetsPadding(WindowInsets.statusBars),
        title = "",
        header = {
            ActionIconButton(
                icon = painterResource(Res.drawable.ic_back),
                contentDescription = stringResource(Res.string.back),
                tint = Theme.color.surfaces.textColor,
                onClick = onBackClick
            )
        },
        tail = {
            ActionIconButton(
                icon = painterResource(Res.drawable.ic_bookmark),
                contentDescription = stringResource(Res.string.bookmark),
                tint = Theme.color.surfaces.textColor,
                onClick = onBookmarkClick
            )
        }
    )
}